package combiz.business.pm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.pm.Pmgenhistory;
import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseDao;
import combiz.system.IBOSrvUtil;
import combiz.system.schedultask.Task;

public class PmGenerator  extends Task
{
	/**
	 * @TODO 必须实现的任务方法
	 * @param map
	 * yuanjq 2007-8-7 下午02:40:19
	 * @see combiz.system.schedultask.Task#execute(java.util.Map)
	 */
	IBOBaseDao baseDao = null;
	WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
	
	public void execute(Map map) throws Exception
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
				String ifnext = premain.getUsefrequency();//得到是否使用频率
				Long frequency = premain.getFrequency();//得到频率
				String frequnit = premain.getFrequnit();//得到频率单位
				String desc = premain.getDescription();
				Long pmcounter = premain.getPmcounter();
				
				Date nowdate = new Date();//获得当前的时间
				Date firstdate = premain.getFirstdate();
				Date lastdate = premain.getNextdate();
				
				if (firstdate != null)
				{
					
					/***
					 * 判断是否应该生成工单(考虑到循环周期的问题)，并计算出下次执行时间
					 */
					
					if (lastdate != null && this.getNowDate().equals(this.getIntoDate(lastdate)))// 在这个时间范围之内
					{
						
						Workorder workorder = this.createWorkorder(premain,desc);
						//	需要更新有关的记录
						
						if ("是".equals(ifnext) && ifnext != null)
						{
							/**********更新下次执行的时间**************/
							if (frequency>0) 
							{
								this.getDate(premain,lastdate,frequnit, frequency);
							}
						}
						/***
						 * 在执行操作后在维护历史表中添加记录信息
						 */
						this.createPmhistory(premain, workorder);
						
							
							System.out.println("**************第一次启动日期和下一次启动日期、下一个到期日都不为空，且当前日期等于下一个到期日的情况下××××××××××××××工单生成成功！！！！***************");
						
					}
					else if (lastdate != null && lastdate.before(nowdate))//如果下一个到期日早于当前系统日期，只更新下一个到期日；
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
						System.out.println("××××××××××××××××××××下个到期日早于系统当前日期，只更新下一个到期日，不生成工单！！！！！××××××××××××××××××××××");
					}
					else if (lastdate == null)
					{
						/***************时间相等或者在当前时间六小时范围内说明是第一次产生工单*******************************/
						
						if (this.getNowDate().equals(this.getIntoDate(firstdate)))
						{
							
							Workorder workorder = this.createWorkorder(premain,desc);
							if("是".equals(ifnext) && ifnext != null)
							{
								if (frequency>0) 
								{
									this.getDate(premain,firstdate,frequnit, frequency);
								}
							}
							this.createPmhistory(premain, workorder);
							
							//System.out.println("__________第一次产生工单完成。_______________");
							System.out.println("××××××××××××××××××下一个到期日为空，第一次启动日期如果等于当前系统日期，生成工单成功！！！！！！××××××××××××××××");
							
						}
						
						if (firstdate.before(nowdate))
						{
							if("是".equals(ifnext) && ifnext != null)
							{
								if (frequency>0) 
								{
									this.getDate(premain,firstdate,frequnit, frequency);
								}
							}
							System.out.println("××××××××××××××××××"+premain.getPmnum()+"-----------------下一个到期日早于当前系统日期，更新下一个到期日成功！！！！！！××××××××××××××××");
							
						}
						
					}
						
					
				}
			}//for 循环结束(())
		}
		
		System.out.println(""+new Date()+"： SimpleTask1 ..........................");
	}
	
	
	
	/********************计算并更新时间************************/
	public void getDate(Premaint premain,Date date,String frequnit,Long frequency) 
	throws Exception
	{
		Date date1 = null;
		date1=date;
		do{
			date1 = this.updateDate(date1, frequnit, frequency);
		}while (date1.before(new Date()));
		
		/**********更新下次执行的时间**************/
		//System.out.println("***********+++++++&"+date1+"&+++++++*************");
		/********工单生成后******在此处加入premain需要更新的相关信息****************/
		premain.setNextdate(date1);
		premain.setLaststartdate(date);//上次开始日期
		premain.setLastcompdate(date);//最近一次完成日期
		baseDao.updateObject(premain);
		System.out.println("__________"+premain.getPmnum()+"-----------------更新时间完成。_______________");
		
	}
	
	
	/*********************判断并得到时间*********************************/
	public Date updateDate(Date getdate,String frequnit,Long frequency)
	{
		Calendar time=Calendar.getInstance(); 
		time.setTime(getdate);
		if (frequnit.equals("年")) 
		{
			System.out.println("====计算年======="+frequency+frequnit);
			int year = (int) (frequency+0);
			time.add(time.YEAR, year);
			
		}else if(frequnit.equals("月")) {
			System.out.println("====计算月======="+frequency+frequnit);
			int month = (int) (frequency+0);
			time.add(time.MONTH, month);
			
		}else{
			System.out.println("====计算天======="+frequency+frequnit);
			int date = (int) (frequency+0);
			time.add(time.DATE, date);
			//time.add(field, amount)
		}
		return time.getTime();
	}
	
	/********************生成工单*************************/
	public Workorder createWorkorder(Premaint premain ,String description) throws Exception
	{
		Workorder workorder = new Workorder();
		
//		workorder.setWonum(worksrv.genInskey("WONUM"));
//		String date = this.getNowDate();
		Date sysdate = new Date();
		String wonum = worksrv.genInskey("WONUM");
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
		baseDao.updateObject(premain);
		
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
		
		baseDao.saveObject(workorder);
		
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
				worksrv.workflow(workorder,true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("工作流启动失败！");
			}
		}
		return workorder;
	}
	
	/*******************生成维护历史表*************************/
	public void createPmhistory(Premaint premain,Workorder workorder) throws Exception
	{
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
	public String getNowDate()
	{
		//Date mydate = new Date();
		Calendar time=Calendar.getInstance(); 
		
		SimpleDateFormat ntime = new SimpleDateFormat();
		ntime.applyPattern("yyyy-MM-dd");
		String date=ntime.format(time.getTime());

		return date;
		
/*		time.setTime(mydate);
		*//******************得到当前的时间（小时）**************************//*
		time.add(time.HOUR, contime);
		return time.getTime();*/
	}
	
	public String getIntoDate(Date date) 
	{
		SimpleDateFormat ntime = new SimpleDateFormat();
		ntime.applyPattern("yyyy-MM-dd");
		String date1=ntime.format(date.getTime());

		return date1;
	}
	
	/***************String >>Date得到对应日期格式的日期类型YYYY-MM-RR***********************/
    public Date strToDate(String strDate, String format) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
            date = dateFormat.parse(strDate);
        return date;
    }
	
}
