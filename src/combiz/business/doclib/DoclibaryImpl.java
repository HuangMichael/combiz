package combiz.business.doclib;

import combiz.domain.doclib.Doclibance;
import combiz.domain.doclib.Doclibary;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;
import combiz.system.util.DocUtil;

import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class DoclibaryImpl extends IBOBaseImpl
implements DoclibarySrv {

////////////////////通用方法///////////////////////////////////////
	/**
	 * 
	 */
	private DoclibanceSrv doclibanceSrv;
	private DocumentSrv documentSrv;
	private DocapplibSrv docapplibSrv;
	
	public DoclibaryImpl()
	{
		super();
		doclibanceSrv = (DoclibanceSrv) IBOSrvUtil.getSrv("doclibance");
		documentSrv = (DocumentSrv) IBOSrvUtil.getSrv("document");
		docapplibSrv = (DocapplibSrv) IBOSrvUtil.getSrv("docapplib");
	}
	
	
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Doclibary))
			throw new Exception("要删除的对象传递不正确！");
		
		Doclibary doclibary = (Doclibary)obj;
		List childrens = this.findWithQuery("parent = '" + doclibary.getLibnum() +"'");
		if(childrens!=null && childrens.size()>0)
		{
			throw new Exception("有子集分类，请选择最底层分类进行删除！");
		}
		//修改父级分类
		if(doclibary.getParent()!=null)
		{
			List sameParentList = this.findWithQuery("parent = '"+doclibary.getParent()+"' and libnum<>'"
					+ doclibary.getLibnum() + "'");
			if(sameParentList == null || sameParentList.size()<=0)
			{
				List parentList = this.findWithQuery("libnum = '"+doclibary.getParent()+"'");
				if(parentList!=null && parentList.size()>0)
				{
					Doclibary parent = (Doclibary)parentList.get(0);
					parent.setHaschild("否");
					this.update(parent);
				}
			}
		}
		
		//删除祖先表数据
		List anceList = this.getBaseDao().findWithQuery(Doclibance.class, "libnum='"+doclibary.getLibnum()+"'");
		this.doclibanceSrv.deleteBatch(anceList);
		
		//删除当前目录下的所有文档及版本表数据
		//this.documentSrv.deleteBatch(this.getBaseDao().findWithQuery(Document.class, "libnum='"+doclibary.getLibnum()+"'"));

		//删除关联对象－父类方法
		this.deleteAllChild(obj, "docapplibTable");
		this.deleteAllChild(obj,"childdocumentTable");
		
		//删除实际系统目录结构
		DocUtil.deletePath(doclibary.getLibpath());

		//删除本身
		super.delete(obj);
		
	}


	/**
	 * @TODO
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-7 下午05:51:03
	 * @see combiz.system.IBOBaseImpl#save(java.lang.Object)
	 */
	@Override
	public void save(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		Doclibary doclibary = (Doclibary)arg0;
		if(doclibary.getId()==null || doclibary.getId().equals(""))
		{
			doclibary.setHaschild("否");
			//添加数据
			//祖先表添加数据			
			Doclibance doclibance = new Doclibance();
			doclibance.setLibnum(doclibary.getLibnum());
			doclibance.setAncestor(doclibary.getLibnum());
			this.getDoclibanceSrv().save(doclibance);
			
			Doclibary  temp = this.getParent(doclibary);
			//修改父节点
			if(temp!=null && temp.getHaschild()!=null && !temp.getHaschild().equals("是"))
			{
				temp.setHaschild("是");
				super.update(temp);
			}
			while(temp!=null)
			{
				Doclibance parentdoclibance = new Doclibance();
				parentdoclibance.setLibnum(doclibary.getLibnum());
				parentdoclibance.setAncestor(temp.getLibnum());
				this.getDoclibanceSrv().save(parentdoclibance);				
				
				temp = this.getParent(temp);
			}
			//保存自身
			super.save(doclibary);
			
			//继承父目录的属性
			this.docapplibSrv.saveUpExtends(arg0);
			
			//创建实际系统目录结构
			DocUtil.createPath(doclibary.getLibpath());
		}
		else
		{
			//更新数据
			super.save(doclibary);
			
			//继承父目录的属性
			//saveDownExtends(doclibary);
		}
	}
	public String getPathStr()
	{
		return "";
	}
	public Doclibary getParent(Doclibary doclibary)
	throws Exception 
	{
		List parentList = this.findWithQuery("libnum = '" + doclibary.getParent() + "'");
		if(parentList.size()>0)
			return (Doclibary)parentList.get(0);
		
		return null;
			
	}


	public DoclibanceSrv getDoclibanceSrv() {
		return doclibanceSrv;
	}


	public void setDoclibanceSrv(DoclibanceSrv doclibanceSrv) {
		this.doclibanceSrv = doclibanceSrv;
	}


	public DocumentSrv getDocumentSrv() {
		return documentSrv;
	}


	public void setDocumentSrv(DocumentSrv documentSrv) {
		this.documentSrv = documentSrv;
	}


	public DocapplibSrv getDocapplibSrv() {
		return docapplibSrv;
	}


	public void setDocapplibSrv(DocapplibSrv docapplibSrv) {
		this.docapplibSrv = docapplibSrv;
	}

	
/////////////////////业务方法//////////////////////////////////
}
