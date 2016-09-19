package combiz.ui.inventory;


import java.util.Date;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InventorySrv;
import combiz.business.inventory.ItemSrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Item;
import combiz.domain.tool.Tool;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class AdjInventoryDialog 
extends CommonDialog
{
	
	public AdjInventoryDialog()
	{
		super();
	}
	public void onCreate()
	throws Exception
	{
		super.onCreate();
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：调整余量
	 * 日期：Jun 18, 2008 8:02:29 AM
	 *
	 */
	public void curbalnum() 
	throws Exception
	{
		
		//ToolSrv toolsrv = (ToolSrv) this.getMainSrv();
		InventorySrv invsrv = (InventorySrv) IBOSrvUtil.getSrv("inventory");
		Invtrans  invtrans = (Invtrans) this.getMainObject();
		MainWindow inventorywnd =(MainWindow) this.getOwnerWnd();
		Inventory inventory = (Inventory) inventorywnd.getMainObject();
		String binnum = invtrans.getBinnum();
		//String frombin = invrectrans.getFrombin();
		Double curbal = invtrans.getCurbal();
		Double quantity = invtrans.getQuantity();
		
//		if(!Util.isNotNull(binnum))
//		{
//			throw new Exception("箱柜号不能为空,请确认!");
//		}
		if((quantity == null)||quantity<0)
		{
			invtrans.setQuantity(null);
			this.refreshData();
			throw new Exception("调整数量为空或者为负值,请确认!");
		}
		

		if (!(invtrans instanceof Invtrans))
			throw new Exception("请选择一条记录!");
		invsrv.editcurbal(invtrans);
		this.detach();
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：调整物理盘点
	 * 日期：Jun 18, 2008 8:02:29 AM
	 *
	 */
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：调整平均成本
	 * 日期：Jun 18, 2008 8:04:00 AM
	 *
	 */
	public void createinventory() 
	throws Exception
	{
		ItemSrv itemsrv = (ItemSrv) IBOSrvUtil.getSrv("item");
		Inventory  inventory = (Inventory) this.getMainObject();
		MainWindow itemwnd =(MainWindow) this.getOwnerWnd();
		Item item = (Item) itemwnd.getMainObject();
		//String binnum = invtrans.getBinnum();
		//String frombin = invrectrans.getFrombin();
		Double avgcost = inventory.getAvgcost();
		Double stdcost = inventory.getStdcost();
		Double lastcost = inventory.getLastcost();
		
		Double minlevel = inventory.getMinlevel();
		Double maxlevel = inventory.getMaxlevel();
		Double orderqty = inventory.getOrderqty();
		
		Double invcurbal = inventory.getInvcurbal();
		Double invphycnt = inventory.getInvphycnt();
		Date invphydate = inventory.getInvphydate();
		
		if (avgcost == null) {
			avgcost = 0D;
		}
		if (stdcost == null) {
			stdcost = 0D;
		}
		if (lastcost == null) {
			lastcost = 0D;
		}
		if (avgcost < 0) {
			inventory.setAvgcost(0D);
			this.refreshData();
			throw new Exception("物资平均价格不能小于0,请核实!");
		}
		if (stdcost < 0) {
			inventory.setStdcost(0D);
			this.refreshData();
			throw new Exception("物资标准成本不能小于0,请核实!");
		}
		if (lastcost < 0) {
			inventory.setLastcost(0D);
			this.refreshData();
			throw new Exception("物资上次成本不能小于0,请核实!");
		}
		
		if (minlevel == null) {
			minlevel = 0D;
		}
		if (maxlevel == null) {
			maxlevel = 0D;
		}
		if (orderqty == null) {
			orderqty = 0D;
		}
		if (minlevel < 0) {
			inventory.setMinlevel(0D);
			this.refreshData();
			throw new Exception("最低库存不能小于0,请核实!");
		}
		if (maxlevel < 0) {
			inventory.setMaxlevel(0D);
			this.refreshData();
			throw new Exception("安全库存不能小于0,请核实!");
		}
		if (orderqty < 0) {
			inventory.setOrderqty(0D);
			this.refreshData();
			throw new Exception("经济订购不能小于0,请核实!");
		}
		
		if(invcurbal == null) {
			invcurbal = 0D;
		}
		
		if(invcurbal < 0) {
			inventory.setInvcurbal(0D);
			this.refreshData();
			throw new Exception("余量不能小于0,请核实!");
		}
		
		if (invphycnt == null) {
			invphycnt = 0D;
		}
		
		if (invphycnt < 0) {
			inventory.setInvphycnt(0D);
			this.refreshData();
			throw new Exception("物理盘点数量不能小于0,请核实!");
		}
		
		if (inventory.getWarehouse() == null || inventory.getWarehouse().equals("")) {
			inventory.setWarehouse(null);
			this.refreshData();
			throw new Exception("请选择仓库 ！");
		}
		
		if (!(item instanceof Item))
			throw new Exception("请选择一条记录!");
		itemsrv.createinventory(inventory);
		this.detach();
	}
}
