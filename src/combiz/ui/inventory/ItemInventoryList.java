package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.system.ui.ListWindow;

public class ItemInventoryList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ItemInventoryList()
	{
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Item parent = (Item) ownerWnd.getMainObject();
		
		Inventory newobj = new Inventory();
		String itemnum = parent.getItemnum();
		newobj.setItemnum(itemnum);
		newobj.setModelnum(parent.getModelnum());
		newobj.setStocktype("非常备库存");
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
