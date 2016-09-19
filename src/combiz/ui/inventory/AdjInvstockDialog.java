package combiz.ui.inventory;


import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InventorySrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invtrans;
import combiz.domain.tool.Tool;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.IBSServer;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class AdjInvstockDialog 
extends CommonDialog
{
	
	public AdjInvstockDialog()
	{
		super();
	}
	public void onCreate()
	throws Exception
	{
		super.onCreate();
	}
	

	public void adjustbinnum() 
	throws Exception
	{
		
		//ToolSrv toolsrv = (ToolSrv) this.getMainSrv();
		InventorySrv invsrv = (InventorySrv) IBOSrvUtil.getSrv("inventory");
		Invrectrans  invrectrans = (Invrectrans) this.getMainObject();
		MainWindow inventorywnd =(MainWindow) this.getOwnerWnd();
		Inventory inventory = (Inventory) inventorywnd.getMainObject();
		String tobin = invrectrans.getTobin();
		String binnum = invrectrans.getFrombin();
		List invstocklist = null;
		Double curbal = invrectrans.getCurbal();
		/*Invstock invstock = null;
		    if(Util.isNull(binnum))
	        {
		    	String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
		    	if(DBProduct.equals("SQLSERVER"))
				{
		    		invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "warehouse='"+invrectrans.getFromwarehouse()+"' and itemnum='"+invrectrans.getItemnum()+"'and (binnum is null or DATALENGTH(binnum) = 0)");
				}
				else if(DBProduct.equals("ORACLE"))
				{
					invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "warehouse='"+invrectrans.getFromwarehouse()+"' and itemnum='"+invrectrans.getItemnum()+"' and binnum is null");
				}
		    	
	        }
	        else
	        {
	        	invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "warehouse='"+invrectrans.getFromwarehouse()+"' and itemnum='"+invrectrans.getItemnum()+"' and binnum ='"+invrectrans.getFrombin()+"'");
	        }
			
			if (invstocklist.size()>0){
				invstock = (Invstock) invstocklist.get(0);
				curbal = invstock.getCurbal();
			}*/
		Double quantity = invrectrans.getQuantity();
		
		if(!Util.isNotNull(tobin))
		{
			throw new Exception("原箱柜号和目标箱柜不能为空,请确认!");
		}
		if((quantity == null)||quantity<0)
		{
			invrectrans.setQuantity(null);
			this.refreshData();
			throw new Exception("转移数量为空或者为负值,请确认!");
		}
		if(quantity - curbal >0)
		{
			throw new Exception("转移数量大于原箱柜上存放余量，不能调整,请确认!");
		}

		if (!(invrectrans instanceof Invrectrans))
			throw new Exception("请选择一条记录!");
		invsrv.adjustbinnum(invrectrans);
		this.detach();
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
	 * 功能：调整平均成本
	 * 日期：Jun 18, 2008 8:04:00 AM
	 *
	 */
	public void rsetavgcost() 
	throws Exception
	{
		
		InventorySrv invsrv = (InventorySrv) IBOSrvUtil.getSrv("inventory");
		Invtrans  invtrans = (Invtrans) this.getMainObject();
		MainWindow inventorywnd =(MainWindow) this.getOwnerWnd();
		Double newcost = invtrans.getNewcost();
		if((newcost == null)||newcost<0)
		{
			invtrans.setQuantity(null);
			this.refreshData();
			throw new Exception("调整平均成本为空或者为负值,请确认!");
		}
		invsrv.rsetavgcost(invtrans);
		this.detach();
	}
}
