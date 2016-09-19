package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.business.inventory.ItemSrv;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.ICombobox;

public class ItemWindow extends MainWindow implements InfWindow {
	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ItemWindow() {
		super();
	}

	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Item item = (Item) this.getMainObject();
		ICombobox combox = (ICombobox) this.getFellow("item.lottype");
		String rota = item.getRotating();
		if (rota != null) {
			if (item.getRotating().equals("是")) {
				this.setReadonly(combox);
			} else {
				this.setNoReadonly(combox);
			}
		}

		super.initData();
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
		Item newobj = new Item();
		newobj.setItemnum(this.genAutokey("itemnum"));
		newobj.setRotating("否");
		newobj.setLottype("非批次");
		newobj.setOutside("否");
		newobj.setSpareautoadd("否");
		newobj.setInspectreq("否");

		mainObject = newobj;
		return true;
	}

	/**
	 * 方法createtoinventory()
	 * 
	 * 作者：李建红 功能：从物资编码直接生成到库存表中 日期：Sep 23, 2008 2:15:41 PM
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void createtoinventory() throws Exception {
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("生成操作前，请先保存当前的记录！");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行生成操作！");
			return;
		}
		Item item = (Item) this.getMainObject();
		// 弹出指定的窗口
		Inventory inventory = new Inventory();
		inventory.setItemnum(item.getItemnum());
		inventory.setOrderunit(item.getOrderunit());
		inventory.setIssueunit(item.getIssueunit());
		inventory.setModelnum(item.getModelnum());
		inventory.setStocktype("常备库存");
		inventory.setConversion(1D);
		inventory.setIssueytd(0D);

		//inventory.setSitenum(this.getLaborInfo().getSitenum());
		//inventory.setCorpnum(this.getLaborInfo().getCorpnum());

		this.popupDialog((Object) inventory, "/inventory/inventorypopup.xul");

		this.refreshData();

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：维护计量单位 日期：2008-4-14 上午08:27:14
	 * 
	 */
	public void measure() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this
				.popupDialog(this.getMainObject(),
						"/inventory/measureunit.xul", ""); // "tablename='IBSAPPS'"

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：定义库存项目的转换系数 日期：Oct 15, 2008 2:07:53 PM
	 * 
	 */
	public void conversion() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog(this.getMainObject(), "/inventory/conversion.xul", "");

	}

	/**
	 * 方法：createeqnum 作者：李建红 功能：部件bom生成 日期：Feb 24, 2009 3:31:46 PM
	 */
	public void createeqnum() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行生成操作！");
			return;
		}
		int rtn = Messagebox.show("是否确定生成备件清单吗？", "确认生成！", Messagebox.YES
				| Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			Item item = (Item) this.getMainObject();
			if (!(item.getRotating().equals("是"))) {
				throw new Exception("非周转件，不能生成部件！");
			}
			// 查询是否有备件
			/*
			 * List itemeqbomlist =
			 * this.getMainSrv().getBaseDao().findWithQuery( Itemeqbom.class,
			 * "parent='" + item.getItemnum() + "'"); if (itemeqbomlist.size() <=
			 * 0) { Messagebox.show("该物料无物料清单，不能生成备件清单！"); return; }
			 */
			ItemSrv itemsrv = (ItemSrv) this.getMainSrv();
			itemsrv.createeqnum(item);
			Messagebox.show("备件BOM生成完成！");
		}
	}

	/**
	 * 方法
	 * 
	 * 作者：李建红 功能：在修改物资为周转件的时候验证库存必须为零
	 * 
	 */
	
	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行保存操作！");
			return;
		}
		Item item = (Item) this.getMainObject();//得到界面上的对象
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+item.getItemnum()+"'");
		Item olditem =(Item) itemlist.get(0);
		if (item.getId() != null) {//说明不是新建记录
			if (!(item.getRotating().equals(olditem.getRotating()))) {
				if ("是".equals(item.getRotating())) {
					int rtn = Messagebox.show("是否确定修改为周转件吗？", "确认修改！", Messagebox.YES
							| Messagebox.NO, Messagebox.QUESTION);
					if (rtn == Messagebox.NO)
						return;
					else if (rtn == Messagebox.YES) {
						//得到库存是否有余量，有，则不能修改
						List invlist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+item.getItemnum()+"'");
						/*Double culbar = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(i.culbar) from Invstock i where i.itemnum='"+item.getItemnum()+"'");
						if (culbar!=null && culbar>0) {
							Messagebox.show("物资库存余量不为零，不能修改成周转件！");
							return;
						}*/
						if (invlist.size()>0) {
							Messagebox.show("物资库存余量不为零，不能修改成周转件！");
							return;
						}
						}
				}
			}
			
		}
		
		super.save();
	}
	
	public void intowarehouse() throws Exception {
		this.popupDialog(this.getMainObject(), "/test/warehousedialog.xul", "");
	}
}
