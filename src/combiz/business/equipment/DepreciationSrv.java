package combiz.business.equipment;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface DepreciationSrv  extends IBOBaseSrv
{
	/**
	 * 资产折旧
	 * 作者:陈明锐
	 * 日期:Mar 31, 2009
	 * @param list
	 * @param obj
	 * @throws Exception
	 */
	public void depAsset(List list,Object obj)throws Exception;
}
