package ${package};

import ${domainPackage}.*;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;

public class ${classImpl} extends IBOBaseImpl
implements ${classSrv}
{

	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave(Object obj) 
	throws Exception
	{
		return true;
	}
	
	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeUpdate(Object obj) 
	throws Exception
	{
		return true;
	}
	
	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeInsert(Object obj) 
	throws Exception
	{
		return true;
	}
	
	
	
	/**
	 * �û��ӿ�
	 * �����½��������û��ӿڷ���
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterInsert(Object obj) 
	throws Exception
	{
	}
	
	/**
	 * �û��ӿ�
	 * ������¶������û��ӿڷ���
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterUpdate(Object obj) 
	throws Exception
	{
	}
	
	/**
	 * ɾ������֮ǰ���û��ӿ�
	 * ����false�Ļ�����ɾ���ö���
	 * @param obj
	 * @throws Exception
	 */
	public boolean beforeDelete(Object obj) throws Exception
	{
		return true;
	}
	
	/**
	 * ϵͳɾ������ķ���
	 * �����ڸ÷������ֹ�ָ��ɾ����Щ�ӱ�������ͨ����ϵ����
	 * 
	 * @param obj ������
	 * @throws Exception
	 */
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");  //ͨ����ϵ��ɾ���ֱ�����
		super.delete(obj);
	}
	
	
	/**
	 * ɾ���������û��ӿ�
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void afterDelete(Object obj) throws Exception
	{
		
	}

	
	/**
	 * ������������ͨ���÷��������������״̬
	 * @param obj ������
	 * @param toStatus ״̬
	 * @throws Exception
	 */
	public void changeStatus(Object obj, String toStatus) throws Exception
	{
		super.changeStatus(obj, toStatus);
	}

	/**
	 * ����������ʱ�����ô˽ӿڡ�
	 * �ڷ��͹�����֮ǰ�ж����ݵ������ԡ�
	 * �ڷ�������֮ǰ���ø÷���,Ч�鹤������Ӧ�����������Ƿ���Ч
	 * ����ͨ���������ȡ�ӱ��¼������Ч��:List childs = this.getListByRelation(parentObj, relation)
	 * ����ǵ�һ������������,��ô����wfinstance==null
	 * ����д���,���Ե���Messagebox��Ϣ,�����׳��쳣 throw new Exception("�쳣��Ϣ");
	 * ����true�������������ߣ��������false�������ж�
	 * @param mainObject ������
	 * @param wfinstance ��������ʵ��
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance) throws Exception
	{
		return true;
	}
	
	
	/**
	 * ����������ʱ��������һ������ѡ�񴰿ڣ�ѡ��������ߺ󣬵��ô˽ӿڡ�
	 * ���Ը���ѡ�����һ���������ж����ݵ������ԣ������Ƿ�ִ����һ����������
	 * �ڷ�������ѡ���������ø÷���,Ч�鹤������Ӧ�����������Ƿ���Ч
	 * ����ͨ���������ȡ�ӱ��¼������Ч��:List childs = this.getListByRelation(parentObj, relation)
	 * ����ǵ�һ������������,��ô����wfinstance==null
	 * ����д���,���Ե���Messagebox��Ϣ,�����׳��쳣 throw new Exception("�쳣��Ϣ");
	 * ����true�������������ߣ��������false�������ж�
	 * @param mainObject ������
	 * @param wfinstance ��������ʵ��
	 * @param wfaction  ��һ���Ĳ���
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance, Wfaction wfaction) throws Exception
	{
		return true;
	}
	
	
	/**
	 * �����������û����·���
	 * �û��ӿ�
	 */
	public void wfReassign()
	throws Exception
	{
		//�û��Զ���ӿڷ���
	}
	
}