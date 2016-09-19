package combiz.ui.cal;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IFloatbox;
import combiz.system.ui.common.ITimebox;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;

public class FldStarttime 
extends FieldAdapter 
{

	@Override
	public void action(Component component) 
	throws Exception 
	{
		ITimebox itimebox = (ITimebox)component;
		String starttimestr = itimebox.getValue();
		if(starttimestr!=null && starttimestr.length()>0)
		{
			//计算时差
			String endtimestr = (String) this.getValueByColname("endtime");
			if(endtimestr!=null && endtimestr.length()>0)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String todaystr = sdf.format(new Date());
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date starttime = sdf.parse(todaystr + " " + starttimestr);
				Date endtime = sdf.parse(todaystr + " " + endtimestr);
				
				//计算并写入值
				float times=0.0f;
				if(starttime!=null && endtime!=null)
				{
					if(endtime.before(starttime))
						times = ((endtime.getTime() - starttime.getTime()) / 1000 / 60 / 60) + 24;
					else
						times = (endtime.getTime() - starttime.getTime()) / 1000 / 60 / 60;
				}
				IFloatbox ifloatbox = (IFloatbox) component.getFellow("calshiftday.workhours");
				ifloatbox.setIBSValue(times);
			}
		}
	}

	@Override
	public void init(Component component) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Component component)
	throws Exception 
	{

	}

}
