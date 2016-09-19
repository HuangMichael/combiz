package combiz.ui.inventory;


import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InventorySrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invusetrans;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class IssueInventoryDialog 
extends CommonDialog
{
	
	public IssueInventoryDialog()
	{
		super();
	}
	public void onCreate()
	throws Exception
	{
		super.onCreate();
	}
	

	public void issue() 
	throws Exception
	{
		
		//ToolSrv toolsrv = (ToolSrv) this.getMainSrv();
		InventorySrv invsrv = (InventorySrv) IBOSrvUtil.getSrv("inventory");
		Invusetrans  invusetrans = (Invusetrans) this.getMainObject();
		MainWindow inventorywnd =(MainWindow) this.getOwnerWnd();
		Inventory inventory = (Inventory) inventorywnd.getMainObject();
		String location = invusetrans.getLocation();
		String issueto = invusetrans.getIssuetolabor();
//		String tobin = invrectrans.getTobin();
//		String frombin = invrectrans.getFrombin();
//		Double curbal = invrectrans.getCurbal();
		Double quantity = invusetrans.getQuantity();
		
		if(Util.isNull(location)&&Util.isNull(issueto))
		{
			throw new Exception("发放位置和发放人员不能同时为空,请确认!");
		}
//		if((quantity == null)||quantity<0)
//		{
//			invrectrans.setQuantity(null);
//			this.refreshData();
//			throw new Exception("转移数量为空或者为负值,请确认!");
//		}
//		if(quantity - curbal >0)
//		{
//			throw new Exception("转移数量大于原箱柜上存放余量，不能调整,请确认!");
//		}
//
//		if (!(invrectrans instanceof Invrectrans))
//			throw new Exception("请选择一条记录!");
		invsrv.issue(invusetrans);
		this.detach();
	}
	
	
	
}
