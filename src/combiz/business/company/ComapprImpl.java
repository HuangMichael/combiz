package combiz.business.company;

import combiz.domain.company.*;
import combiz.system.IBOBaseImpl;

public class ComapprImpl extends IBOBaseImpl
implements ComapprSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}