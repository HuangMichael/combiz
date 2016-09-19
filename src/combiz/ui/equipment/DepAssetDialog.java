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
					" depnum = '" + dep.getDepnum() + "' and status='未'");
			depreciationSrv.depAsset(setDepEquipListValue(list), dep);
		}
		this.refreshData();
		this.onClose();
		Messagebox.show("固资数据已经折旧！");
	}

	public List setDepEquipListValue(List list)throws Exception{
		Equipdep eqdep = (Equipdep) this.getMainObject();
		double ldepcost = 0.0; // 折旧额
		double ldeprate = 0.0; // 折旧率
		long lusedyears = 0; // 使用年
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
				throw new Exception("行号:"+equipdep.getLinenum()+"已使用年限不能为空");
			}
			if(equipdep.getPlanyears()==null){
				throw new Exception("行号:"+equipdep.getLinenum()+"预计使用年限不能为空");
			}
			if(equipdep.getStatus().equals("未")){
				if(ldepcost!=0){
					equipdep.setLdepcost(ldepcost);
					equipdep.setDepcost(ldepcost);
				}
				if(eqdep.getDepcount()!=null)
						equipdep.setDepcount(eqdep.getDepcount());// 折旧法
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
