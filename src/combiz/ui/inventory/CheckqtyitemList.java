package combiz.ui.inventory;

import combiz.domain.inventory.Checkqty;
import combiz.domain.inventory.Checkqtyitem;
import combiz.system.ui.ListWindow;

public class CheckqtyitemList extends ListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CheckqtyitemList()
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
//		Checkqty parent = (Checkqty) this.getOwnerWnd().getMainObject();
//		
//		Checkqtyitem newobj= new Checkqtyitem();
//		//newobj.setXXXX(parent.getXXXX()); //������ӹ����ֶ�ֵ
//
//		this.mainObject = newobj;
//		return true;
		throw new Exception("���������ϸ�У����ڲ����˵���ѡ�������̵���ϸ���в�����");
	}
}
