package combiz.business.po;

import combiz.domain.po.Poline;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PolineSrv extends IBOBaseSrv
{
	/**
	 * ����:������
	 * ��Ӳɹ�������
	 * @param list
	 * @param obj
	 * @throws Exception
	 *  ����:Feb 25, 2009
	 */
	public void addPoline(List list,Object obj)throws Exception;
	/**
	 * ����:������
	 * �����豸������Ӳɹ�������
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Feb 25, 2009
	 */
	public void addPolinebypart(List list,Object obj)throws Exception;
	
	/**
	 * ����:������
	 * ��������BOM������Ӳɹ�������
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Feb 25, 2009
	 */
	public void addPolinebybom(List list,Object obj)throws Exception;
}

