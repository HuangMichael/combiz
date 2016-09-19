package combiz.ui.workorder;

import com.inbasis.zk.ui.Component;

import combiz.domain.workorder.Wplabor;
import combiz.system.FieldAdapter;

public class FldLaborHrsLinecost extends FieldAdapter{

	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		Wplabor wplabor = (Wplabor)this.mainObject;
		if(wplabor.getLaborhrs()!=null && wplabor.getLaborqty()!=null && wplabor.getRate()!=null){
			wplabor.setLinecost(wplabor.getLaborhrs()*wplabor.getLaborqty()*wplabor.getRate());
		}
		this.mainObject=wplabor;
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
