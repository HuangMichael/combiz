package combiz.business.budget;

import combiz.domain.budget.Budget;
import combiz.system.IBOBaseSrv;

public interface BudgetSrv  extends IBOBaseSrv
{
	/**
	 * 生成预算行记录
	 * brianhong  2008-11-24
	 */
	public void genBudgetLine(Budget budget,String itemStr,String ver)
	throws Exception;
	
	/**
	 *  删除所有预算行
	 * brianhong  2008-11-24
	 * @throws Exception
	 */
	public void deleteAllLine(Object obj)
	throws Exception;
	
	/**
	 * 启用或者禁用预算项目
	 * brianhong  2008-11-26
	 * @param obj
	 * @param enable
	 * @throws Exception
	 */
	public void enablebd(Object obj,boolean enable)
	throws Exception;
}
