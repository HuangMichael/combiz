package combiz.ui.equipment;

import java.util.List;

import com.inbasis.zk.ui.Component;

import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;
import combiz.util.DateUnit;
import combiz.util.Util;

public class FldEquipdepEqnum extends FieldAdapter {

	@Override
	public void action(Component arg0) throws Exception {/*
		Equipdep equipdep = (Equipdep) this.mainObject;
		List list = IBOSrvUtil.getBaseDao().findWithQuery(Equipment.class,
				"eqnum='" + equipdep.getEqnum() + "'");
		double depcost = 0.0;
		if (list != null && list.size() > 0) {
			Equipment equipment = (Equipment) list.get(0);
			if (equipment.getPlanyears() != null) {
				Depreciation dep = (Depreciation)IBOSrvUtil.getBaseDao().findWithQuery(Depreciation.class, "depnum='"+equipdep.getDepnum()+"'").get(0);
				this.setValueByColname("planyears", equipment.getPlanyears());
				this.setValueByColname("deprate", Util.countDeprate(dep.getDepfaction(), dep.getScraprate(), equipment.getPlanyears(), DateUnit.getYearValue(equipment.getInstalldate())));
				depcost = Util.countDepcost(dep.getDepfaction(), dep.getScraprate(), equipment.getPlanyears(), DateUnit.getYearValue(equipment.getInstalldate()), equipment.getNowcost(), equipment.getTotalcost());
				this.setValueByColname("depcost", depcost);
			}
			if (equipment.getNowcost() != null && equipment.getNowcost()-equipment.getTotalcost()!=0) {
				this.setValueByColname("nowcost", equipment.getNowcost());
			}else{
				this.setValueByColname("nowcost",equipment.getTotalcost()-depcost);
			}
			if (equipment.getInstalldate() != null) {
				this.setValueByColname("usedyears", DateUnit
						.getYearValue(equipment.getInstalldate()));
			}

		}
	*/}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
