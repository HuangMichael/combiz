package combiz.business.inventory;

import combiz.domain.inventory.*;
import combiz.system.IBOBaseImpl;

public class ItemeqbomImpl extends IBOBaseImpl
implements ItemeqbomSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}