package combiz.ui.equipment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.inbasis.zk.ui.Component;

import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;
import combiz.util.Util;

public class FldDepcost extends FieldAdapter{
	@Override
	public void action(Component arg0) throws Exception {
		Equipdep equipdep = (Equipdep) this.mainObject;
		if(equipdep.getDepcost()!=null && equipdep.getDepcost()!=0){
			List list = IBOSrvUtil.getBaseDao().findWithQuery(
					Equipment.class,
					"eqnum='" + equipdep.getEqnum() + "'");
			if (list != null && list.size() > 0) {
				Equipment equip = (Equipment) list.get(0);
				double result = 0.00;
				result = equip.getTotalcost()*equipdep.getUsedyears();
				this.setValueByColname("deprate",Util.doubleTodouble(equipdep.getDepcost()/result));
				this.setValueByColname("nowcost", Util.doubleTodouble(equip.getTotalcost()-equipdep.getDepcost()));
			}
			
			
		}
		
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
