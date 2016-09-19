package combiz.business.budget;

import combiz.domain.budget.Budgetitem;
import combiz.system.IBOBaseSrv;

public interface BudgetitemSrv  extends IBOBaseSrv
{
	/**
	 * 删除主对象版本的所有记录
	 * brianhong  2008-11-24
	 * @param budgetitem
	 * @throws Exception
	 */
	public void deleteall(Budgetitem budgetitem)
	throws Exception;
	/**
	 * 复制版本数据
	 * brianhong  2008-11-24
	 * @param ver
	 */
	public void copyVersion(Budgetitem fromBd,Budgetitem toBd)
	throws Exception;
}
