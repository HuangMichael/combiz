package combiz.business.test;

import combiz.domain.kpi.Kpitarget;
import combiz.system.IBOBaseSrv;
import combiz.system.kpi.KpiCustomValue;

import java.util.List;

public class CustomValue implements KpiCustomValue
{
	public Double execute(Kpitarget arg0, IBOBaseSrv ibobaseSrv, Object mainObject)
	throws Exception 
	{
		List list = ibobaseSrv.getBaseDao().selectListByHql("select count(*) from ibstables");
		return Double.valueOf(list.get(0).toString());
	}

}
