package combiz.ui.inventory;

import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.ListWindow;

public class RejecteqList extends ListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public RejecteqList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
//		//��ȡ�������������
//		Reject parent = (Reject) this.getOwnerWnd().getMainObject();
//		
//		Rejectitem newobj= new Rejectitem();
//		//newobj.setXXXX(parent.getXXXX()); //������ӹ����ֶ�ֵ
//
//		this.mainObject = newobj;
//		return true;
		throw new Exception("���������ϸ�У����ڲ����˵���ѡ�񱨷���ϸ�н��в�����");
	}
}
