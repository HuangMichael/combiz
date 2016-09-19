package combiz.business.equipment;

import combiz.domain.equipment.Dephistory;
import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseImpl;
import combiz.util.Util;

import java.util.List;

public class DepreciationImpl extends IBOBaseImpl implements DepreciationSrv {
	public void delete(Object obj) throws Exception {
		// this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
	/**
	 * 
	 * 方法 折旧固资
	 * 
	 * 作者:陈明锐
	 * 功能
	 * 日期Apr 7, 2009
	 */
	public void depAsset(List list, Object obj) throws Exception {/*
		Depreciation dep = (Depreciation)obj;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Equipdep equipdep = (Equipdep)list.get(i);
				//更新设备表内 nowcost,depmark;
				Equipment equipment = (Equipment)this.findWithQuery(Equipment.class, "eqnum = '"+equipdep.getEqnum()+"'").get(0);
				//条件折旧额无变化,条件折旧率无变化,以可能变更的条件折旧法和条件使用年 从新计算折旧额
				if(equipdep.getLdepcost()==null && equipdep.getLdeprate()==null ){
					if(equipdep.getDepcount()!=null){
						dep.setDepfaction(equipdep.getDepcount());//如果选择了新的的折旧法
					}
					equipdep.setDepcost(Util.countDepcost(dep.getDepfaction(), dep.getScraprate(), equipment.getPlanyears(), equipdep.getUsedyears(), equipment.getTotalcost()));// 折旧额
				}
				//条件折旧额无变化,以条件折旧率统计新折旧额
				if(equipdep.getLdepcost()==null && equipdep.getLdeprate()!=null && equipdep.getLdeprate()!=0){
					equipdep.setDepcost(Util.doubleTodouble((equipdep.getLdeprate()*equipment.getTotalcost()*equipdep.getUsedyears())));
				}
				//条件折旧额变化,从新算出折旧率
				if(equipdep.getLdepcost()!=null && equipdep.getLdepcost()!=0){
					double total = equipment.getTotalcost()*equipdep.getUsedyears();
					equipdep.setDeprate(equipdep.getLdepcost()/total);
				}
				//在原总价高于折旧额的时候计算当前值;
				if(equipdep.getDepcost()!=null && equipdep.getDepcost()!=0 && equipment.getTotalcost() - equipdep.getDepcost()>0 ){
					//从新计算当前值
					equipdep.setNowcost(equipment.getTotalcost() - equipdep.getDepcost());
				}else{
					//在折旧额高于原总价的时候,当前价为0;
					equipdep.setDepcost(equipment.getTotalcost());
					equipdep.setNowcost(0.00);
					equipdep.setDeprate(1.0);
				}
				equipdep.setStatus("已折旧");
				this.update(equipdep);//跟新明细表
				
				/////////////////////设备//////////////////////////
				//equipment.setNowcost(equipdep.getNowcost());
				this.update(equipment);//更新设备表
				/////////////////////设备////////////////////////////
				
				//插入历史表
				Dephistory history = new Dephistory();
				history.setLabornum(dep.getLabornum());
				history.setDepdate(dep.getDepdate());
				history.setPlanyears(equipdep.getPlanyears());
				history.setDeprate(equipdep.getDeprate());
				history.setDepcost(equipdep.getDepcost());
				history.setNowcost(equipdep.getNowcost());
				history.setEqnum(equipdep.getEqnum());
				this.save(history);
			}
		}
	*/}
}