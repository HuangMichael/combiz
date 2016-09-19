package combiz.business.tool;

import combiz.domain.tool.*;
import combiz.system.IBOBaseImpl;

public class ToolspecImpl extends IBOBaseImpl
implements ToolspecSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}