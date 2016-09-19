package combiz.ui.equipment.kpi;

import combiz.domain.equipment.Eqdowntime;
import combiz.domain.equipment.Equipment;
import combiz.domain.kpi.Kpitarget;
import combiz.system.IBOBaseSrv;
import combiz.system.IBOSrvUtil;
import combiz.system.kpi.KpiCustomValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 陈明锐
 * @author Administrator
 *
 */
public class EqdowntimeRate implements KpiCustomValue{

	public Double execute(Kpitarget kpitarget, IBOBaseSrv ibobaseSrv, Object mainObject)
			throws Exception {
		double rate = 0.00;
		// TODO Auto-generated method stub
		///停机时间/(当前时间-停机设备的运行开始时间)=设备停机率 
//		List list = IBOSrvUtil.getBaseDao().findWithQuery(Equipment.class,"eqnum='10000029'");
//		Double obj = (Double)IBOSrvUtil.getBaseSrv().getBaseDao().selectSumByHql("select sum(e.downtime) from Eqdowntime e where e.eqnum='10000029'");
//		if(list != null && obj !=null){
//			Equipment equipment = (Equipment)list.get(0);
//			Date date = equipment.getRundate();
//			Date now = new Date();
//			long runtime = (now.getTime() - date.getTime())/3600000; //设备运行小时数 
//			Double dtime= obj;//设备停机时间总和 
//			double downtime = dtime;
//			double druntime = (double)runtime;//设备运行时间总和 
//			rate = downtime/druntime; //停机率 
//		}
//		return round(rate,5,BigDecimal.ROUND_UP);
		return 40.0;
	}
	public static double round(double value, int scale, int roundingMode) {   
        BigDecimal bd = new BigDecimal(value);   
        bd = bd.setScale(scale, roundingMode);   
        double d = bd.doubleValue();   
        bd = null;   
        return d;   
    } 
}
