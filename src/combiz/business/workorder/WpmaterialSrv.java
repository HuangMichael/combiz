package combiz.business.workorder;

import java.util.List;

import combiz.system.IBOBaseSrv;
/**
 * 
 * 方法
 * 
 * 作者:陈明锐
 * 功能
 * 日期Mar 3, 2009
 */
public interface WpmaterialSrv extends IBOBaseSrv
{
	/**
	 * 作者:陈明锐
	 * 根据我的收藏添加备件(工单)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Mar 3, 2009
	 */
	public void addwpmaterial(List list,Object obj)throws Exception;
	/**
	 * 作者:陈明锐
	 * 根据备件添加物料(工单)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Mar 3, 2009
	 */
	public void addwpmaterialbypart(List list,Object obj)throws Exception;
	

	/**
	 * 作者:陈明锐
	 * 根据我的收藏夹添加备件(物资领用)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Mar 3, 2009
	 */
	public void addwpmaterialbymatreq(List list,Object obj)throws Exception;
	
	/**
	 * 作者:陈明锐
	 * 根据设备备件添加(物资领用)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Mar 3, 2009
	 */
	public void addwpmaterialbypartmatreq(List list,Object obj)throws Exception;
	
	/**
	 * 作者:陈明锐
	 * 根据物资编码添加其BOM备件(工单)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Mar 3, 2009
	 */
	public void addwpmaterialbybom(List list,Object obj)throws Exception;
	
	/**
	 * 作者:陈明锐
	 * 根据物资编码添加其BOM备件(物资领用)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * 日期:Mar 3, 2009
	 */
	public void addwpmaterialbybommatreq(List list,Object obj)throws Exception;
}
