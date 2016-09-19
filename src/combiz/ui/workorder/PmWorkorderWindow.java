package combiz.ui.workorder;

import java.util.Date;
import java.util.List;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Invreserve;
import combiz.domain.stdplan.Jobplan;
import combiz.domain.tool.Toolreserve;
import combiz.domain.user.Ibsgroupusers;
import combiz.domain.user.Ibsusers;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workorder.Wptool;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.util.Util;

import com.inbasis.zul.Messagebox;

public class PmWorkorderWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public PmWorkorderWindow() {
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
		Workorder newobj = new Workorder();
		Labor labor = (Labor) this.getLaborInfo();
		String project = labor.getDefaultstoreloc();
		String wonum = "PM" + this.genAutokey("wonum");
		newobj.setWonum(wonum);
		newobj.setStatus("流程未启动");
		newobj.setEstlabhrs(new Double(0.0));
		newobj.setEstlabcost(new Double(0.0));
		newobj.setEstmatcost(new Double(0.0));
		newobj.setEsttoolcost(new Double(0.0));
		newobj.setEsttoolcost(new Double(0.0));
		newobj.setActlabhrs(new Double(0.0));
		newobj.setActmatcost(new Double(0.0));
		newobj.setActlabcost(new Double(0.0));
		newobj.setActtoolcost(new Double(0.0));
		newobj.setEstatapprlabcost(new Double(0.0));
		newobj.setEstatapprlabhrs(new Double(0.0));
		newobj.setEstatapprmatcost(new Double(0.0));
		newobj.setEstatapprtoolcost(new Double(0.0));
		newobj.setStatusdate(new Date());
		newobj.setWorktype("PM");
		newobj.setEqdown("否");
		newobj.setHaschildren("否");
		newobj.setHasfollowupwork("否");
		newobj.setEstdur(0L);
		newobj.setReportedby(labor.getLabornum());
		newobj.setReportdate(new Date());
		newobj.setProject(project);
		mainObject = newobj;
		return true;
	}

	@Override
	public void save() throws Exception {
		Workorder workorder = (Workorder) this.getMainObject();
		String eqnum = workorder.getEqnum();
		String location = workorder.getLocation();
		String wonum = workorder.getWonum();
		if (!Util.isNotNull(eqnum) && !Util.isNotNull(location)) {
			Messagebox.show("位置和设备编号不能同时为空，请补充信息");
			return;
		}
		/** ******************判断界面上结束时间是否早于开始时间******************** */
		Date planbegindate = null;
		Date planenddate = null;
		if(null != workorder.getSchedstart() && null != workorder.getSchedfinish()){
			planbegindate = workorder.getSchedstart();
			planenddate = workorder.getSchedfinish();
			if(planbegindate.after(planenddate)){
				Messagebox.show("计划完成时间不能早于计划开始时间");
				return;
			}
		}
		Date begindate = null;
		Date enddate = null;
		if(null != workorder.getActstart() && null != workorder.getActfinish()){
			begindate = workorder.getActstart();
			enddate = workorder.getActfinish();
			if(begindate.after(enddate)){
				Messagebox.show("实际完成时间不能早于实际开始时间");
				return;
			}
		}
		Date aimbegindate = null;
		Date aimenddate = null;
		if(null != workorder.getTargstart() && null != workorder.getTargcomp()){
			aimbegindate = workorder.getTargstart();
			aimenddate = workorder.getTargcomp();
			if(aimbegindate.after(aimenddate)){
				Messagebox.show("目标结束时间不能早于目标开始时间");
				return;
			}
		}
		
		/** ******************判断修改人是否和报告人是否是同一个部门******************** */
		String modifylabornum = this.getUserInfo().getLabornum();
		String enterbylabornum = workorder.getReportedby();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,
				"labornum ='" + enterbylabornum + "'");
		Ibsusers ibsusers = (Ibsusers) this.getDesktop().getSession()
				.getAttribute("userinfo");// 获取当前登录用户。
		Labor labor = (Labor) this.getDesktop().getSession().getAttribute(
				"laborinfo");// 获取当前登录对应的人员。
		String userid = ibsusers.getUserid();

		List ibsuserlist = this.mainSrv.getBaseDao().findWithQuery(
				Ibsgroupusers.class, "userid  = '" + userid + "'");
		String grpname = null;
		String project = labor.getDefaultstoreloc();
		if (ibsuserlist.size() > 0) {
			Ibsgroupusers ibsgroupusers = (Ibsgroupusers) ibsuserlist.get(0);
			grpname = ibsgroupusers.getGrpname();
		}
		if (!Util.isNotNull(project)) {
			project = "Admin";
		}
		int allsave = (int) this.getMainSrv().getBaseDao().selectCountByHql(
				"select count(*) from Labor t where t.labornum = '"
						+ modifylabornum + "' and t.defaultstoreloc ='"
						+ project + "'");
		if (allsave == 0 && !grpname.equals("ADMIN")) {
			Messagebox.show("该工单编制人和您不在同一个项目部，不能修改该工单");
			return;
		}
		workorder.setChangeby(this.getUserInfo().getLabornum());
	/********************修改workorder表里的ESTMATCOST*********************/
		
		Double estmatcost = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.linecost) from Wpmaterial t where t.wonum = '"+ wonum +"'");
		if(estmatcost == null )
		{
			estmatcost =0d;
		}
		workorder.setEstmatcost(estmatcost);
		super.save();
	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：将工单里的标准作业计划添加到标准作业计划模板里。
	 * 日期：2008-4-15 下午04:58:45
	 *
	 */
	public void createjbplan() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行创建标准作业计划操作！");
			return;
		}
	    Workorder mainObject = (Workorder)this.getMainObject();
	    if (mainObject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		Jobplan jobplan = new Jobplan();
		jobplan.setJpnum(null);
		jobplan.setDescription(null);
		jobplan.setJpduration(0d);		
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog((Object)jobplan, "/workorder/createjobplanpopup.xul");

	}

	/******************创建故障代码****************************/
	public void createfailcode() throws Exception
	{
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("请在创建故障代码前保存数据！");
			return;
		}
		Workorder workorder = (Workorder) this.getMainObject();
		if (workorder.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		((WorkorderSrv)this.getMainSrv()).createfailcode(workorder);
		this.refreshData();
	}

	/**
	 * 
	 * @author:yuanjq
	 * @throws Exception
	 * @description:删除标准作业计划及相关信息 @ 2007-7-6 上午10:21:06
	 */
	public void deljbplan() throws Exception {
		int rtn = Messagebox.show("是否确定删除当前工单的标准作业计划及所有关联的子记录吗？", "删除确认！",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			((WorkorderSrv) this.getMainSrv()).deljbplan(this.mainObject);
			this.refreshData();
		}
		setMainData();
	}
	
	public void copywo() throws Exception{
		int rtn = Messagebox.show("是否确定复制工单？","复制！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			
			if(this.selectedTabid.equals("list"))
			{
				Messagebox.show("请选择一条主记录，然后再进行复制操作！");
				return;
			}
			if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
			{
				Messagebox.show("采购单复制操作前，请先保存数据！");
				return;
			}
			Workorder wo = (Workorder) this.getMainObject();
			if (wo.getId()==null) {
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			Workorder newWO = ((WorkorderSrv)this.getMainSrv()).copywo(wo);
			this.setMainObject(newWO);
			Messagebox.show("已成功将工单:" + wo.getWonum() + "复制到工单" + newWO.getWonum());
			this.refreshData();		
		}	
	}
	
	/** ****************生成预留信息*************ljh************** */
	public void createinvr() throws Exception
	{
		int rtn = Messagebox.show("是否确定生成预留信息？","生成！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			if (this.selectedTabid.equals("list"))
			{
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
			{
				Messagebox.show("请在生成预留前保存数据！");
				return;
			}
			Workorder workorder = (Workorder) this.getMainObject();
			if (workorder.getId()==null) {
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			/**
			 *判断是否是重复生成预留，是否需要生成预留
			 * 一个工单只能生成一次预留，判断状态必需为批准状态的才能生成预留
			 */
			String wonum = workorder.getWonum();
			String status = workorder.getStatus();
			if (!status.equals("已批准")) {
				throw new Exception ("工单未被批准，不能生成预留！");
			}
			List wpmateriallist = this.getMainSrv().getBaseDao().findWithQuery(Wpmaterial.class, "wonum = '"+wonum+"'");
			List wptoollist = this.getMainSrv().getBaseDao().findWithQuery(Wptool.class, "wonum='"+wonum+"'");
			int wpmaterialcount = wpmateriallist.size();
			int wptoolcount = wptoollist.size();
			if (!((wpmaterialcount+wptoolcount)>0)) {
				throw new Exception ("该工单没有可生成的预留，请核实！");
			}
			
			List toolreservelist = this.getMainSrv().getBaseDao().findWithQuery(Toolreserve.class, "reqnum = '"+wonum+"'");
			if (toolreservelist.size()>0) {
				throw new Exception ("工单已经生成预留，不能重复生成！");
			}
			
			List invreservelist = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+wonum+"'");
			if (invreservelist.size()>0) {
				throw new Exception ("工单已经生成预留，不能重复生成！");
			}
			
			((WorkorderSrv)this.getMainSrv()).createinvr(workorder);
			this.refreshData();
		}
	}

	/** ****************查看预留信息*************ljh*************** */
	public void selectinvr() throws Exception {
		Workorder wo = (Workorder) this.mainObject;
		if (wo.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("请在生成预留前保存数据！");
			return;
		}

		this.popupDialog(this.getMainObject(),
				"/inventory/invreservepopup.xul", "wonum='" + wo.getWonum()
						+ "'");

	}
	/* 
	 * 功能：设置子窗口只读
	 * 作者：李建红
	 * 日期：Oct 30, 200811:03:07 AM
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Workorder workorder = (Workorder)this.getMainObject();
		ListWindow tasklistwnd =(ListWindow) this.getFellow("wptaskTable");
		ListWindow materiallistwnd =(ListWindow) this.getFellow("wpmaterialTable");
		ListWindow toollistwnd =(ListWindow) this.getFellow("wptoolTable");
		ListWindow laborlistwnd =(ListWindow) this.getFellow("wplaborTable");
		ListWindow vendorlistwnd =(ListWindow) this.getFellow("wpvendorTable");
		ListWindow hazardlistwnd =(ListWindow) this.getFellow("wohazardTable");
		ListWindow hazpreclistwnd =(ListWindow) this.getFellow("wohazprecTable");
		ListWindow isotaglistwnd =(ListWindow) this.getFellow("woisotagTable");
		ListWindow isolockoutlistwnd =(ListWindow) this.getFellow("woisolockoutTable");
		ListWindow failclasslistwnd =(ListWindow) this.getFellow("wofailclass");
		ListWindow failcodelistwnd =(ListWindow) this.getFellow("wofailcode");
		ListWindow failproblemlistwnd =(ListWindow) this.getFellow("wofailproblem");
		ListWindow failcauselistwnd =(ListWindow) this.getFellow("wofailcause");
		ListWindow documentlistwnd =(ListWindow) this.getFellow("document");
		ListWindow docversionlistwnd =(ListWindow) this.getFellow("docversion");
		ListWindow docauthlistwnd =(ListWindow) this.getFellow("docauth");
		//ListWindow kpichartlistwnd =(ListWindow) this.getFellow("kpichartwnd");
		if (workorder.getStatus() != null && workorder.getStatus().equals("已批准")) {
			tasklistwnd.setReadonlyList(true);
			materiallistwnd.setReadonlyList(true);
			toollistwnd.setReadonlyList(true);
			laborlistwnd.setReadonlyList(true);
			vendorlistwnd.setReadonlyList(true);
			hazardlistwnd.setReadonlyList(true);
			hazpreclistwnd.setReadonlyList(true);
			isotaglistwnd.setReadonlyList(true);
			isolockoutlistwnd.setReadonlyList(true);
			failclasslistwnd.setReadonlyList(true);
			failcodelistwnd.setReadonlyList(true);
			failproblemlistwnd.setReadonlyList(true);
			failcauselistwnd.setReadonlyList(true);
			documentlistwnd.setReadonlyList(true);
			docversionlistwnd.setReadonlyList(true);
			docauthlistwnd.setReadonlyList(true);
			//kpichartlistwnd.setReadonlyList(true);
		}else{
			tasklistwnd.setReadonlyList(false);
			materiallistwnd.setReadonlyList(false);
			toollistwnd.setReadonlyList(false);
			laborlistwnd.setReadonlyList(false);
			vendorlistwnd.setReadonlyList(false);
			hazardlistwnd.setReadonlyList(false);
			hazpreclistwnd.setReadonlyList(false);
			isotaglistwnd.setReadonlyList(false);
			isolockoutlistwnd.setReadonlyList(false);
			failclasslistwnd.setReadonlyList(false);
			failcodelistwnd.setReadonlyList(false);
			failproblemlistwnd.setReadonlyList(false);
			failcauselistwnd.setReadonlyList(false);
			documentlistwnd.setReadonlyList(false);
			docversionlistwnd.setReadonlyList(false);
			docauthlistwnd.setReadonlyList(false);
			//kpichartlistwnd.setReadonlyList(false);
		}
		super.initData();
	}
	/**
	 * 根据物资编码添加BOM备件信息
	 * 
	 * 作者:陈明锐
	 * 日期:Mar 2, 2009
	 * @throws Exception
	 */
	public void findeqbom() throws Exception {
		//Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("已批准")) {
		Wpmaterial wpmaterial = new Wpmaterial();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(wpmaterial,"/common/findbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow)this.getFellow("wpmaterialTable");
		listWnd.refreshData();//刷新部分
		// }else{
		// Messagebox.show("流程已批准无法添加备件");
		// }
	}

}
