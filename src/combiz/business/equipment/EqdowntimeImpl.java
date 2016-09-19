package combiz.business.equipment;

import combiz.domain.equipment.*;
import combiz.system.IBOBaseImpl;

public class EqdowntimeImpl extends IBOBaseImpl
implements EqdowntimeSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}