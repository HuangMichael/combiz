package combiz.business.equipment;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface EquipdepSrv  extends IBOBaseSrv
{
	/**
	 * �����۾ɵ������۾ɵ���ϸ����
	 * ����:������
	 * ����:Mar 31, 2009
	 * @param list ���е���ϸ
	 * @param obj �۾ɵ�������
	 * @throws Exception
	 */
	public void creatList(List list,Object obj)throws Exception;
}
