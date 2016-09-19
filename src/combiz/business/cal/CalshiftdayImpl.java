package combiz.business.cal;

import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calshift;
import combiz.domain.cal.Calshiftday;
import combiz.domain.cal.Calworkperiod;
import combiz.system.IBOBaseImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class CalshiftdayImpl extends IBOBaseImpl implements CalshiftdaySrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Calshiftday))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		// this.deleteAllChild(obj, "");
	}

	/**
	 * 点击生成工作排程表 brianhong 2007-10-29
	 * 
	 * @throws Exception
	 */
	public void calwp(Calendar cal, Calshift calshift) throws Exception {
		// 重新生成排程数据
		String weekday[] = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		int weekdayen[] = { java.util.Calendar.MONDAY,
				java.util.Calendar.TUESDAY, java.util.Calendar.WEDNESDAY,
				java.util.Calendar.THURSDAY, java.util.Calendar.FRIDAY,
				java.util.Calendar.SATURDAY, java.util.Calendar.SUNDAY };
		this.setOrderBy("dayseq");
		List shiftdaylist = this.findWithQuery("shiftnum='"
				+ calshift.getShiftnum() + "'");
		Calworkperiod calworkperiod;
		// 工作日历的开始时间和结束时间生成JAVA日历时间对象
		java.util.Calendar startdate = java.util.Calendar.getInstance();
		startdate.setTime(cal.getStartdate());
		java.util.Calendar enddate = java.util.Calendar.getInstance();
		enddate.setTime(cal.getEnddate());
		if (enddate.before(startdate))
			throw new Exception("日历定义的时间有错误，开始时间不能在结束时间后！");

		// 设置星期一为一个星期的第一天
		startdate.setFirstDayOfWeek(java.util.Calendar.MONDAY);
		enddate.setFirstDayOfWeek(java.util.Calendar.MONDAY);
		// 获取开始的星期，如从星期三开始，从而从日历开始日算起获取最新的符合开始星期的时间，存在在startdate
		int j;
		for (j = 0; j < weekday.length; j++) {
			if (weekday[j].equals(calshift.getStartday()))
				break;
		}
		// 调整到最新的开始的星期数的那天，可能小于日历开始时间，也可能大于
		startdate.set(java.util.Calendar.DAY_OF_WEEK, weekdayen[j]);
		// System.out.println("调整到星期："+weekdayen[j]+" 日期: "
		// +startdate.getTime());

		// 先删除所有日历排程
		this.getBaseDao().deleteBatch(
				this.getBaseDao().findWithQuery(
						Calworkperiod.class,
						"calnum='" + cal.getCalnum() + "' and shiftnum='"
								+ calshift.getShiftnum() + "'"));

		// 获取日历总的天数
		long quot = 0;
		quot = cal.getEnddate().getTime() - cal.getStartdate().getTime();
		int days = (int) (quot / 1000 / 60 / 60 / 24);
		int k = 0;
		for (long i = 0; i < days; i++) {
			if (startdate.before(cal.getStartdate())) {
				// 从开始日计算，循环加一天
				startdate.add(java.util.Calendar.DATE, 1);
				continue;
			}
			if (startdate.after(cal.getEnddate()))
				break;

			// 根据日模式来循环取出calshiftday
			Calshiftday calshiftday = (Calshiftday) shiftdaylist.get(k);
			k = k + 1;
			if (k >= calshift.getDaymold())
				k = 0;

			// 工作开始和结束时间只要有一个为空，那就不生成记录
			if (calshiftday.getStarttime() == null
					|| calshiftday.getEndtime() == null) {
				// 从开始日计算，循环加一天
				startdate.add(java.util.Calendar.DATE, 1);
				continue;
			}

			// 获取目前的工作日，将工作日的日期加入到calshiftday的日开始和结束时间中，形成当日的开始工作时间和结束时间
			java.util.Calendar workstartdate = java.util.Calendar.getInstance();
			java.util.Calendar workenddate = java.util.Calendar.getInstance();
			// 调整到当前的日期，但是时间还是用开始和结束时间代替
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todaystr = sdf.format(new Date());
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String starttimes = calshiftday.getStarttime();
			String endtimes = calshiftday.getEndtime();
			//取出在模式定义里定义的开始时间小时数。
			String[] s = null; 
			s=starttimes.split(":"); 
			String begstarttimes = s[0];
			//取出在模式定义里定义的结束时间小时数。
			String[] s1 = null; 
			s1=endtimes.split(":");
			String begendtimes = s1[0];
			//将开始时间和结束时间小时数转换为double型比较大小。
			Double starttimed = Double.parseDouble(begstarttimes);
			Double endtimed = Double.parseDouble(begendtimes);
			
			Date starttime = sdf.parse(todaystr + " "
					+ calshiftday.getStarttime());
			Date endtime = sdf.parse(todaystr + " " + calshiftday.getEndtime());
			
			
			// 设置开始时间和结束时间，并且用循环的日历工作日期代替上面设置的当前日期，时间值保持不变
			workstartdate.setTime(starttime);
			workenddate.setTime(endtime);
			
			workstartdate.set(startdate.get(java.util.Calendar.YEAR), startdate
					.get(java.util.Calendar.MONTH), startdate
					.get(java.util.Calendar.DATE));
			//判断结束时间是否早于开始时间，如果结束时间早于开始时间，说明是第二天的开始时间。
			if(endtimed<starttimed)
			{
				
				workenddate.set(startdate.get(java.util.Calendar.YEAR), startdate
						.get(java.util.Calendar.MONTH), startdate
						.get(java.util.Calendar.DATE)+1);
			}
			else
			{
				workenddate.set(startdate.get(java.util.Calendar.YEAR), startdate
						.get(java.util.Calendar.MONTH), startdate
						.get(java.util.Calendar.DATE));
			}
			
			// 设置对象值
			calworkperiod = new Calworkperiod();
			calworkperiod.setCalnum(cal.getCalnum());
			calworkperiod.setShiftnum(calshift.getShiftnum());
			calworkperiod.setWorkdate(startdate.getTime());
			calworkperiod.setStarttime(workstartdate.getTime());
			calworkperiod.setEndtime(workenddate.getTime());
			calworkperiod.setWorkhours(calshiftday.getWorkhours());

			this.getBaseDao().saveObject(calworkperiod);

			// 从开始日计算，循环加一天
			startdate.add(java.util.Calendar.DATE, 1);
		}
	}
}
