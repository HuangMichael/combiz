package combiz.business.cal;

import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calshift;
import combiz.domain.cal.Calshiftday;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface CalshiftdaySrv extends IBOBaseSrv {

	/**
	 * 点击生成工作排程表 brianhong 2007-10-29
	 * 
	 * @throws Exception
	 */
	public void calwp(Calendar cal, Calshift calshift) throws Exception;
}
