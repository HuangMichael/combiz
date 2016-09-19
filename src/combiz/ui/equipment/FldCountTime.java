package combiz.ui.equipment;

import java.text.SimpleDateFormat;
import java.util.Date;

import combiz.domain.equipment.Eqdowntime;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.util.DateUnit;

import com.inbasis.zk.ui.Component;

public class FldCountTime extends FieldAdapter{

	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		Eqdowntime eqdowntime = (Eqdowntime)this.mainObject;
		if(eqdowntime.getStarttime() !=null && eqdowntime.getDowntime()!=null){
			double doubletime = eqdowntime.getDowntime();
			long longtime = (long)doubletime;
			long time = eqdowntime.getStarttime().getTime() +  longtime*3600000;
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	java.sql.Timestamp   t   =   java.sql.Timestamp.valueOf(bartDateFormat.format(new Date(time)));   
			java.util.Date   d   =   new   java.util.Date(t.getTime());
			this.setValueByColname("endtime", d);
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Component arg0) throws Exception {
	}

}
