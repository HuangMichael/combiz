package combiz.ui.inventory;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.RejectSrv;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

public class EqrejectWindow extends MainWindow implements InfWindow {
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EqrejectWindow() {
		super();
	}

	/**
	 * 添加记录
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Reject newobj = new Reject();
		// 请在下面添加对象的初始化值
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,
				"labornum ='" + labornum + "'");
		Labor labor = (Labor) laborlist.get(0);
		String sitenum = labor.getSitenum();
		String rejectnum = this.genAutokey("rejectnum");
		newobj.setRejectnum(rejectnum);
		newobj.setReqdept(labor.getDeptnum());
		newobj.setRejectdate(new Date());
		newobj.setRequestby(labornum);
		newobj.setStatusdate(new Date());
		newobj.setRejecttype("资产报废");
		newobj.setStatus("草稿");
		mainObject = newobj;
		return true;
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：从台帐中选择满足条件的资产信息。 日期：1:53:43 PM Feb 13, 2009
	 * 
	 */
	public void listeq() throws Exception {
		Reject reject = (Reject) this.mainObject;
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请在选择待报废资产明细行前保存数据！");
			return;
		}
		if (reject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (reject.getStatus().equals("草稿")
				|| reject.getStatus().equals("审批不通过")) {
			this.popupDialog(this.getMainObject(),
					"/inventory/eqrejectpopup.xul");
		} else {
			throw new Exception("在状态为:'" + reject.getStatus() + "'下，不能添加报废申请行！");
		}

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：领导审批时候，选中某些资产不通过审批报废。 日期：4:43:32 PM Feb 14, 2009
	 * 
	 */
	public void noapprove() throws Exception {
		Reject reject = (Reject) this.mainObject;
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请在选择报废审批不通过前保存数据！");
			return;
		}

		if (reject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (reject.getStatus().equals("部门领导审批")
				|| reject.getStatus().equals("设备主管领导审批")
				|| reject.getStatus().equals("中心领导审批")) {
			ListWindow listWnd = (ListWindow) this.getFellow("rejectitem");
			List noappeqlist = listWnd.getSelectObjects();
			if (noappeqlist.size() > 0) {
				int rtn = Messagebox.show("确定选中的资产不通过报废审批？", "报废审批不通过！",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
				if (rtn == Messagebox.NO)
					return;
				else if (rtn == Messagebox.YES) {
					((RejectSrv) this.getMainSrv()).noapproveeq(noappeqlist);
					this.refreshData();
					Messagebox.show("已经将选中的资产记录设置为报废审批不通过!");
				}
			} else {
				throw new Exception("请在报废申请明细行中，选择本次不报废的资产记录！");
			}
		} else {
			throw new Exception("在状态为:'" + reject.getStatus()
					+ "'下，不能执行该操作，请核实！");
		}

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：领导审批时候，对设置为本次不报废的设备重新报废。 日期：4:43:32 PM Feb 14, 2009
	 * 
	 */
	public void noyesapprove() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请在选择修改为不报废前保存数据！");
			return;
		}

		Reject reject = (Reject) this.mainObject;
		if (reject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (reject.getStatus().equals("部门领导审批")
				|| reject.getStatus().equals("设备主管领导审批")
				|| reject.getStatus().equals("中心领导审批")) {
			ListWindow listWnd = (ListWindow) this.getFellow("rejectitem");
			List noappeqlist = listWnd.getSelectObjects();
			if (noappeqlist.size() > 0) {
				int rtn = Messagebox.show("确定选中的资产修改为报废状态？", "修改为报废！",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
				if (rtn == Messagebox.NO)
					return;
				else if (rtn == Messagebox.YES) {
					((RejectSrv) this.getMainSrv()).noyesapproveeq(noappeqlist);
					this.refreshData();
					Messagebox.show("已经将选中的资产记录中是否报废修改为‘是’!");
				}
			} else {
				throw new Exception("请在报废申请明细行中，选择本次修改为报废的记录！");
			}

		} else {
			throw new Exception("在状态为:'" + reject.getStatus()
					+ "'下，不能执行该操作，请核实！");
		}

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：领导审批时候，批准字段标识（是否报废）为'是'的所有资产。 日期：4:43:32 PM Feb 14, 2009
	 * 
	 */
	public void approverej() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请在选择同意报废前保存数据！");
			return;
		}

		Reject reject = (Reject) this.mainObject;

		if (reject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (reject.getStatus().equals("草稿")
				|| reject.getStatus().equals("报废完成")) {
			throw new Exception("报废单在状态为'草稿'或'报废完成'时，不能报废！");
		}
		// ListWindow listWnd = (ListWindow) this.getFellow("rejectitem");
		List appeqlist = this.getMainSrv().getBaseDao().findWithQuery(
				Rejectitem.class,
				"rejectnum='" + reject.getRejectnum() + "' and isreject ='是'");
		if (appeqlist.size() > 0) {
			int rtn = Messagebox.show("确定将所有要报废的资产进行报废？", "同意报废！",
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
			if (rtn == Messagebox.NO)
				return;
			else if (rtn == Messagebox.YES) {
				((RejectSrv) this.getMainSrv()).approverej(appeqlist, reject);
				this.refreshData();
				Messagebox.show("已经将所有要报废的资产进行报废!");
			}
		} else {
			throw new Exception("报废申请明细行中，没有要报废的资产记录，请确认！");
		}

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：进行报废批准，将报废行中的报废明细转移到报废库里。 日期：Oct 21, 2008 11:52:54 AM
	 * 
	 */
	public void verify() throws Exception {

		Reject reject = (Reject) this.mainObject;

		if (reject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Rejectitem.class,
				"rejectnum = '" + reject.getRejectnum() + "' ");
		if (retList.size() == 0) {
			Messagebox.show("没有要批准的报废记录!");
		} else {
			((RejectSrv) this.getMainSrv()).verify(retList);
			this.refreshData();
			Messagebox.show("已经完成报废!");

		}
	}

}
