package combiz.ui.budget;

import java.util.List;

import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetline;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldBudgetitem 
extends FieldAdapter 
{
	@Override
	public void action(Component component) 
	throws Exception 
	{
		Budgetline budgetline = (Budgetline) this.getMainObject();
		String buditem = budgetline.getBuditem();
		List lists = this.getMainSrv().getBaseDao().findWithQuery(Budgetitem.class, "buditem='"+buditem+"'");
		if(lists.size()>0)
		{
			Budgetitem budgetitem = (Budgetitem) lists.get(0);
			this.setValueByColname("budclass", budgetitem.getBudclass());
			this.setValueByColname("Description", budgetitem.getDescription());
			this.setValueByColname("budperiod", budgetitem.getBudperiod());
			this.setValueByColname("Budtype", budgetitem.getBudtype());
			this.setValueByColname("Childclass", budgetitem.getChildclass());
			this.setValueByColname("Meaunit", budgetitem.getMeaunit());
			this.setValueByColname("Orderby", budgetitem.getOrderby());
			this.setValueByColname("Remark", budgetitem.getRemark());
		}
	}

	@Override
	public void init(Component component)
	throws Exception 
	{
	}

	@Override
	public void validate(Component component) 
	throws Exception
	{
	}

}
