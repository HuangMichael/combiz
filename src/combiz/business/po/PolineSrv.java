package combiz.business.po;

import combiz.domain.po.Poline;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PolineSrv extends IBOBaseSrv
{
	/**
	 * 作者:陈明锐
	 * 添加采购订单行
	 * @param list
	 * @param obj
	 * @throws Exception
	 *  日期:Feb 25, 2009
	 */
	public void addPoline(List list,Object obj)throws Exception;
	/**
	 * 作者:陈明锐
	 * 根据设备备件添加采购订单行
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Feb 25, 2009
	 */
	public void addPolinebypart(List list,Object obj)throws Exception;
	
	/**
	 * 作者:陈明锐
	 * 根据物资BOM备件添加采购订单行
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Feb 25, 2009
	 */
	public void addPolinebybom(List list,Object obj)throws Exception;
}

