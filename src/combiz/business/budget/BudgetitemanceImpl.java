package combiz.business.budget;

import combiz.domain.budget.*;
import combiz.system.IBOBaseImpl;

public class BudgetitemanceImpl extends IBOBaseImpl
implements BudgetitemanceSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}