package combiz.ui.inventory;

import java.util.Date;

import combiz.business.inventory.InvrectransSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvrectransList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvrectransList()
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
		
		Invrectrans newobj = new Invrectrans();
		newobj.setItemnum(parent.getItemnum());
		newobj.setTowarehouse(parent.getWarehouse());
		this.mainObject = newobj;
		return true;
	}
}
