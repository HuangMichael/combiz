package combiz.ui.workorder;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqdowntime;
import combiz.domain.inventory.Invreserve;
import combiz.domain.stdplan.Jobplan;
import combiz.domain.tool.Toolreserve;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wplabor;
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

public class JobWorkorderWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public JobWorkorderWindow() {
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
		String wonum = this.genAutokey("wonum");
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
		newobj.setEstatapprmatcost(new Double(0.0));
		newobj.setEstatapprtoolcost(new Double(0.0));
		newobj.setEstatapprlabhrs(new Double(0.0));
		newobj.setStatusdate(new Date());
		//newobj.setWorktype("JOB");
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
		String wonum = workorder.getWonum();
		String eqnum = workorder.getLocation();
		String location = workorder.getEqnum();
		if (!Util.isNotNull(eqnum) && !Util.isNotNull(location)) {
			Messagebox.show("λ�ú��豸��Ų���ͬʱΪ�գ��벹����Ϣ");
		}
		/** ******************�жϽ����Ͻ���ʱ���Ƿ����ڿ�ʼʱ��******************** */
		Date planbegindate = null;
		Date planenddate = null;
		if (null != workorder.getSchedstart()
				&& null != workorder.getSchedfinish()) {
			planbegindate = workorder.getSchedstart();
			planenddate = workorder.getSchedfinish();
			if (planbegindate.after(planenddate)) {
				Messagebox.show("�ƻ����ʱ�䲻�����ڼƻ���ʼʱ��");
				return;
			}
		}
		Date begindate = null;
		Date enddate = null;
		if (null != workorder.getActstart() && null != workorder.getActfinish()) {
			begindate = workorder.getActstart();
			enddate = workorder.getActfinish();
			if (begindate.after(enddate)) {
				Messagebox.show("ʵ�����ʱ�䲻������ʵ�ʿ�ʼʱ��");
				return;
			}
		}
		Date aimbegindate = null;
		Date aimenddate = null;
		if (null != workorder.getTargstart() && null != workorder.getTargcomp()) {
			aimbegindate = workorder.getTargstart();
			aimenddate = workorder.getTargcomp();
			if (aimbegindate.after(aimenddate)) {
				Messagebox.show("Ŀ�����ʱ�䲻������Ŀ�꿪ʼʱ��");
				return;
			}
		}
		workorder.setChangeby(this.getUserInfo().getLabornum());
		// ////////////////////////Ԥ�㹤����Ŀ/////////
		super.save();
	}

	


	/*
	 * ���ܣ������Ӵ���ֻ�� ���ߣ���� ���ڣ�Oct 30, 200811:03:07 AM
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Workorder workorder = (Workorder) this.getMainObject();
		
		ListWindow documentlistwnd = (ListWindow) this.getFellow("document");
		ListWindow docversionlistwnd = (ListWindow) this
				.getFellow("docversion");
		ListWindow docauthlistwnd = (ListWindow) this.getFellow("docauth");
		
		if (workorder.getStatus() != null
				&& workorder.getStatus().equals("����׼")) {
			documentlistwnd.setReadonlyList(true);
			docversionlistwnd.setReadonlyList(true);
			docauthlistwnd.setReadonlyList(true);
		}else{
			documentlistwnd.setReadonlyList(false);
			docversionlistwnd.setReadonlyList(false);
			docauthlistwnd.setReadonlyList(false);
		}
		super.initData();
	}

}
