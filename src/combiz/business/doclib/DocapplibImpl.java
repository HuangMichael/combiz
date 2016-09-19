package combiz.business.doclib;

import combiz.domain.doclib.Docapplib;
import combiz.domain.doclib.Doclibary;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class DocapplibImpl extends IBOBaseImpl
implements DocapplibSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Docapplib))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Docapplib docapplib = (Docapplib)obj;
		//�ж��ܷ�ɾ��
		//if(this.findWithQuery("libnum='"+docapplib.getLibnum()+"' and id<>'" + docapplib.getId() + "'"))
		//{
			
		//}
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	/**
	 * @TODO ɾ�������б�Ĺ���Ӧ�ó��������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List docapplibs) throws Exception {
		this.getBaseDao().deleteBatch(docapplibs);
	}


	@Override
	public void save(Object arg0) 
	throws Exception 
	{
		Docapplib docapplib = (Docapplib) arg0;
		
		//�ݹ������Ŀ¼
		if(docapplib.getId()==null || docapplib.getId().equals(""))
		{
			saveDownExtends(arg0);
		}
		
		//�Ѿ����ڲ����ٲ���
		/*if(this.getCountByWhere("libnum='" + docapplib.getLibnum() +"' and app='" + docapplib.getApp() +"' and ownertable='"+docapplib.getOwnertable()+"' and isrelapp='"+docapplib.getIsrelapp()+"'")>0)
		{
			return;
		}*/
		super.save(arg0);
	}
	/**
	 * @TODO �̳и�Ŀ¼�Ĺ�����Ӧ�ó��� ���ӵ���
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 ����07:05:55
	 */ 
	public void saveUpExtends(Object arg0) throws Exception
	{
		Doclibary doclibary = (Doclibary)arg0;
		List docapplibs = this.getBaseDao().findWithQuery(Docapplib.class, "libnum='" + doclibary.getParent() +"'");
		for (int i = 0; i < docapplibs.size(); i++)
		{
			Docapplib docapplib = (Docapplib)docapplibs.get(i);
			
			Docapplib docapplib1 = new Docapplib();
			
			docapplib1.setLibnum(doclibary.getLibnum());
			docapplib1.setApp(docapplib.getApp());
			docapplib1.setIsrelapp(docapplib.getIsrelapp());
			docapplib1.setRelquery(docapplib.getRelquery());
			docapplib1.setOwnertable(docapplib.getOwnertable());
			super.save(docapplib1);
		}
	}
	/**
	 * @TODO �̳и�Ŀ¼�Ĺ�����Ӧ�ó���,�ɸ�����
	 * @param  arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 ����07:05:55
	 */ 
	public void saveDownExtends(Object arg0) throws Exception
	{
		Docapplib docapplib = (Docapplib)arg0;
		
		List doclibarys = this.getBaseDao().findWithQuery(Doclibary.class, "parent='" + docapplib.getLibnum() +"'");
		for (int i = 0; i < doclibarys.size(); i++) {
			Doclibary doclibary1 = (Doclibary)doclibarys.get(i);
			//saveUpExtends(doclibary1);
			Docapplib docapplib1 = new Docapplib();
			
			docapplib1.setLibnum(doclibary1.getLibnum());
			docapplib1.setApp(docapplib.getApp());
			docapplib1.setIsrelapp(docapplib.getIsrelapp());
			docapplib1.setRelquery(docapplib.getRelquery());
			docapplib1.setOwnertable(docapplib.getOwnertable());
			//�Ѿ����ڲ����ٲ���
			/*if(this.getCountByWhere("libnum='" + docapplib1.getLibnum() +"' and app='" + docapplib1.getApp() +"' and ownertable='"+docapplib.getOwnertable()+"' and isrelapp='"+docapplib.getIsrelapp()+"'")>0)
			{
				return;
			}*/
			super.save(docapplib1);
			
			//�ݹ�
			saveDownExtends(docapplib1);
		}
	}
	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
