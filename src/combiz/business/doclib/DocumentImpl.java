package combiz.business.doclib;

import combiz.domain.doclib.Docauth;
import combiz.domain.doclib.Doclibary;
import combiz.domain.doclib.Document;
import combiz.domain.doclib.Docversion;
import combiz.system.IBOBaseImpl;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;
import combiz.system.util.DocUtil;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class DocumentImpl extends IBOBaseImpl
implements DocumentSrv {
	
	private static Logger log = Logger.getLogger(DocumentImpl.class);
	
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Document))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除关联对象－父类方法
		this.deleteAllChild(obj, "docversion");
		this.deleteAllChild(obj, "docauth");
		//删除本身
		super.delete(obj);
		
		//删除文档版本的文件夹，并且删除目录内的文件
		/*try
		{
			Document document = (Document)obj;
			if(document.getId()!=null)
				DocUtil.deletePath(document.getUrlpath());
		}
		catch(Exception e)
		{
			log.error("删除文档及路径时出错",e);
		}*/
	}

	/**
	 * 
	 * 保存文档数据
	 * @param obj
	 * @throws Exception
	 * @洪小林  2007-8-17  下午01:54:09
	 */
	@Override
	public void save(Object obj)
	throws Exception 
	{
		Document document = (Document)obj;
		if(document.getId()==null) 	//当新建文档的时候同时创建文档目录
		{
			document.setUrlpath(document.getUrlpath() + document.getDocnum() + "/");
			//创建文件路径
			DocUtil.createPath(document.getUrlpath());
			
			//begin--设备图--自动生成第一条版本记录
			RecWindow recwnd = this.getRecWnd();
			if(recwnd!=null)
			{
				RecWindow owner = recwnd.getOwnerWnd();
				if(owner!=null)
				{
					String app = owner.getApp();
					if(app!=null && app.equals("EQUIPDRAW"))
					{
						Docversion newobj = new Docversion();
						newobj.setVersion("v1");
						newobj.setDocnum(document.getDocnum());
						newobj.setLibnum(document.getLibnum());
						newobj.setCreatedate(new Date());
						newobj.setCreator(this.getUserInfo().getLabornum());
						newobj.setDescription(document.getDescription());
						newobj.setOwnerid(document.getOwnerid());
						newobj.setOwnertable(document.getOwnertable());
						newobj.setRevdate(new Date());
						newobj.setStatus("已上传");
						newobj.setUrlpath(document.getUrlpath());
						newobj.setUrltype(document.getUrltype());
						//newobj.setSitenum(document.getSitenum());
						//newobj.setCorpnum(document.getCorpnum());
						//自动生成图形文件名
						newobj.setFilename(document.getDocnum()+"_v1.ibx");
						//通过该方法保存，可以创建文件及文件夹
						ListWindow listwnd = (ListWindow) owner.getFellow("docversion");
						listwnd.getMainSrv().save(newobj);
					}
				}
			}
			//end---设备图--自动生成版本记录
			
			//自动生成管理员授权
			Docauth docauth = new Docauth();
			docauth.setDocedit("是");
			docauth.setDocnum(document.getDocnum());
			docauth.setDocread("是");
			docauth.setGrpname("ADMIN");
			docauth.setMemo("默认授权给系统管理员组");
			super.getBaseDao().saveObject(docauth);
		}
		
		super.save(document);
	}


	/**
	 * 删除给定列表的文档表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List documents)
	throws Exception
	{
		for (int i = 0; i < documents.size(); i++) {
			Document document = (Document)documents.get(i);
			this.delete(document);
		}
		//this.deleteBatch(documents);
	}

	
	/**
	 * 从已有的文档库中拷贝文档
	 * 洪小林  2010-5-10
	 * @param docsList
	 * @throws Exception 
	 */
	public void copyDocument(Object mainObject, Doclibary doclibary, List docsList) 
	throws Exception
	{
		Long id = (Long) Util.getValueFromObject(mainObject, "id");
		for(int i=0;i<docsList.size();i++)
		{
			Document fromDoc = (Document) docsList.get(i);
			
			Document newobj = new Document();
			newobj.setDocnum(this.genInskey("docnum"));
			newobj.setLibnum(doclibary.getLibnum());
			newobj.setAuthor(this.getLaborInfo().getLabornum());
			newobj.setAuthordate(new Date());
			newobj.setChangeby(this.getLaborInfo().getLabornum());
			newobj.setChangedate(new Date());
			newobj.setCreatedate(new Date());
			newobj.setCreator(this.getLaborInfo().getLabornum());
			newobj.setOwnerdept(this.getLaborInfo().getDeptnum());
			newobj.setOwnertable(mainObject.getClass().getSimpleName().toUpperCase());
			newobj.setOwnerid(id);
			newobj.setStatus(fromDoc.getStatus());
			newobj.setUrlpath(fromDoc.getUrlpath());
			newobj.setUrltype(fromDoc.getUrltype());
			newobj.setDoctype(fromDoc.getDoctype());
			newobj.setDocextend(fromDoc.getDocextend());
			newobj.setDescription(fromDoc.getDescription());
			this.getBaseDao().saveObject(newobj);
			
			//拷贝授权
			List fromDocauthList = this.getBaseDao().findWithQuery(Docauth.class, "docnum='"+fromDoc.getDocnum()+"'");
			for(int j=0;j<fromDocauthList.size();j++)
			{
				Docauth fromDocauth = (Docauth) fromDocauthList.get(j);
				Docauth docauth = new Docauth();
				docauth.setDocnum(newobj.getDocnum());
				docauth.setDocedit(fromDocauth.getDocedit());
				docauth.setDocread(fromDocauth.getDocread());
				docauth.setGrpname(fromDocauth.getGrpname());
				docauth.setMemo(fromDocauth.getMemo());
				super.getBaseDao().saveObject(docauth);
			}
			
			
			//拷贝版本
			List fromDocversionList = this.getBaseDao().findWithQuery(Docversion.class, "docnum='"+fromDoc.getDocnum()+"'");
			for(int j=0;j<fromDocversionList.size();j++)
			{
				Docversion fromDocversion = (Docversion) fromDocversionList.get(j);
				Docversion docversion = new Docversion();
				docversion.setDocnum(newobj.getDocnum());
				docversion.setLibnum(newobj.getLibnum());
				docversion.setCreatedate(new Date());
				docversion.setCreator(this.getUserInfo().getLabornum());
				docversion.setDescription(newobj.getDescription());
				docversion.setOwnerid(newobj.getOwnerid());
				docversion.setOwnertable(newobj.getOwnertable());
				docversion.setRevdate(new Date());
				docversion.setUrltype(newobj.getUrltype());
				
				docversion.setContenttype(fromDocversion.getContenttype()); //必须与拷贝的文档相同
				docversion.setVersion(fromDocversion.getVersion()); //必须与拷贝的文档相同
				docversion.setStatus(fromDocversion.getStatus()); //必须与拷贝的文档相同
				docversion.setFilename(fromDocversion.getFilename()); //必须与拷贝的文档相同
				docversion.setUrlpath(fromDocversion.getUrlpath()); //必须与拷贝的文档相同
				super.getBaseDao().saveObject(docversion);
			}
		}
	}
}
