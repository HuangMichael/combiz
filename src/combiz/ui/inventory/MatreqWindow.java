package combiz.ui.inventory;

import combiz.business.inventory.MatreqSrv;
import combiz.domain.corp.Department;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.tool.Toolreserve;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workorder.Wptool;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class MatreqWindow extends MainWindow implements InfWindow {
	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public MatreqWindow() {
		super();
	}
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：解密
	 * 日期：1:34:07 PM  Jul 5, 2010 
	 *
	 */
	public String passwd() throws Exception{
		Messagebox.show(Util.unPasswordString("___0021003900790077005500a4004a006d0053"));
		return Util.unPasswordString("___0021003900790077005500a4004a006d0053");
	}

	public void save() throws Exception {
		/*Matreq mr = (Matreq) this.getMainObject();
		String usedept = mr.getUsedept();
		if (Util.isNotNull(usedept)) {
			String dept[] = usedept.split("、");
			int length = dept.length - 1;
			for (int i = 0; i < dept.length; i++) {
				String deptnum = dept[i];
				List list = this.getMainSrv().getBaseDao().findWithQuery(
						Department.class, "deptnum = '" + deptnum + "'");
				if (list.size() > 0) {
					Department department = (Department) list.get(0);
					String departnum = department.getDeptnum();
					String supervisor = department.getSupervisor();
					if (Util.isNull(supervisor)) {
						Messagebox.show("部门为'" + departnum + "'的负责人为空，请核实！");
						return;
					}

					int ibscount = this.getMainSrv().getBaseDao()
							.selectCountByHql(
									"select count(*)  from Ibsusers t where t.labornum = '"
											+ supervisor + "'");
					if (ibscount == 0) {
						Messagebox.show("人员'" + supervisor + "'对应的用户不存在,请核实！");
						return;
					}
				}
			}
		}*/
		super.save();
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
		Matreq newobj = new Matreq();
		String matreqnum = this.genAutokey("matreqnum");
		newobj.setMatreqnum(matreqnum);
		newobj.setRequestdate(new Date());
		newobj.setStatus("流程未启动");
		newobj.setStatusdate(new Date());
		newobj.setReqtype("物资领用申请");
		newobj.setReqdept(this.getLaborInfo().getDeptnum());
		newobj.setRequestby(this.getUserInfo().getLabornum());
		newobj.setRequireddate(new Date());
		newobj.setUsedept(this.getLaborInfo().getDeptnum());

		mainObject = newobj;
		return true;
	}

	/** ****************生成预留信息*************ljh************** */
	public void createinvr() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请在生成预留前保存数据！");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();
		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		/**
		 * 判断是否是重复生成预留，是否需要生成预留 一个工单只能生成一次预留，判断状态必需为批准状态的才能生成预留
		 */
		String matreqnum = matreq.getMatreqnum();
		String status = matreq.getStatus();
		if (!(status.equals("已批准"))) {
			Messagebox.show("该领用申请未被批准，不能生成预留！");
			return;
		}
		List toolreservelist = this.getMainSrv().getBaseDao().findWithQuery(
				Toolreserve.class, "reqnum = '" + matreqnum + "'");
		if (toolreservelist.size() > 0) {
			Messagebox.show("该申请单已经生成预留，不能重复生成！");
			return;
		}

		List invreservelist = this.getMainSrv().getBaseDao().findWithQuery(
				Invreserve.class, "reqnum = '" + matreqnum + "'");
		if (invreservelist.size() > 0) {
			Messagebox.show("该申请单已经生成预留，不能重复生成！");
			return;
		}

		((MatreqSrv) this.getMainSrv()).createinvr(matreq);
		//Messagebox.show("预留生成完成！");
		this.refreshData();
	}

	/*
	 * 功能：设置子窗口只读 作者：李建红 日期：2008-11-28上午10:56:15
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getMainObject();
		ListWindow listwnd = (ListWindow) this.getFellow("wpmaterial");
		if (matreq.getStatus() != null && matreq.getStatus().equals("已批准")) {
			listwnd.setReadonlyList(true);
		} else {
			listwnd.setReadonlyList(false);
		}
		super.initData();
	}

	/**
	 * 陈明锐 根据设备选择备件 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		// Matreq matreq = (Matreq) this.getMainObject();
		// if (!matreq.getStatus().equals("已批准")) {
		Workorder workorder = new Workorder();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
				"/common/matreqfindpartbyequip.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		this.refreshData();
		// }else{
		// Messagebox.show("流程已批准无法添加领用行");
		// }
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：对资产领用申请里提出的记录生成ITEM记录。 日期：3:55:29 PM Dec 26, 2008
	 * 
	 */
	public void createitem() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请在领用申请界面进行操作！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请先保存数据！");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("wpmaterial");
		List selectlist = listWnd.getSelectObjects();
		if (selectlist.size() == 0) {
			throw new Exception("请选择要生成资产编码的记录！");
		}
		int count = ((MatreqSrv) this.getMainSrv()).createitem(selectlist);
		this.refreshData();
		Messagebox.show("成功生成'" + count + "'条资产编码记录！");
	}

	/*
	 * 功能：发送工作流的时候，如果mainsend为空，让用户确认一下。 作者：李阳 日期：Jan 17, 2009 12:24:41 PM
	 */
	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getMainObject();
		String mainsend = matreq.getSupervisor();
		boolean flag = false;
		ListWindow listWnd = (ListWindow) this.getFellow("wpmaterial");
		List selectlist = listWnd.getSelectObjects();
		if (selectlist.size()>=0) {
			for (int i=0;i<selectlist.size();i++) {
				Wpmaterial wpmaterial = (Wpmaterial)selectlist.get(i);
				if (wpmaterial.getItemqty() == null || wpmaterial.getItemqty()<=0) {
					flag = true;
				}
			}
		}else{
			Messagebox.show("未添加领用申请行，不能发送工作流，请核实！");
			return;
		}
		if (flag) {
			Messagebox.show("领用申请行物资数量不能小于0，不能发送工作流，请核实！");
			return;
		}
		if (matreq.getStatus().equals("设备主管领导审批")) {
			if (Util.isNull(mainsend)) {
				Boolean tosend = null;
				if (Messagebox.show("主送人为空，您确定发送工作流吗？", "提示！！！", Messagebox.YES
						| Messagebox.NO, "") == Messagebox.YES)
					tosend = true;
				else
					tosend = false;
				if (!tosend) {
					return;
				} else {
					super.workflow();
				}
			} else {
				super.workflow();
			}

		} else {
			super.workflow();
		}
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
				"/common/matreqfindbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow) this.getFellow("wpmaterial");
		listWnd.refreshData();// 刷新部分
		// }else{
		// Messagebox.show("流程已批准无法添加备件");
		// }
	}

}
