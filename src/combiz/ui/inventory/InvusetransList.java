package combiz.ui.inventory;

import java.util.Date;

import combiz.business.inventory.InvusetransSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvusetransList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvusetransList()
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
		
		Invusetrans newobj = new Invusetrans();
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		this.mainObject = newobj;
		return true;
	}
}
