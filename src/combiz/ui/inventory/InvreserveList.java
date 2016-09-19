package combiz.ui.inventory;

import java.util.Date;

import combiz.business.inventory.InvusetransSrv;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvreserveList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InvreserveList()
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
		//InventoryWindow parentWnd = (InventoryWindow)ownerWnd;
		Inventory parent = (Inventory)this.getOwnerWnd().getMainObject();
		
		Invreserve newobj = new Invreserve();
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setReqby(this.getLaborInfo().getLabornum());
		newobj.setReqdate(new Date());
		//newobj.setCorpnum(parent.getCorpnum());
		//newobj.setSitenum(parent.getSitenum());
		this.mainObject = newobj;
		return true;
	}
}
