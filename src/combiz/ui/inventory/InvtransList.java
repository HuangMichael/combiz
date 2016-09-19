package combiz.ui.inventory;

import java.util.Date;

import combiz.business.inventory.InvtransSrv;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvtransList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InvtransList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：高群凯 日期：2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		InventoryWindow parentWnd = (InventoryWindow)ownerWnd;
		Inventory parent = (Inventory)parentWnd.getMainObject();
		
		Invtrans newobj = new Invtrans();
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		this.mainObject = newobj;
		return true;
	}
}
