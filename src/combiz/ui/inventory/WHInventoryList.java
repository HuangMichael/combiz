package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Warehouse;
import combiz.system.ui.ListWindow;

public class WHInventoryList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WHInventoryList()
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
		//��ȡ�������������
		Warehouse parent = (Warehouse) ownerWnd.getMainObject();
		
		Inventory newobj = new Inventory();
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setAvgcost(0.00);
		newobj.setConversion(1D);
		newobj.setLastcost(0.00);
		newobj.setMaxlevel(0.00);
		newobj.setMinlevel(0.00);
		newobj.setOrderqty(0.00);
		newobj.setSstock(0.00);
		

		this.mainObject = newobj;
		return true;
	}
	
}
