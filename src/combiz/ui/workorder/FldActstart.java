package combiz.ui.workorder;

import java.util.Date;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDatebox;
import combiz.system.ui.common.IDatetimebox;

import com.inbasis.zk.ui.Component;

/**
 * @author 李建红 E-mail:superyang4208731@yahoo.com.cn
 * 2008-5-8下午08:21:51
 * 功能：如果不选择开始时间，则完成时间不能选择，目的计算时间13753311706
 * 捆绑表：workorder
 * 捆绑字段：actstart
 */
public class FldActstart extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根

	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}

	@Override
	public void validate(Component com)
	throws Exception
	{

		IDatetimebox datebox = null;
		datebox = (IDatetimebox) com;
		
		Date startdate = null;
		Date enddate = (Date) this.getValueByColname("actfinish");
		if (datebox.getValue() != null)
		{
			
			this.setNoReadonly("workorder.actfinish");
		}
		startdate=datebox.getValue();
		if (startdate !=null && enddate != null)
		{
			if (startdate.after(enddate)) {
				this.setValueByColname("schedstart", null);
				this.throwException(com, "开始时间["+startdate+"]应在结束时间前，请核实！");
			}
			
			Long getmu = this.getTime(startdate, enddate);
			this.setValueByColname("remdur", getmu);
		}
			return;
		
	}
	public Long getTime(Date date1,Date date2) 
	{
		Long d = 0L;
		d= (date2.getTime() - date1.getTime())/1000/60;
		return d;
	}
}
