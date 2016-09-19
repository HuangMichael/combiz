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
	 * ���� ������ϸ
	 * 
	 * ����:������ ���� ����Apr 7, 2009
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
				eqdep.setDepnum(dep.getDepnum());// �۾ɵ���
				eqdep.setEqnum(equipment.getEqnum());// �豸����
				eqdep.setLinenum((long) count + i + 1);// �к�
				eqdep.setPlanyears(equipment.getPlanyears());// Ԥ��ʹ������
				eqdep.setNowcost(equipment.getNowcost());// ��ǰֵ
				eqdep.setStatus("δ");// ״̬
				eqdep.setDepcount("�����۾ɷ�");// ���㷽��
				eqdep.setUsedyears(DateUnit.getYearValue(equipment
						.getInstalldate()));// ��ʹ������
				eqdep.setDeprate(Util.countDeprate(dep.getDepfaction(), dep
						.getScraprate(), equipment.getPlanyears(), eqdep
						.getUsedyears()));// �۾���
				eqdep.setDepcost(Util.countDepcost(dep.getDepfaction(), dep
						.getScraprate(), equipment.getPlanyears(), eqdep
						.getUsedyears(), equipment.getNowcost(), equipment
						.getTotalcost()));// �۾ɶ�
				if(equipment.getNowcost()!=null && equipment.getNowcost()-equipment.getTotalcost()!=0)
					eqdep.setNowcost(equipment.getNowcost());// ��ǰֵ
				else
					eqdep.setNowcost(equipment.getTotalcost() - eqdep.getDepcost());// ��ǰֵ
				this.save(eqdep);
			}
		}

	*/}

	/**
	 * ����SQL ��� ����:������ ����:Mar 31, 2009
	 * 
	 * @param list
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String buildSql(List list, Object obj) throws Exception {
		// �������ж��ʲ����۾��Ƿ����
		String sql = "";
		Depreciation dep = (Depreciation) obj;
		if (dep.getClassid() != null) {
			sql += " classid like '%" + dep.getClassid() + "%'";
		}
		// ��ʼʱ��Ϊ��
		if (dep.getBegintime() == null && dep.getEndtime() != null) {
			sql += " and installdate <= to_date('"
					+ DateUnit.getSimpleFormatsToday(dep.getEndtime())
					+ "','yyyy-MM-dd')";
		}
		// ����ʱ��Ϊ��
		if (dep.getBegintime() != null && dep.getEndtime() == null) {
			sql += " and installdate >= to_date('"
					+ DateUnit.getSimpleFormatsToday(dep.getBegintime())
					+ "','yyyy-MM-dd') and installdate <= to_date('"
					+ DateUnit.getSimpleFormatsToday() + "','yyyy-MM-dd')";
		}
		// ����ʱ�䶼��Ϊ��
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
	 * ����:������ ����:Mar 31, 2009
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