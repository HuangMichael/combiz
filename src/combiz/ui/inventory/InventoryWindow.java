package combiz.ui.inventory;

import java.util.Date;
import java.util.List;

import combiz.business.inventory.InventorySrv;
import combiz.business.inventory.InvstockSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tab;

public class InventoryWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InventoryWindow() {
		super();
	}

	/**
	 * 新增记录
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Inventory newobj = new Inventory();

		mainObject = newobj;
		newobj.setConversion(1.00);
		return true;
	}
	
	
	public void initData() throws Exception {
		// TODO 自动生成方法存根
		/*Tab list = (Tab) this.getFellow("invreservetab");
		list.setVisible(false);*/
		super.initData();
		
		
	}




	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：调整库存项目箱柜
	 * 日期：2008-4-14 下午01:35:23
	 *
	 */
	public void adjustbinnum() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行调整箱柜操作！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		
		Inventory inv = (Inventory)this.getMainObject();
		
		if (inv.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		
		Invrectrans irt = new Invrectrans();
		irt.setItemnum(inv.getItemnum());
		irt.setDescription(inv.getItemdesc());
		irt.setFromwarehouse(inv.getWarehouse());
		irt.setTowarehouse(inv.getWarehouse());
		irt.setRecunit(inv.getIssueunit());
		irt.setUnitcost(inv.getAvgcost());
		irt.setActualcost(inv.getAvgcost());
		irt.setOldavgcost(inv.getAvgcost());
		irt.setLinecost(0d);
		irt.setLoadedcost(0d);
		irt.setReqby(this.getUserInfo().getLabornum());
		irt.setExchangerate(1.0);
		irt.setRejectqty(0d);
	    irt.setConversion(1d);
	    irt.setRectype("TRANSFER");
	    irt.setStatus("待确认");
	    irt.setCurbal(0.0d);
	    irt.setChangeby(this.getUserInfo().getLabornum());
		irt.setTransdate(new Date());
		irt.setActualdate(new Date());
		//irt.setCorpnum(inv.getCorpnum());
		//irt.setSitenum(inv.getSitenum());
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog((Object)irt, "/inventory/invstockpopup.xul");
//		this.refreshChildData();
		this.refreshData();

	}
	public void issue() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择要发放的库存项目，然后再进行发放操作！");
			return;
		}
		
        Inventory inv = (Inventory)this.getMainObject();
		
        Invusetrans invuse = new Invusetrans();
		invuse.setItemnum(inv.getItemnum());
		invuse.setDescription(inv.getItemdesc());
		invuse.setWarehouse(inv.getWarehouse());
		invuse.setIssuetype("发放");
		invuse.setTransdate(new Date());
		invuse.setActualdate(new Date());
		List invstock = this.getMainSrv().getBaseDao().findWithQuery(
				Invstock.class,
				"itemnum = '" + inv.getItemnum()
						+ "' and warehouse = '" + inv.getWarehouse()
						+ "'");
		Invstock curbal = (Invstock) invstock.get(0);
		invuse.setCurbal(curbal.getCurbal());
	
		invuse.setConversion(1.0);
		invuse.setPhyscnt(0d);
		invuse.setEnterby(this.getLaborInfo().getLabornum());
		//invuse.setCorpnum(inv.getCorpnum());
		//invuse.setSitenum(inv.getSitenum());
		
	
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog((Object)invuse, "/inventory/invissuepopup.xul");
//		this.refreshChildData();
		this.refreshData();

}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：重订购库存项目
	 * 日期：Oct 24, 2008 12:25:44 PM
	 *
	 */
	public void autogenpo() throws Exception {
		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行重订购库存项目操作！");
			return;
		}
		Inventory inven = (Inventory) this.getMainObject();
		List list = this.getMainSrv().getBaseDao().findWithQuery(Inventory.class, "stocktype='常备库存' ");
		if (list.size()>0)
		{
			int count = ((InventorySrv)this.getMainSrv()).autogenerate(list);
			
			this.refreshData();
			Messagebox.show("已经成功重订购生成'"+count+"'行采购申请行");
		}
		else
		{
			Messagebox.show("没有满足生成重订购的库存记录！");
		}
			
		
		
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：调整物料余量
	 * 日期：Jun 17, 2008 2:55:33 PM
	 *
	 */
	public void editcurbal() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行调整余量操作！");
			return;
		}
		//判断语句
		/*Boolean flage = this.check();
		if (flage != true){
			throw new Exception("您没有权限操作该仓库！");
		}*/
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		
		Inventory inv = (Inventory)this.getMainObject();
		
		Invtrans invtrans = new Invtrans();
		
		//invtrans.setBinnum(inv.getBinnum());
		invtrans.setConversion(inv.getConversion());
		invtrans.setCurbal(0D);
		invtrans.setOldcost(inv.getAvgcost());
		invtrans.setNewcost(inv.getAvgcost());
		
		invtrans.setWarehouse(inv.getWarehouse());
		//invtrans.setCorpnum(inv.getCorpnum());
		invtrans.setEnterby(this.getLaborInfo().getLabornum());
		invtrans.setItemnum(inv.getItemnum());
		//invtrans.setSitenum(inv.getSitenum());
		//invtrans.setTransdate(new Date());
		invtrans.setTranstype("调整余量");
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog((Object)invtrans, "/inventory/invcurbalpopup.xul");
//		this.refreshChildData();
		this.refreshData();
	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：调整物料物理盘点
	 * 日期：Jun 17, 2008 2:55:33 PM
	 *
	 */
	public void editphyscnt() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行调整物理盘点操作！");
			return;
		}
		//判断语句
		/*Boolean flage = this.check();
		if (flage != true){
			throw new Exception("您没有权限操作该仓库！");
		}*/
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		
		Inventory inv = (Inventory)this.getMainObject();
		
		Invtrans invtrans = new Invtrans();
		
		//invtrans.setBinnum(inv.getBinnum());
		invtrans.setConversion(inv.getConversion());
		invtrans.setCurbal(0D);
		invtrans.setPhyscnt(0D);
		invtrans.setOldcost(inv.getAvgcost());
		invtrans.setNewcost(inv.getAvgcost());
		
		
		invtrans.setWarehouse(inv.getWarehouse());
		//invtrans.setCorpnum(inv.getCorpnum());
		invtrans.setEnterby(this.getLaborInfo().getLabornum());
		invtrans.setItemnum(inv.getItemnum());
		//invtrans.setSitenum(inv.getSitenum());
		invtrans.setTransdate(new Date());
		invtrans.setTranstype("调整物理盘点");
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog((Object)invtrans, "/inventory/invphyscntpopup.xul");
//		this.refreshChildData();
		this.refreshData();
	}
	
	/**
	 * 方法resetavgcost()
	 * 
	 * 作者：李建红
	 * 功能：调整物料平均成本
	 * 日期：Jun 18, 2008 7:37:23 AM
	 *
	 */
	public void resetavgcost() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行调整平均成本操作！");
			return;
		}
		
		Inventory inv = (Inventory)this.getMainObject();
		
		Invtrans invtrans = new Invtrans();
		
		invtrans.setBinnum(inv.getBinnum());
		invtrans.setConversion(inv.getConversion());
		invtrans.setCurbal(0D);
		invtrans.setOldcost(inv.getAvgcost());
		invtrans.setWarehouse(inv.getWarehouse());
		//invtrans.setCorpnum(inv.getCorpnum());
		invtrans.setEnterby(this.getLaborInfo().getLabornum());
		invtrans.setItemnum(inv.getItemnum());
		//invtrans.setSitenum(inv.getSitenum());
		invtrans.setTransdate(new Date());
		invtrans.setTranstype("调整平均成本");
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog((Object)invtrans, "/inventory/invavgcostpopup.xul");
//		this.refreshChildData();
		this.refreshData();
	}
}
