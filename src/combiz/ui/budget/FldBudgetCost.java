package combiz.ui.budget;

import java.util.List;

import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetline;
import combiz.system.FieldAdapter;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;

public class FldBudgetCost 
extends FieldAdapter 
{
	@Override
	public void action(Component component) 
	throws Exception 
	{
	}

	@Override
	public void init(Component component)
	throws Exception 
	{
		Budgetline budgetline = (Budgetline) this.getMainObject();
		if(Util.getBoolean(budgetline.getHaschild()))
		{
			this.setReadonly(component);
		}
		else
			this.setNoReadonly(component);
	}

	@Override
	public void validate(Component component) 
	throws Exception
	{
	}

}
