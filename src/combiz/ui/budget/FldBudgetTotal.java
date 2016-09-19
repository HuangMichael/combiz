package combiz.ui.budget;

import com.inbasis.zk.ui.Component;

import combiz.domain.budget.Budget;
import combiz.system.FieldAdapter;

public class FldBudgetTotal
extends FieldAdapter 
{

	
	@Override
	public Object initValue() throws Exception
	{
		Budget budget = (Budget) this.getMainObject();
		String budnum = budget.getBudnum();
		return this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.budget) from Budgetline t where t.budnum='"+budnum+"' and (parent is null or parent='')");
	}

	@Override
	public void action(Component component) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Component component) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Component component) throws Exception
	{
		// TODO Auto-generated method stub
		
	}
	
}
