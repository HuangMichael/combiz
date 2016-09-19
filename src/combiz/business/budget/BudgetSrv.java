package combiz.business.budget;

import combiz.domain.budget.Budget;
import combiz.system.IBOBaseSrv;

public interface BudgetSrv  extends IBOBaseSrv
{
	/**
	 * ����Ԥ���м�¼
	 * brianhong  2008-11-24
	 */
	public void genBudgetLine(Budget budget,String itemStr,String ver)
	throws Exception;
	
	/**
	 *  ɾ������Ԥ����
	 * brianhong  2008-11-24
	 * @throws Exception
	 */
	public void deleteAllLine(Object obj)
	throws Exception;
	
	/**
	 * ���û��߽���Ԥ����Ŀ
	 * brianhong  2008-11-26
	 * @param obj
	 * @param enable
	 * @throws Exception
	 */
	public void enablebd(Object obj,boolean enable)
	throws Exception;
}
