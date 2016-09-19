package combiz.ui.pm;
 
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.pm.PremaintSrv;
import combiz.domain.corp.Labor;
import combiz.domain.pm.Premaint;
import combiz.domain.user.Ibsgroupusers;
import combiz.domain.user.Ibsusers;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ICheckbox;
import combiz.system.ui.common.ICombobox;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;


public class PremaintWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public PremaintWindow()
	{
		super();
	}
	
	/* 
	 * 功能：判断位置和资产是否同时为空
	 * 作者：李阳
	 * 日期：2008-3-19下午08:16:20
	 */
	public void save() throws Exception {
		// TODO 自动生成方法存根
		Premaint premaint = (Premaint) this.getMainObject();
		IBandbox arg0 =  (IBandbox) this.getFellow("premaint.location");
		IBandbox arg1 = (IBandbox) this.getFellow("premaint.eqnum");
		ICheckbox arg2 = (ICheckbox) this.getFellow("premaint.usejpseq");
		IBandbox arg3 =  (IBandbox) this.getFellow("premaint.jpnum");
		String eqnum = arg1.getValue();
		String location = arg0.getValue();
		String jpnum = arg3.getValue();
		String usejpseq = arg2.getValue();
		if(!Util.isNotNull(eqnum) && !Util.isNotNull(location))
		{
			Messagebox.show("位置和设备编号不能同时为空，请补充信息");
			return;			
		}
		if(!Util.isNotNull(jpnum) && usejpseq.equals("是"))
		{
			Messagebox.show("您选择使用标准作业计划，请完善'标准作业计划'字段！");
			return;
		}
		
		
		
	
		
		super.save();
	}
	

	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Premaint newobj = new Premaint();
		newobj.setPmcounter(0L);
		newobj.setHaschild("否");
		newobj.setAdjnextdue("否");
		newobj.setAutowf("否");
		newobj.setUsefrequency("是");
		newobj.setFrequnit("天");
		newobj.setEqdown("否");
		newobj.setAutowf("是");
		newobj.setUsejpseq("否");
		newobj.setWostatus("流程未启动");
//		String labornum = this.getUserInfo().getLabornum();
//		List laborlist = this.getMainSrv().findWithQuery(Labor.class,"labornum ='" + labornum+ "'");
//		Ibsusers ibsusers = (Ibsusers) this.getDesktop().getSession().getAttribute("userinfo");//获取当前登录用户。
		
		
		Labor labor =(Labor)this.getDesktop().getSession().getAttribute("laborinfo");//获取当前登录对应的人员。
		String  sitenum = labor.getSitenum();
		
		String pmnum = this.genAutokey("pmnum");
		if (Util.isNotNull(sitenum)) {
			pmnum = sitenum + " - " + pmnum;
		}
		else {
			sitenum = "ADMIN";
			pmnum = "admin-" + pmnum;
		}
		newobj.setPmnum(pmnum);
		mainObject = newobj;
		return true;
	}

	
	
	/**
	 * 生成工单
	 * @throws Exception
	 * hyf
	 */
	public void workorder() throws Exception {
		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行拷贝操作！");
			return;
		}
		Premaint premaint = (Premaint)this.getMainObject();
		String description = premaint.getDescription();
		//调用业务类的方法
		((PremaintSrv)this.getMainSrv()).createWorkorder(premaint,description);
		Messagebox.show("生成完成，请完善！");
		this.refreshData();
	}

}
