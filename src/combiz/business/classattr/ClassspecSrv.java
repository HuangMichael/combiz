package combiz.business.classattr;

import combiz.system.IBOBaseSrv;


public interface ClassspecSrv extends IBOBaseSrv {
	/**
	 * @TODO �̳и��豸���༼�����������ӵ���
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 ����07:05:55
	 */
	public void saveUpExtends(Object arg0) throws Exception;

	/**
	 * @TODO ���¼̳и��豸���༼���������ɸ�����
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 ����07:05:55
	 */
	public void saveDownExtends(Object arg0) throws Exception;
}
