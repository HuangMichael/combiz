package combiz.business.pm;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;

import java.util.Date;
import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class PremaintImpl extends IBOBaseImpl
implements PremaintSrv {


	WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Premaint))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////业务方法//////////////////////////////////
	public Workorder createWorkorder(Premaint premain ,String description) throws Exception
	{
		Workorder workorder = new Workorder();
		
//		workorder.setWonum(worksrv.genInskey("WONUM"));
//		String date = this.getNowDate();
		Date sysdate = new Date();
		String wonum = this.genInskey("WONUM");
		String worktype = premain.getWorktype();
		wonum = "Sys-"+worktype +"-"+ wonum;
		workorder.setWonum(wonum);
//		String date = sysdate.toLocaleString();
//		String wodesc = date + " 由预防性维护 -" + description +"- 自动生成";
		String wodesc = description +"- 自动生成（周期:"+premain.getFrequency()+" "+premain.getFrequnit()+"）";
		workorder.setDescription(wodesc);
		//workorder.setSitenum(premain.getSitenum());
		//workorder.setCorpnum(premain.getCorpnum());
		
		/*****************更新预防性维护中的维修次数*******************/
    	Long pmcounter=premain.getPmcounter();
		pmcounter++;
		premain.setPmcounter(pmcounter);
		this.getBaseDao().updateObject(premain);
		
		/*****************为生成的面板中赋值*******************/
		workorder.setStatus(premain.getWostatus());
		workorder.setEstlabhrs(new Double(0.0));
		workorder.setEstlabcost(new Double(0.0));
		workorder.setEstmatcost(new Double(0.0));
		workorder.setEsttoolcost(new Double(0.0));
		workorder.setActlabhrs(new Double(0.0));
		workorder.setActmatcost(new Double(0.0));
		workorder.setActlabcost(new Double(0.0));
		workorder.setActtoolcost(new Double(0.0));
		workorder.setEstatapprlabcost(new Double(0.0));
		workorder.setEstatapprlabhrs(new Double(0.0));
		workorder.setEstatapprmatcost(new Double(0.0));
		workorder.setEstatapprtoolcost(new Double(0.0));
		workorder.setEqdown("否");
		workorder.setHaschildren("否");
		workorder.setHasfollowupwork("否");
		workorder.setEstdur(0L);
		workorder.setStatusdate(new java.util.Date());
		workorder.setCrewid(premain.getCrewid());
		workorder.setPmnum(premain.getPmnum());
		workorder.setLocation(premain.getLocation());
		workorder.setEqnum(premain.getEqnum());
		workorder.setWorktype(premain.getWorktype());
		workorder.setSupervisor(premain.getSupervisor());
		workorder.setCrewid(premain.getCrewid());
		workorder.setJpnum(premain.getJpnum());
		workorder.setReportedby("默认管理员");
		workorder.setSchedstart(new Date());
		workorder.setSchedfinish(new Date());
		//workorder.setIsfinish("否");
		
		this.getBaseDao().saveObject(workorder);
		
		/*****************用户选择了标准作业计划,则将标准作业计划数据写入相应的表中***************************/
		if (premain.getJpnum()!=null && !premain.getJpnum().equals("")) 
		{
			worksrv.genJobplan(workorder, premain.getJpnum());
			System.out.println("-------标准作业计划生成完成 ！--------");
		}
		
		
		/****************判断是否启用工作流***********************/
		if ("是".equals(premain.getAutowf())) {
			try
			{
				this.workflow(workorder,true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("工作流启动失败！");
			}
		}
		return workorder;
	}
	
}
