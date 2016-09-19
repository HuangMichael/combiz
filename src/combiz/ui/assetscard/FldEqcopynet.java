package combiz.ui.assetscard;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;
import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetline;
import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class FldEqcopynet extends FieldAdapter {
	public void action(Component component) 
	throws Exception 
	{
		
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public Object initValue() throws Exception
	{
		Equipment equipment = (Equipment) this.getMainObject();
		String eqnum = equipment.getEqnum();
		Double net_xn = 0D;
		List list = this.getMainSrv().getBaseDao().findWithQuery(Assetscard.class, "parent='"+eqnum+"'");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Assetscard assetscard = (Assetscard)list.get(i);
				
				net_xn = net_xn + (assetscard.getNet_xn()==null?0:assetscard.getNet_xn());
				
			}
		}
		return net_xn;
	}
	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
