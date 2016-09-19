package combiz.ui.equipment;

import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;
import combiz.util.Util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldPlanYears extends FieldAdapter {

	@Override
	public void action(Component arg0) throws Exception {
		Equipdep equipdep = (Equipdep) this.mainObject;
		double deprate =0.0;
		double nowcost =0.0;
		double depcost = 0.0;
		Equipment equip = null;
		if (equipdep.getPlanyears() != null && equipdep.getUsedyears() != null) {/*
			
			if (equipdep.getUsedyears() - equipdep.getPlanyears() > 0) {
				throw new Exception("已使用年限大于预计使用年限,建议直接填报折旧额");
			}
			List list = IBOSrvUtil.getBaseDao().findWithQuery(Equipment.class,"eqnum='" + equipdep.getEqnum() + "'");
			if (list != null && list.size() > 0) {
				 equip = (Equipment) list.get(0);
			}
			if (equipdep.getUsedyears() - equipdep.getPlanyears() == 0) {
				depcost =  equip.getTotalcost();
				deprate = 1.0;
			} else {
					Depreciation dep = (Depreciation) this.getRecWnd().getOwnerWnd().getMainObject();
					deprate = Util.countDeprate(dep.getDepfaction(), dep.getScraprate(), equipdep.getPlanyears(), equipdep.getUsedyears());
					depcost = Util.countDepcost(dep.getDepfaction(), dep.getScraprate(), equip.getPlanyears(), equipdep.getUsedyears(), equip.getNowcost(), equip.getTotalcost());
					if(equip.getTotalcost()- depcost <= equip.getNowcost())
						nowcost = equip.getTotalcost()- depcost;
					else
						nowcost = equip.getNowcost();
				}
			*/}
		this.setValueByColname("depcost", Util.doubleTodouble(depcost));
		this.setValueByColname("nowcost",Util.doubleTodouble(nowcost));
		this.setValueByColname("deprate", Util.doubleTodouble(deprate));
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
