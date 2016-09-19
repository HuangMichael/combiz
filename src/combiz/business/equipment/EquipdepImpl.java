package combiz.business.equipment;

import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseImpl;
import combiz.util.DateUnit;
import combiz.util.Util;

import java.util.List;

public class EquipdepImpl extends IBOBaseImpl implements EquipdepSrv {
	public void delete(Object obj) throws Exception {
		// this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}

	/**
	 * 
	 * 方法 生成明细
	 * 
	 * 作者:陈明锐 功能 日期Apr 7, 2009
	 */
	public void creatList(List list, Object obj) throws Exception {/*
		int count = 0;
		if (list != null && list.size() > 0) {
			count = list.size();
		}
		List eqlist = this.findWithQuery(Equipment.class, buildSql(list, obj));
		Depreciation dep = (Depreciation) obj;
		if (eqlist != null && eqlist.size() > 0) {
			for (int i = 0; i < eqlist.size(); i++) {
				Equipment equipment = (Equipment) eqlist.get(i);
				Equipdep eqdep = new Equipdep();
				eqdep.setDepnum(dep.getDepnum());// 折旧单号
				eqdep.setEqnum(equipment.getEqnum());// 设备编码
				eqdep.setLinenum((long) count + i + 1);// 行号
				eqdep.setPlanyears(equipment.getPlanyears());// 预计使用年限
				eqdep.setNowcost(equipment.getNowcost());// 当前值
				eqdep.setStatus("未");// 状态
				eqdep.setDepcount("固资折旧法");// 运算方法
				eqdep.setUsedyears(DateUnit.getYearValue(equipment
						.getInstalldate()));// 已使用年限
				eqdep.setDeprate(Util.countDeprate(dep.getDepfaction(), dep
						.getScraprate(), equipment.getPlanyears(), eqdep
						.getUsedyears()));// 折旧率
				eqdep.setDepcost(Util.countDepcost(dep.getDepfaction(), dep
						.getScraprate(), equipment.getPlanyears(), eqdep
						.getUsedyears(), equipment.getNowcost(), equipment
						.getTotalcost()));// 折旧额
				if(equipment.getNowcost()!=null && equipment.getNowcost()-equipment.getTotalcost()!=0)
					eqdep.setNowcost(equipment.getNowcost());// 当前值
				else
					eqdep.setNowcost(equipment.getTotalcost() - eqdep.getDepcost());// 当前值
				this.save(eqdep);
			}
		}

	*/}

	/**
	 * 生成SQL 语句 作者:陈明锐 日期:Mar 31, 2009
	 * 
	 * @param list
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String buildSql(List list, Object obj) throws Exception {
		// 按月来判断资产月折旧是否完成
		String sql = "";
		Depreciation dep = (Depreciation) obj;
		if (dep.getClassid() != null) {
			sql += " classid like '%" + dep.getClassid() + "%'";
		}
		// 开始时间为空
		if (dep.getBegintime() == null && dep.getEndtime() != null) {
			sql += " and installdate <= to_date('"
					+ DateUnit.getSimpleFormatsToday(dep.getEndtime())
					+ "','yyyy-MM-dd')";
		}
		// 结束时间为空
		if (dep.getBegintime() != null && dep.getEndtime() == null) {
			sql += " and installdate >= to_date('"
					+ DateUnit.getSimpleFormatsToday(dep.getBegintime())
					+ "','yyyy-MM-dd') and installdate <= to_date('"
					+ DateUnit.getSimpleFormatsToday() + "','yyyy-MM-dd')";
		}
		// 结束时间都不为空
		if (dep.getBegintime() != null && dep.getEndtime() != null) {
			sql += " and installdate >= to_date('"
					+ DateUnit.getSimpleFormatsToday(dep.getBegintime())
					+ "','yyyy-MM-dd') and installdate <= to_date('"
					+ DateUnit.getSimpleFormatsToday(dep.getEndtime())
					+ "','yyyy-MM-dd')";
		}
		sql += " and eqnum not in(select e.eqnum from Equipdep e where e.depnum='"
				+ dep.getDepnum() + "')";
		return sql;
	}

	/**
	 * 
	 * 作者:陈明锐 日期:Mar 31, 2009
	 * 
	 * @param list
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String buildStr(List list) throws Exception {
		String str = "";
		if (list != null && list.size() > 0) {
			if (list.size() == 1) {
				Equipdep equipdep = (Equipdep) list.get(0);
				str += "'" + equipdep.getEqnum() + "'";
			} else {
				for (int i = 0; i < list.size(); i++) {
					Equipdep equipdeps = (Equipdep) list.get(i);
					if (i == list.size() - 1) {
						str += "'" + equipdeps.getEqnum() + "'";
						break;
					}
					str += "'" + equipdeps.getEqnum() + "',";
				}
			}
		}
		return str;
	}
}