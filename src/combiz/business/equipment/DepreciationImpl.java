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
	 * ���� �۾ɹ���
	 * 
	 * ����:������
	 * ����
	 * ����Apr 7, 2009
	 */
	public void depAsset(List list, Object obj) throws Exception {/*
		Depreciation dep = (Depreciation)obj;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Equipdep equipdep = (Equipdep)list.get(i);
				//�����豸���� nowcost,depmark;
				Equipment equipment = (Equipment)this.findWithQuery(Equipment.class, "eqnum = '"+equipdep.getEqnum()+"'").get(0);
				//�����۾ɶ��ޱ仯,�����۾����ޱ仯,�Կ��ܱ���������۾ɷ�������ʹ���� ���¼����۾ɶ�
				if(equipdep.getLdepcost()==null && equipdep.getLdeprate()==null ){
					if(equipdep.getDepcount()!=null){
						dep.setDepfaction(equipdep.getDepcount());//���ѡ�����µĵ��۾ɷ�
					}
					equipdep.setDepcost(Util.countDepcost(dep.getDepfaction(), dep.getScraprate(), equipment.getPlanyears(), equipdep.getUsedyears(), equipment.getTotalcost()));// �۾ɶ�
				}
				//�����۾ɶ��ޱ仯,�������۾���ͳ�����۾ɶ�
				if(equipdep.getLdepcost()==null && equipdep.getLdeprate()!=null && equipdep.getLdeprate()!=0){
					equipdep.setDepcost(Util.doubleTodouble((equipdep.getLdeprate()*equipment.getTotalcost()*equipdep.getUsedyears())));
				}
				//�����۾ɶ�仯,��������۾���
				if(equipdep.getLdepcost()!=null && equipdep.getLdepcost()!=0){
					double total = equipment.getTotalcost()*equipdep.getUsedyears();
					equipdep.setDeprate(equipdep.getLdepcost()/total);
				}
				//��ԭ�ܼ۸����۾ɶ��ʱ����㵱ǰֵ;
				if(equipdep.getDepcost()!=null && equipdep.getDepcost()!=0 && equipment.getTotalcost() - equipdep.getDepcost()>0 ){
					//���¼��㵱ǰֵ
					equipdep.setNowcost(equipment.getTotalcost() - equipdep.getDepcost());
				}else{
					//���۾ɶ����ԭ�ܼ۵�ʱ��,��ǰ��Ϊ0;
					equipdep.setDepcost(equipment.getTotalcost());
					equipdep.setNowcost(0.00);
					equipdep.setDeprate(1.0);
				}
				equipdep.setStatus("���۾�");
				this.update(equipdep);//������ϸ��
				
				/////////////////////�豸//////////////////////////
				//equipment.setNowcost(equipdep.getNowcost());
				this.update(equipment);//�����豸��
				/////////////////////�豸////////////////////////////
				
				//������ʷ��
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