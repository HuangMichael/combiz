package combiz.ui.cal;

import combiz.business.cal.CalshiftdaySrv;
import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calshift;
import combiz.system.ui.CommonListWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

public class CalshiftdayList extends CommonListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CalshiftdayList()
	{
		super();
	}

	
	@Override
	public void onCreate() 
	throws Exception
	{
		//CalendarWindow calwnd = (CalendarWindow) this.getOwnerWnd();
		Calshift calshift = (Calshift) this.getParam().get("calshift");
		Long daymold = calshift.getDaymold();
		this.setPageSize(daymold.intValue() + 10);
		//
		this.setQueryString("shiftnum='"+calshift.getShiftnum()+"'");
		
		super.onCreate();
	}
	
	
	/**
	 * 点击生成工作排程表
	 * brianhong  2007-10-29
	 * @throws Exception
	 */
	public void calwp()
	throws Exception
	{
		if(this.getObjStatus()==this.ADDED || this.getObjStatus()==this.MODIFIED)
		{
			Messagebox.show("生成工作排程表之前，请先保存修改！");
			return;
		}
		
		CalendarWindow calwnd = (CalendarWindow) this.getOwnerWnd();
		Calendar calendar = (Calendar) calwnd.getMainObject();
		Calshift calshift = (Calshift) this.getParam().get("calshift");
		//确认
		int rtn = Messagebox.show("是否删除["+calshift.getShiftnum()+"]班次日历下面的所有工作排程，并重新生成？","是否确定！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}

		CalshiftdaySrv calshiftdaySrv = (CalshiftdaySrv)this.getMainSrv();
		calshiftdaySrv.calwp(calendar,calshift);
		calwnd.refreshChildData();
		
		//
		Messagebox.show("已经生成了工作排程表！");
	}
	
	
}
