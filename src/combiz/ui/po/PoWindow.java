package combiz.ui.po;

import combiz.business.po.PoSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class PoWindow extends MainWindow implements InfWindow {

	public PoWindow() {
		super();
	}

	/**
	 * 
	 * @TODO 新增一条记录
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午01:17:05
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Po po = new Po();
		// int count = this.mainSrv.getRowCountByWhere(po, "");
		po.setPonum(this.genAutokey("ponum"));
		po.setStatus("流程未启动");
		po.setStatusdate(new Date());
		po.setTotalcost(0d);
		po.setReceipts("未接收");
		mainObject = po;
		return true;
	}

	/*
	 * 功能：设置之窗口是否为只读 作者：李建红 日期：2008-11-7下午03:47:32
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Po po = (Po) this.mainObject;
		ListWindow polineWnd = (ListWindow) this.getFellow("poline");
		if (po.getStatus() != null && po.getStatus().equals("已批准")) {
			polineWnd.setReadonlyList(true);
		} else {
			polineWnd.setReadonlyList(false);
		}
		super.initData();
	}

	/**
	 * 
	 * @TODO 拷贝采购单申请行。弹出一个窗体，用户可以选择其中的部分已有数据。
	 * @throws Exception
	 * @蒋祖兵 2007-8-24 下午04:20:44
	 */
	public void copypr() throws Exception {
		Po po = (Po) this.mainObject;
		if (po.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行复制采购申请行操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("拷贝采购申请行操作前，请先保存数据！");
			return;
		}
		if (po.getStatus().equals("已批准"))
			Messagebox.show("该采购单已经批准，不能进行拷贝采购申请行操作");
		else {

			CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
					.getMainObject(), "/pr/copyprlinepopup.xul");
			if (listWnd == null)
				return;

			// 判断是否点击了确定按钮，还是取消按钮
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			((PoSrv) this.getMainSrv()).copyprline(retList, po);
			Messagebox.show("成功拷贝采购申请行");
			this.refreshData();
		}
	}

	/**
	 * @author 李阳 功能：复制采购单，将选中的采购单进行复制；
	 * @throws Exception
	 *             2008-1-22下午01:10:31
	 */
	public void copypo() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行复制操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("采购单复制操作前，请先保存数据！");
			return;
		}
		Po po = (Po) this.getMainObject();
		if (po.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Prline.class, "prnum = '" + po.getPonum() + "'");
		Po newpo = ((PoSrv) this.getMainSrv()).copypo(po);
		this.setMainObject(newpo);
		Messagebox.show("已成功将采购单:" + po.getPonum() + "复制到采购单"
				+ newpo.getPonum());
		this.refreshData();
	}

	/**
	 * 陈明锐 根据设备选择备件 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		// Po po = (Po) this.getMainObject();
		// if (!po.getStatus().equals("已批准")) {
		Workorder workorder = new Workorder();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
				"/common/pofindpartbyequip.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		this.refreshData();
		// } else {
		// Messagebox.show("流程已批准无法添加采购行");
		// }
	}

	/**
	 * 根据物资编码添加BOM备件信息
	 * 
	 * 作者:陈明锐 日期:Mar 2, 2009
	 * 
	 * @throws Exception
	 */
	public void findeqbom() throws Exception {
		// Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("已批准")) {
		Wpmaterial wpmaterial = new Wpmaterial();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(wpmaterial,
				"/common/pofindbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow) this.getFellow("poline");
		listWnd.refreshData();// 刷新部分
		// }else{
		// Messagebox.show("流程已批准无法添加备件");
		// }
	}

	/* workflow
	 * 发送工作流的时候，校验仓库，数量，单价信息
	 * 李建红
	 */
	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Po po = (Po)this.getMainObject();
		String ponum = po.getPonum();
		boolean flasg = false;
		List polinelist = this.getMainSrv().getBaseDao().findWithQuery(Poline.class, "ponum = '"+ponum+"'");
		if (polinelist.size()>0) {
			for (int i=0;i<polinelist.size();i++) {
				Poline poline = (Poline) polinelist.get(i);
				String warehouse = poline.getWarehouse();
				String itemnum = poline.getItemnum();
				Double qty = poline.getOrderqty();
				Double unitcost = poline.getUnitcost();
				if (warehouse == null || itemnum == null || qty == null || unitcost==null || qty<=0 || unitcost<=0){
					flasg = true;	
				}
			}
			if (flasg) {
				Messagebox.show("采购订单行订购数量，含税单价，仓库信息不完善，\n不能发送工作流，请核实！");
				return;
			}
		}else{
			Messagebox.show("该采购订单未任何采购单行，不能发送工作流，请核实！");
			return;
		}
		super.workflow();
	}

}
