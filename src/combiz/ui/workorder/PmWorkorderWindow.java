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

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public PmWorkorderWindow() {
		super();
	}

	/**
	 * ������¼
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Workorder newobj = new Workorder();
		Labor labor = (Labor) this.getLaborInfo();
		String project = labor.getDefaultstoreloc();
		String wonum = "PM" + this.genAutokey("wonum");
		newobj.setWonum(wonum);
		newobj.setStatus("����δ����");
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
		newobj.setEqdown("��");
		newobj.setHaschildren("��");
		newobj.setHasfollowupwork("��");
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
			Messagebox.show("λ�ú��豸��Ų���ͬʱΪ�գ��벹����Ϣ");
			return;
		}
		/** ******************�жϽ����Ͻ���ʱ���Ƿ����ڿ�ʼʱ��******************** */
		Date planbegindate = null;
		Date planenddate = null;
		if(null != workorder.getSchedstart() && null != workorder.getSchedfinish()){
			planbegindate = workorder.getSchedstart();
			planenddate = workorder.getSchedfinish();
			if(planbegindate.after(planenddate)){
				Messagebox.show("�ƻ����ʱ�䲻�����ڼƻ���ʼʱ��");
				return;
			}
		}
		Date begindate = null;
		Date enddate = null;
		if(null != workorder.getActstart() && null != workorder.getActfinish()){
			begindate = workorder.getActstart();
			enddate = workorder.getActfinish();
			if(begindate.after(enddate)){
				Messagebox.show("ʵ�����ʱ�䲻������ʵ�ʿ�ʼʱ��");
				return;
			}
		}
		Date aimbegindate = null;
		Date aimenddate = null;
		if(null != workorder.getTargstart() && null != workorder.getTargcomp()){
			aimbegindate = workorder.getTargstart();
			aimenddate = workorder.getTargcomp();
			if(aimbegindate.after(aimenddate)){
				Messagebox.show("Ŀ�����ʱ�䲻������Ŀ�꿪ʼʱ��");
				return;
			}
		}
		
		/** ******************�ж��޸����Ƿ�ͱ������Ƿ���ͬһ������******************** */
		String modifylabornum = this.getUserInfo().getLabornum();
		String enterbylabornum = workorder.getReportedby();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,
				"labornum ='" + enterbylabornum + "'");
		Ibsusers ibsusers = (Ibsusers) this.getDesktop().getSession()
				.getAttribute("userinfo");// ��ȡ��ǰ��¼�û���
		Labor labor = (Labor) this.getDesktop().getSession().getAttribute(
				"laborinfo");// ��ȡ��ǰ��¼��Ӧ����Ա��
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
			Messagebox.show("�ù��������˺�������ͬһ����Ŀ���������޸ĸù���");
			return;
		}
		workorder.setChangeby(this.getUserInfo().getLabornum());
	/********************�޸�workorder�����ESTMATCOST*********************/
		
		Double estmatcost = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.linecost) from Wpmaterial t where t.wonum = '"+ wonum +"'");
		if(estmatcost == null )
		{
			estmatcost =0d;
		}
		workorder.setEstmatcost(estmatcost);
		super.save();
	}
	
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���������ı�׼��ҵ�ƻ���ӵ���׼��ҵ�ƻ�ģ���
	 * ���ڣ�2008-4-15 ����04:58:45
	 *
	 */
	public void createjbplan() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��д�����׼��ҵ�ƻ�������");
			return;
		}
	    Workorder mainObject = (Workorder)this.getMainObject();
	    if (mainObject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		Jobplan jobplan = new Jobplan();
		jobplan.setJpnum(null);
		jobplan.setDescription(null);
		jobplan.setJpduration(0d);		
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog((Object)jobplan, "/workorder/createjobplanpopup.xul");

	}

	/******************�������ϴ���****************************/
	public void createfailcode() throws Exception
	{
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("���ڴ������ϴ���ǰ�������ݣ�");
			return;
		}
		Workorder workorder = (Workorder) this.getMainObject();
		if (workorder.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		((WorkorderSrv)this.getMainSrv()).createfailcode(workorder);
		this.refreshData();
	}

	/**
	 * 
	 * @author:yuanjq
	 * @throws Exception
	 * @description:ɾ����׼��ҵ�ƻ��������Ϣ @ 2007-7-6 ����10:21:06
	 */
	public void deljbplan() throws Exception {
		int rtn = Messagebox.show("�Ƿ�ȷ��ɾ����ǰ�����ı�׼��ҵ�ƻ������й������Ӽ�¼��", "ɾ��ȷ�ϣ�",
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
		int rtn = Messagebox.show("�Ƿ�ȷ�����ƹ�����","���ƣ�",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			
			if(this.selectedTabid.equals("list"))
			{
				Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��и��Ʋ�����");
				return;
			}
			if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
			{
				Messagebox.show("�ɹ������Ʋ���ǰ�����ȱ������ݣ�");
				return;
			}
			Workorder wo = (Workorder) this.getMainObject();
			if (wo.getId()==null) {
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			Workorder newWO = ((WorkorderSrv)this.getMainSrv()).copywo(wo);
			this.setMainObject(newWO);
			Messagebox.show("�ѳɹ�������:" + wo.getWonum() + "���Ƶ�����" + newWO.getWonum());
			this.refreshData();		
		}	
	}
	
	/** ****************����Ԥ����Ϣ*************ljh************** */
	public void createinvr() throws Exception
	{
		int rtn = Messagebox.show("�Ƿ�ȷ������Ԥ����Ϣ��","���ɣ�",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			if (this.selectedTabid.equals("list"))
			{
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
			{
				Messagebox.show("��������Ԥ��ǰ�������ݣ�");
				return;
			}
			Workorder workorder = (Workorder) this.getMainObject();
			if (workorder.getId()==null) {
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			/**
			 *�ж��Ƿ����ظ�����Ԥ�����Ƿ���Ҫ����Ԥ��
			 * һ������ֻ������һ��Ԥ�����ж�״̬����Ϊ��׼״̬�Ĳ�������Ԥ��
			 */
			String wonum = workorder.getWonum();
			String status = workorder.getStatus();
			if (!status.equals("����׼")) {
				throw new Exception ("����δ����׼����������Ԥ����");
			}
			List wpmateriallist = this.getMainSrv().getBaseDao().findWithQuery(Wpmaterial.class, "wonum = '"+wonum+"'");
			List wptoollist = this.getMainSrv().getBaseDao().findWithQuery(Wptool.class, "wonum='"+wonum+"'");
			int wpmaterialcount = wpmateriallist.size();
			int wptoolcount = wptoollist.size();
			if (!((wpmaterialcount+wptoolcount)>0)) {
				throw new Exception ("�ù���û�п����ɵ�Ԥ�������ʵ��");
			}
			
			List toolreservelist = this.getMainSrv().getBaseDao().findWithQuery(Toolreserve.class, "reqnum = '"+wonum+"'");
			if (toolreservelist.size()>0) {
				throw new Exception ("�����Ѿ�����Ԥ���������ظ����ɣ�");
			}
			
			List invreservelist = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+wonum+"'");
			if (invreservelist.size()>0) {
				throw new Exception ("�����Ѿ�����Ԥ���������ظ����ɣ�");
			}
			
			((WorkorderSrv)this.getMainSrv()).createinvr(workorder);
			this.refreshData();
		}
	}

	/** ****************�鿴Ԥ����Ϣ*************ljh*************** */
	public void selectinvr() throws Exception {
		Workorder wo = (Workorder) this.mainObject;
		if (wo.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("��������Ԥ��ǰ�������ݣ�");
			return;
		}

		this.popupDialog(this.getMainObject(),
				"/inventory/invreservepopup.xul", "wonum='" + wo.getWonum()
						+ "'");

	}
	/* 
	 * ���ܣ������Ӵ���ֻ��
	 * ���ߣ����
	 * ���ڣ�Oct 30, 200811:03:07 AM
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
		if (workorder.getStatus() != null && workorder.getStatus().equals("����׼")) {
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
	 * �������ʱ������BOM������Ϣ
	 * 
	 * ����:������
	 * ����:Mar 2, 2009
	 * @throws Exception
	 */
	public void findeqbom() throws Exception {
		//Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("����׼")) {
		Wpmaterial wpmaterial = new Wpmaterial();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(wpmaterial,"/common/findbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow)this.getFellow("wpmaterialTable");
		listWnd.refreshData();//ˢ�²���
		// }else{
		// Messagebox.show("��������׼�޷���ӱ���");
		// }
	}

}
