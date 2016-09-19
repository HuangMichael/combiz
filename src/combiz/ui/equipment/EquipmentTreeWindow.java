package combiz.ui.equipment;

import combiz.domain.equipment.Eqdowntime;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainTreeListWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;

import com.inbasis.zul.Messagebox;

public class EquipmentTreeWindow extends MainTreeListWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EquipmentTreeWindow() {
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
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		Equipment parentEq = (Equipment) this.getSelectObject();
		if(parentEq!=null)
		{
			newobj.setParent(parentEq.getEqnum());
			newobj.setLocation(parentEq.getLocation());
		}
		newobj.setInvcost(0.0D);
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		newobj.setIsrunning("是");

		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setChangedate(new Date());

		mainObject = newobj;
		return true;
	}

	/**
	 * @throws Exception
	 * @author:高群凯
	 * @description:设备移动 @ 2007-8-7 上午11:41:49
	 */
	public void eqmove() throws Exception 
	{
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行设备移动操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("设备移动操作前，请先保存数据！");
			return;
		}

		Equipment mainObject = (Equipment) this.getMainObject();

		Eqtrans eqtrans = new Eqtrans();
		eqtrans.setEqnum(mainObject.getEqnum());
		eqtrans.setFromloc(mainObject.getLocation());

		this.popupDialog((Object) eqtrans, "/equipment/eqmove.xul");

		// 重新刷新数据
		this.refreshData();
	}

	
	/**
	 * 设备停机
	 * 作者:陈明锐
	 * 日期:Apr 2, 2009
	 * @throws Exception
	 */
	public void eqdowntime() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录!");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.objStatus == this.ADDED) {
			Messagebox.show("请先保存数据");
			return;
		}
		Eqdowntime eqdowntime = new Eqdowntime();
		this.popupDialog(eqdowntime, "/equipment/eqdowntimelist.xul");
	}
}
