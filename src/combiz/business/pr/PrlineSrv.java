package combiz.business.pr;

import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PrlineSrv extends IBOBaseSrv
{
	/**
	 *  ����:������
	 * ��Ӳɹ�����
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Feb 25, 2009
	 */
	public void addPrline(List list,Object obj)throws Exception;
	/**
	 * ����:������
	 * �����豸������Ӳɹ�����
	 * @param list
	 * @param obj
	 * @throws Exception
	 *  ����:Feb 25, 2009
	 */
	public void addPrlinebypart(List list,Object obj)throws Exception;
	
	/**
	 *  ����:������
	 * ��������BOM������Ӳɹ�����
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Feb 25, 2009
	 */
	public void addPrlinebybom(List list,Object obj)throws Exception;
}
