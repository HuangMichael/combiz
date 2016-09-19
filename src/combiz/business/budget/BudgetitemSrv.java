package combiz.business.budget;

import combiz.domain.budget.Budgetitem;
import combiz.system.IBOBaseSrv;

public interface BudgetitemSrv  extends IBOBaseSrv
{
	/**
	 * ɾ��������汾�����м�¼
	 * brianhong  2008-11-24
	 * @param budgetitem
	 * @throws Exception
	 */
	public void deleteall(Budgetitem budgetitem)
	throws Exception;
	/**
	 * ���ư汾����
	 * brianhong  2008-11-24
	 * @param ver
	 */
	public void copyVersion(Budgetitem fromBd,Budgetitem toBd)
	throws Exception;
}
