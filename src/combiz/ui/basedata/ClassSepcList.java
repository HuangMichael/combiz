package combiz.ui.basedata;

import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.system.ui.ListWindow;

public class ClassSepcList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ClassSepcList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼�������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		//ItemClassWindow parentWnd = (ItemClassWindow)ownerWnd;
		Classification parent = (Classification) this.getOwnerWnd().getMainObject();
		
		Classspec newobj = new Classspec();
		newobj.setClassid(parent.getClassid());
		newobj.setIsmustbe("��");
		this.mainObject = newobj;
		return true;
	}
	
}