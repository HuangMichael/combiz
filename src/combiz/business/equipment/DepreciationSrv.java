package combiz.business.equipment;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface DepreciationSrv  extends IBOBaseSrv
{
	/**
	 * �ʲ��۾�
	 * ����:������
	 * ����:Mar 31, 2009
	 * @param list
	 * @param obj
	 * @throws Exception
	 */
	public void depAsset(List list,Object obj)throws Exception;
}
