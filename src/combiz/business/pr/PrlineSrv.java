package combiz.business.pr;

import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PrlineSrv extends IBOBaseSrv
{
	/**
	 *  作者:陈明锐
	 * 添加采购单行
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Feb 25, 2009
	 */
	public void addPrline(List list,Object obj)throws Exception;
	/**
	 * 作者:陈明锐
	 * 根据设备备件添加采购单行
	 * @param list
	 * @param obj
	 * @throws Exception
	 *  日期:Feb 25, 2009
	 */
	public void addPrlinebypart(List list,Object obj)throws Exception;
	
	/**
	 *  作者:陈明锐
	 * 根据物资BOM备件添加采购单行
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Feb 25, 2009
	 */
	public void addPrlinebybom(List list,Object obj)throws Exception;
}
