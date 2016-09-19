package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Warehouse;
import combiz.system.ui.ListWindow;

public class WHInventoryList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WHInventoryList()
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
