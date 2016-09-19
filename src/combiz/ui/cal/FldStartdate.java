package combiz.ui.cal;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDatebox;

import java.util.Date;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldStartdate 
extends FieldAdapter 
{

	@Override
	public void action(Component component)
	throws Exception 
	{

	}

	@Override
	public void init(Component component) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Component component)
	throws Exception 
	{
		Date startdate = (Date) this.getValueByColname("startdate");
		Date enddate = (Date) this.getValueByColname("enddate");
		if(enddate!=null && startdate!=null)
		{
			if(enddate.before(startdate))
			{
				IDatebox idatebox= ((IDatebox)component);
				Messagebox.show("结束时间早于开始时间，请重新选择！");
				idatebox.focus(); //为了强制输入正确的值后才能进行其他操作
			}
		}
	}

}
