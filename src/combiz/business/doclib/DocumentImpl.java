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
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class DocumentImpl extends IBOBaseImpl
implements DocumentSrv {
	
	private static Logger log = Logger.getLogger(DocumentImpl.class);
	
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Document))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "docversion");
		this.deleteAllChild(obj, "docauth");
		//ɾ������
		super.delete(obj);
		
		//ɾ���ĵ��汾���ļ��У�����ɾ��Ŀ¼�ڵ��ļ�
		/*try
		{
			Document document = (Document)obj;
			if(document.getId()!=null)
				DocUtil.deletePath(document.getUrlpath());
		}
		catch(Exception e)
		{
			log.error("ɾ���ĵ���·��ʱ����",e);
		}*/
	}

	/**
	 * 
	 * �����ĵ�����
	 * @param obj
	 * @throws Exception
	 * @��С��  2007-8-17  ����01:54:09
	 */
	@Override
	public void save(Object obj)
	throws Exception 
	{
		Document document = (Document)obj;
		if(document.getId()==null) 	//���½��ĵ���ʱ��ͬʱ�����ĵ�Ŀ¼
		{
			document.setUrlpath(document.getUrlpath() + document.getDocnum() + "/");
			//�����ļ�·��
			DocUtil.createPath(document.getUrlpath());
			
			//begin--�豸ͼ--�Զ����ɵ�һ���汾��¼
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
						newobj.setStatus("���ϴ�");
						newobj.setUrlpath(document.getUrlpath());
						newobj.setUrltype(document.getUrltype());
						//newobj.setSitenum(document.getSitenum());
						//newobj.setCorpnum(document.getCorpnum());
						//�Զ�����ͼ���ļ���
						newobj.setFilename(document.getDocnum()+"_v1.ibx");
						//ͨ���÷������棬���Դ����ļ����ļ���
						ListWindow listwnd = (ListWindow) owner.getFellow("docversion");
						listwnd.getMainSrv().save(newobj);
					}
				}
			}
			//end---�豸ͼ--�Զ����ɰ汾��¼
			
			//�Զ����ɹ���Ա��Ȩ
			Docauth docauth = new Docauth();
			docauth.setDocedit("��");
			docauth.setDocnum(document.getDocnum());
			docauth.setDocread("��");
			docauth.setGrpname("ADMIN");
			docauth.setMemo("Ĭ����Ȩ��ϵͳ����Ա��");
			super.getBaseDao().saveObject(docauth);
		}
		
		super.save(document);
	}


	/**
	 * ɾ�������б���ĵ�������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
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
	 * �����е��ĵ����п����ĵ�
	 * ��С��  2010-5-10
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
			
			//������Ȩ
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
			
			
			//�����汾
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
				
				docversion.setContenttype(fromDocversion.getContenttype()); //�����뿽�����ĵ���ͬ
				docversion.setVersion(fromDocversion.getVersion()); //�����뿽�����ĵ���ͬ
				docversion.setStatus(fromDocversion.getStatus()); //�����뿽�����ĵ���ͬ
				docversion.setFilename(fromDocversion.getFilename()); //�����뿽�����ĵ���ͬ
				docversion.setUrlpath(fromDocversion.getUrlpath()); //�����뿽�����ĵ���ͬ
				super.getBaseDao().saveObject(docversion);
			}
		}
	}
}
