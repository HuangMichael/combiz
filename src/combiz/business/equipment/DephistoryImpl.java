package combiz.business.equipment;

import combiz.domain.equipment.*;
import combiz.system.IBOBaseImpl;

public class DephistoryImpl extends IBOBaseImpl
implements DephistorySrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		this.deleteAllChild(obj, "equipdep");
		super.delete(obj);
	}
}