package combiz.business.test;

import combiz.business.corp.LaborSrv;
import combiz.business.pm.PmgenhistorySrv;
import combiz.business.pm.PremaintSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.corp.Labor;
import combiz.domain.pm.Pmgenhistory;
import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseDao;
import combiz.system.IBOSrvUtil;
import combiz.system.schedultask.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zkplus.spring.SpringUtil;

/**
 * @author hp520
 * 任务实例
 */
public class SimpleTask1 extends Task
{
	/**
	 * @TODO 必须实现的任务方法
	 * @param map
	 * yuanjq 2007-8-7 下午02:40:19
	 * @see combiz.system.schedultask.Task#execute(java.util.Map)
	 */
	IBOBaseDao baseDao = null;
	WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
	//PremaintSrv premainsrv = (PremaintSrv)IBOSrvUtil.getSrv("premaint");
	
	public void execute(Map map)
	{
		baseDao = (IBOBaseDao)map.get("baseDao");
		//任务执行的代码...
		/***
		 * 检验预防性维护中是否有数据，并得到信息
		 */
		System.out.println("&&&&&&&&&&&&&&&&&--------come into execute()------------&&&&&&&&&&&&&&&&&");
		List list = baseDao.findWithQuery(Premaint.class, "");
		if (list.size()>0)
		{
			for (int i=0;i<list.size();i++)
			{
				Premaint premain = (Premaint) list.get(i);
				String ifnext = premain.getUsefrequency();
				Long frequency = premain.getFrequency();
				String frequnit = premain.getFrequnit();
				
				Date nowdate = new Date();//获得当前的时间
				Date firstdate = premain.getFirstdate();
				Date lastdate = premain.getNextdate();
				
				if (firstdate != null)
				{
					
					/***
					 * 判断是否应该生成工单(考虑到循环周期的问题)，并计算出下次执行时间
					 */
					if (lastdate != null && this.ifCreate(-12).before(lastdate) && lastdate.before(this.ifCreate(12)))// 在这个时间范围之内
					{
						//	需要更新有关的记录
						if ("是".equals(ifnext) && ifnext != null)
						{
							/**********更新下次执行的时间**************/
							if (frequency>0) 
							{
								this.getDate(premain,lastdate,frequnit, frequency);
							}
						}
						try {
							
							this.createWorkorder(premain);
							System.out.println("**************工单生成成功***************");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}	
						
					}
					else if (lastdate != null && lastdate.before(nowdate))
					{
						if ("是".equals(ifnext) && ifnext != null)
						{
							/*****************需要更新时间但不需要生成工单***********************/
							//Date date1 = this.updateDate(lastdate, frequnit, frequency);
							if (frequency>0) 
							{
								this.getDate(premain,lastdate,frequnit, frequency);
							}
						}
					}
					else if (lastdate == null)
					{
						/***************时间相等或者在当前时间六小时范围内说明是第一次产生工单*******************************/
						System.out.println("-------------第一次生成工单---------------");
						
						if (this.ifCreate(-12).before(firstdate) && firstdate.before(this.ifCreate(12)))
						{
								if("是".equals(ifnext) && ifnext != null)
								{
									if (frequency>0) 
									{
										this.getDate(premain,firstdate,frequnit, frequency);
									}
								}
								try {
									this.createWorkorder(premain);
									System.out.println("__________第一次产生工单完成。_______________");
								} catch (Exception e) {
									// TODO 自动生成 catch 块
									e.printStackTrace();
								}
								
						}
						
						if (firstdate.after(nowdate))
						{
							if("是".equals(ifnext) && ifnext != null)
							{
								if (frequency>0) 
								{
									this.getDate(premain,firstdate,frequnit, frequency);
								}
							}
						}
					}
						
					
				}
			}//for 循环结束(())
		}
		
		System.out.println(""+new Date()+"： SimpleTask1 ..........................");
	}
	
