package combiz.business.po;

import combiz.domain.po.*;
import combiz.system.IBOBaseImpl;

public class PoservicedetailImpl extends IBOBaseImpl
implements PoservicedetailSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}