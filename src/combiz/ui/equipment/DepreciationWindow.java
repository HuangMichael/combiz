package combiz.ui.equipment;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.corp.DepartmentSrv;
import combiz.business.equipment.DepreciationSrv;
import combiz.business.equipment.EquipdepSrv;
import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;

import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

public class DepreciationWindow extends MainWindow implements InfWindow {
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public DepreciationWindow() {
		super();
	}

	/**
	 * 添加记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值 
		 **********************************************************************/
		Depreciation newobj = new Depreciation();
		// 请在下面添加对象的初始化值
		newobj.setDepnum(this.genAutokey("depnum"));
		newobj.setLabornum(this.getLaborInfo().getLabornum());
		newobj.setDepdate(new Date());
		mainObject = newobj;
		return true;
	}

	/**
	 * 生成折旧单明细 作者:陈明锐 日期:Mar 31, 2009
	 * 
	 * @throws Exception
	 */
	public void creatline() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行生成折旧单明细操作！");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("equipdep");
		List list = listWnd.getSelectObjects();// 子窗口中已经添加的
		EquipdepSrv equipdepSrv = (EquipdepSrv) IBOSrvUtil.getSrv("equipdep");
		equipdepSrv.creatList(list, this.mainObject);
		this.refreshData();
	}

	/**
	 * 对资产折旧 作者:陈明锐 日期:Mar 31, 2009
	 * 
	 * @throws Exception
	 */
	public void depasset() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行对资产折旧操作！");
			return;
		}
		Equipdep equipdep = new Equipdep();
		Depreciation dep = (Depreciation)this.getMainObject();
		equipdep.setDepcount(dep.getDepfaction());
		this.popupDialog(equipdep, "/equipment/depasset.xul");
		this.refreshData();
	}
}
