package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.system.ui.ListWindow;

public class ItemInventoryList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ItemInventoryList()
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
		Item parent = (Item) ownerWnd.getMainObject();
		
		Inventory newobj = new Inventory();
		String itemnum = parent.getItemnum();
		newobj.setItemnum(itemnum);
		newobj.setModelnum(parent.getModelnum());
		newobj.setStocktype("�ǳ������");
		newobj.setStdcost(0.0);
		newobj.setConversion(1D);
		newobj.setIssueunit(parent.getIssueunit());
		newobj.setLastcost(0.0);
		newobj.setAvgcost(0.0);
		newobj.setMaxlevel(0.0);
		newobj.setMinlevel(0.0);
		newobj.setOrderqty(0.0);
		newobj.setIssueytd(0.0);
		newobj.setOrderunit(parent.getOrderunit());
		newobj.setSstock(0.0);
		

		this.mainObject = newobj;
		return true;
	}
	
}
