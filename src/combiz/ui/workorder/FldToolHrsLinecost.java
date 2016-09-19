package combiz.ui.workorder;

import com.inbasis.zk.ui.Component;

import combiz.domain.workorder.Wplabor;
import combiz.domain.workorder.Wptool;
import combiz.system.FieldAdapter;

public class FldToolHrsLinecost extends FieldAdapter{

	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		Wptool wptool = (Wptool)this.mainObject;
		if(wptool.getToolhrs()!=null && wptool.getToolqty()!=null && wptool.getRate()!=null){
			wptool.setLinecost(wptool.getToolhrs()*wptool.getToolqty()*wptool.getRate());
		}
		this.mainObject=wptool;
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
