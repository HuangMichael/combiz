package ${uipackage};

import ${mainObjectClass};
import ${childObjectClass};
import combiz.system.ui.ListWindow;
import com.inbasis.zul.Listitem;

public class ${listWindowName} extends ListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 */
	public ${listWindowName}()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		${ftablename} parent = (${ftablename}) this.getOwnerWnd().getMainObject();
		
		${fchildtablename} newobj= new ${fchildtablename}();
		//newobj.setXXXX(parent.getXXXX()); //������ӹ����ֶ�ֵ

		this.mainObject = newobj;
		return true;
	}
	
	
	/**
	 * �Զ���ӿ�
	 * �����Զ���Ĭ�ϵ�һ�εĲ�ѯ�������򿪽���ʱִ��һ�Σ��Ժ�Ĳ�ѯ������ִ�и�����
	 * @return
	 * @throws Exception 
	 */
	public String getDefaultQueryString()
	throws Exception
	{
		return null;
	}
	

	/**
	 * ��ʼ��һ��������ʱ����û��ӿ�
	 * 
	 * @param listitem  �����пؼ�
	 * @param objtmp  �����ж�Ӧ��������ֵ
	 * @throws Exception
	 */
	public void initRowData(Listitem listitem, Object objtmp) throws Exception
	{
		//����Զ������
	}
	
	/**
	 * �б���ѡ����ĳ����¼�����ø÷���
	 * brianhong  2007-11-2
	 */
	public void onRowSelected()
	{
		//����Զ������
	}


	/**
	 * �ڱ��水ť������ڱ��涯��ִ��֮����û��ӿ�
	 * 
	 * @throws Exception
	 */
	public void afterSave() throws Exception
	{
		//����Զ������
	}

	/**
	 * �ڱ��水ť������ڱ��涯��ִ��ǰ���û��ӿ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave() throws Exception
	{
		return true;
	}



	/**
	 * ��ɾ���а�ť�������ɾ������ִ��ǰ���û��ӿ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean beforeRemove() throws Exception
	{
		return true;
	}
	
	/**
	 * ��ɾ���а�ť�������ɾ����Ƕ���ִ��֮����û��ӿ�
	 * 
	 * @throws Exception
	 */
	public void afterRemove() throws Exception
	{
		//����Զ������
	}
	
}
