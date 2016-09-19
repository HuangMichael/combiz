package combiz.business.classattr;

import combiz.system.IBOBaseSrv;


public interface ClassspecSrv extends IBOBaseSrv {
	/**
	 * @TODO 继承父设备分类技术参数，由子调用
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 下午07:05:55
	 */
	public void saveUpExtends(Object arg0) throws Exception;

	/**
	 * @TODO 向下继承父设备分类技术参数，由父调用
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 下午07:05:55
	 */
	public void saveDownExtends(Object arg0) throws Exception;
}
