package combiz.ui.equipment;

import combiz.business.equipment.DepreciationSrv;
import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.ListWindow;
import combiz.util.Util;

import java.util.ArrayList;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class DepAssetDialog extends combiz.system.ui.CommonDialog {
	public void depssets() throws Exception {
		Depreciation dep = (Depreciation) this.getOwnerWnd().getMainObject();
		ListWindow listWnd = (ListWindow) this.getOwnerWnd().getFellow(
				"equipdep");
		if (listWnd == null)
			return;
		DepreciationSrv depreciationSrv = (DepreciationSrv) IBOSrvUtil
				.getSrv("depreciation");
		if (listWnd.getSelectObjects() != null
				&& listWnd.getSelectObjects().size() > 0) {
			depreciationSrv.depAsset(setDepEquipListValue(listWnd
					.getSelectObjects()), dep);
		} else {
			List list = IBOSrvUtil.getBaseDao().findWithQuery(Equipdep.class,
					" depnum = '" + dep.getDepnum() + "' and status='δ'");
			depreciationSrv.depAsset(setDepEquipListValue(list), dep);
		}
		this.refreshData();
		this.onClose();
		Messagebox.show("���������Ѿ��۾ɣ�");
	}

	public List setDepEquipListValue(List list)throws Exception{
		Equipdep eqdep = (Equipdep) this.getMainObject();
		double ldepcost = 0.0; // �۾ɶ�
		double ldeprate = 0.0; // �۾���
		long lusedyears = 0; // ʹ����
		if(eqdep.getLdepcost()!=null)
			ldepcost = eqdep.getLdepcost();
		if(eqdep.getLdeprate()!=null)
			ldeprate = eqdep.getLdeprate();
		if(eqdep.getLusedyears()!=null)
			lusedyears  = eqdep.getLusedyears();
		List arrayList = new ArrayList();
		for(int i=0;i<list.size();i++){
			Equipdep equipdep = (Equipdep)list.get(i);
			if(equipdep.getUsedyears()==null){
				throw new Exception("�к�:"+equipdep.getLinenum()+"��ʹ�����޲���Ϊ��");
			}
			if(equipdep.getPlanyears()==null){
				throw new Exception("�к�:"+equipdep.getLinenum()+"Ԥ��ʹ�����޲���Ϊ��");
			}
			if(equipdep.getStatus().equals("δ")){
				if(ldepcost!=0){
					equipdep.setLdepcost(ldepcost);
					equipdep.setDepcost(ldepcost);
				}
				if(eqdep.getDepcount()!=null)
						equipdep.setDepcount(eqdep.getDepcount());// �۾ɷ�
				if(lusedyears!=0)
						equipdep.setUsedyears(lusedyears);
				if(ldeprate!=0){
						equipdep.setLdeprate(ldeprate);
						equipdep.setDeprate(ldeprate);
				}
				arrayList.add(equipdep);
			}
		}
		return arrayList;
	}
}
