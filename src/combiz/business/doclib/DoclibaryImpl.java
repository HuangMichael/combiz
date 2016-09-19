package combiz.business.doclib;

import combiz.domain.doclib.Doclibance;
import combiz.domain.doclib.Doclibary;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;
import combiz.system.util.DocUtil;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class DoclibaryImpl extends IBOBaseImpl
implements DoclibarySrv {

////////////////////ͨ�÷���///////////////////////////////////////
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
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Doclibary))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		Doclibary doclibary = (Doclibary)obj;
		List childrens = this.findWithQuery("parent = '" + doclibary.getLibnum() +"'");
		if(childrens!=null && childrens.size()>0)
		{
			throw new Exception("���Ӽ����࣬��ѡ����ײ�������ɾ����");
		}
		//�޸ĸ�������
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
					parent.setHaschild("��");
					this.update(parent);
				}
			}
		}
		
		//ɾ�����ȱ�����
		List anceList = this.getBaseDao().findWithQuery(Doclibance.class, "libnum='"+doclibary.getLibnum()+"'");
		this.doclibanceSrv.deleteBatch(anceList);
		
		//ɾ����ǰĿ¼�µ������ĵ����汾������
		//this.documentSrv.deleteBatch(this.getBaseDao().findWithQuery(Document.class, "libnum='"+doclibary.getLibnum()+"'"));

		//ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "docapplibTable");
		this.deleteAllChild(obj,"childdocumentTable");
		
		//ɾ��ʵ��ϵͳĿ¼�ṹ
		DocUtil.deletePath(doclibary.getLibpath());

		//ɾ������
		super.delete(obj);
		
	}


	/**
	 * @TODO
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-7 ����05:51:03
	 * @see combiz.system.IBOBaseImpl#save(java.lang.Object)
	 */
	@Override
	public void save(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		Doclibary doclibary = (Doclibary)arg0;
		if(doclibary.getId()==null || doclibary.getId().equals(""))
		{
			doclibary.setHaschild("��");
			//�������
			//���ȱ��������			
			Doclibance doclibance = new Doclibance();
			doclibance.setLibnum(doclibary.getLibnum());
			doclibance.setAncestor(doclibary.getLibnum());
			this.getDoclibanceSrv().save(doclibance);
			
			Doclibary  temp = this.getParent(doclibary);
			//�޸ĸ��ڵ�
			if(temp!=null && temp.getHaschild()!=null && !temp.getHaschild().equals("��"))
			{
				temp.setHaschild("��");
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
			//��������
			super.save(doclibary);
			
			//�̳и�Ŀ¼������
			this.docapplibSrv.saveUpExtends(arg0);
			
			//����ʵ��ϵͳĿ¼�ṹ
			DocUtil.createPath(doclibary.getLibpath());
		}
		else
		{
			//��������
			super.save(doclibary);
			
			//�̳и�Ŀ¼������
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

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
