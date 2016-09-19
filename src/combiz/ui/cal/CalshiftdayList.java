package combiz.ui.cal;

import combiz.business.cal.CalshiftdaySrv;
import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calshift;
import combiz.system.ui.CommonListWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

public class CalshiftdayList extends CommonListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
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
	 * ������ɹ����ų̱�
	 * brianhong  2007-10-29
	 * @throws Exception
	 */
	public void calwp()
	throws Exception
	{
		if(this.getObjStatus()==this.ADDED || this.getObjStatus()==this.MODIFIED)
		{
			Messagebox.show("���ɹ����ų̱�֮ǰ�����ȱ����޸ģ�");
			return;
		}
		
		CalendarWindow calwnd = (CalendarWindow) this.getOwnerWnd();
		Calendar calendar = (Calendar) calwnd.getMainObject();
		Calshift calshift = (Calshift) this.getParam().get("calshift");
		//ȷ��
		int rtn = Messagebox.show("�Ƿ�ɾ��["+calshift.getShiftnum()+"]���������������й����ų̣����������ɣ�","�Ƿ�ȷ����",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}

		CalshiftdaySrv calshiftdaySrv = (CalshiftdaySrv)this.getMainSrv();
		calshiftdaySrv.calwp(calendar,calshift);
		calwnd.refreshChildData();
		
		//
		Messagebox.show("�Ѿ������˹����ų̱�");
	}
	
	
}
