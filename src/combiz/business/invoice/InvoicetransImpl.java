package combiz.business.invoice;

import combiz.domain.invoice.*;
import combiz.system.IBOBaseImpl;

public class InvoicetransImpl extends IBOBaseImpl
implements InvoicetransSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
}