	/********************计算并更新时间************************/
	public void getDate(Premaint premain,Date date,String frequnit,Long frequency)
	{
		Date date1 = null;
		date1=date;
		do{
			date1 = this.updateDate(date1, frequnit, frequency);
		}while (date1.before(new Date()));
		
		/**********更新下次执行的时间**************/
		System.out.println("***********+++++++&"+date1+"&+++++++*************");
		/********工单生成后******在此处加入premain需要更新的相关信息****************/
		premain.setNextdate(date1);
		premain.setLaststartdate(date);//上次开始日期
		premain.setLastcompdate(date);//最近一次完成日期
		try {
			baseDao.updateObject(premain);
			System.out.println("__________更新时间完成。_______________");
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	/*********************判断并得到时间*********************************/
	public Date updateDate(Date getdate,String frequnit,Long frequency)
	{
		Calendar time=Calendar.getInstance(); 
		time.setTime(getdate);
		if (frequnit.equals("年")) 
		{
			int year = (int) (frequency+0);
			time.add(time.YEAR, year);
			
		}else if(frequnit.equals("月")) {
			int month = (int) (frequency+0);
			time.add(time.MONTH, month);
			
		}else{
			int date = (int) (frequency+0);
			time.add(time.DATE, date);
			//time.add(field, amount)
		}
		return time.getTime();
	}
	
	/********************生成工单*************************/
	public Workorder createWorkorder(Premaint premain) throws Exception
	{
		//WorkorderSrv worksrv = (WorkorderSrv)SpringUtil.getBean("WorkorderSrv");
		//LaborSrv laborsrv = (LaborSrv)IBOSrvUtil.getSrv("laborSrv");
		Workorder workorder = new Workorder();
		
		workorder.setWonum(worksrv.genInskey("WONUM"));
		
		//workorder.setSitenum(premain.getSitenum());
		//workorder.setCorpnum(premain.getCorpnum());
		/*****************为生成的面板中赋值*******************/
		workorder.setStatus("开");
		workorder.setEstlabhrs(new Double(0.0));
		workorder.setEstlabcost(new Double(0.0));
		workorder.setEstmatcost(new Double(0.0));
		workorder.setEsttoolcost(new Double(0.0));
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
		
		workorder.setPmnum(premain.getPmnum());
		workorder.setDescription(premain.getDescription());
		workorder.setLocation(premain.getLocation());
		workorder.setEqnum(premain.getEqnum());
		workorder.setWorktype(premain.getWorktype());
		workorder.setSupervisor(premain.getSupervisor());
		workorder.setCrewid(premain.getCrewid());
		workorder.setJpnum(premain.getJpnum());
		
		baseDao.saveObject(workorder);
		
		/***
		 * 在执行操作后在维护历史表中添加记录信息
		 */
		this.createPmhistory(premain, workorder);
		return workorder;
	}
	
	/*******************生成维护历史表*************************/
	public void createPmhistory(Premaint premain,Workorder workorder) throws Exception
	{
		PmgenhistorySrv pmhistorysrv = (PmgenhistorySrv)IBOSrvUtil.getSrv("pmgenhistorySrv");
		Pmgenhistory newobj = new Pmgenhistory();
		
		newobj.setPmnum(premain.getPmnum());
		newobj.setNextstartdate(premain.getNextdate());
		newobj.setStartdate(premain.getFirstdate());
		newobj.setJpnum(premain.getJpnum());
		newobj.setPerformdate(premain.getLastcompdate());
		newobj.setWonum(workorder.getWonum());
		
		baseDao.saveObject(newobj);
	}
	
	/***************判断是否生成工单**************************/
	public Date ifCreate(int contime)
	{
		Date mydate = new Date();
		Calendar time=Calendar.getInstance(); 
		time.setTime(mydate);
		/******************得到当前的时间（小时）**************************/
		time.add(time.HOUR, contime);
		return time.getTime();
	}
}
