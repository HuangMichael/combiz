package combiz.business.equipment;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface EquipdepSrv  extends IBOBaseSrv
{
	/**
	 * 根据折旧单生成折旧单明细数据
	 * 作者:陈明锐
	 * 日期:Mar 31, 2009
	 * @param list 现有的明细
	 * @param obj 折旧单主对象
	 * @throws Exception
	 */
	public void creatList(List list,Object obj)throws Exception;
}
