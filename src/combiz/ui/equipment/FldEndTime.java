package combiz.ui.equipment;

import java.text.SimpleDateFormat;
import java.util.Date;

import combiz.domain.equipment.Eqdowntime;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;

public class FldEndTime extends FieldAdapter{

	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Eqdowntime eqdowntime = (Eqdowntime)this.mainObject;
		if(eqdowntime.getEndtime() !=null && eqdowntime.getStarttime() !=null){
			long time = (eqdowntime.getEndtime().getTime() - eqdowntime.getStarttime().getTime()) / 3600000;
			this.setValueByColname("downtime", new Double(time));
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
