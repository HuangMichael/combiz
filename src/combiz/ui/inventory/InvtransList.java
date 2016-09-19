package combiz.ui.inventory;

import java.util.Date;

import combiz.business.inventory.InvtransSrv;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvtransList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvtransList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		InventoryWindow parentWnd = (InventoryWindow)ownerWnd;
		Inventory parent = (Inventory)parentWnd.getMainObject();
		
		Invtrans newobj = new Invtrans();
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		this.mainObject = newobj;
		return true;
	}
}
