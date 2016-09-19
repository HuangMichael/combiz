package combiz.ui.invoice;

import java.util.List;

import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoicetrans;
import combiz.domain.po.Poline;
import combiz.system.ui.CommonListWindow;


/**
 * 功能：在发票行里复制采购单的时候，将满足条件的采购单行罗列出来。
 *@author ljh
 *2009-1-24下午12:58:21
 */
public class Invoiceinvtrans extends CommonListWindow {


	public Invoiceinvtrans() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		
		Invoice invoice = (Invoice) this.getOwnerWnd().getMainObject();
		String idstr = null;
		//String invoicenum = (invoice.getInvoicenum()!=null?(" and invoicenum='"+invoice.getInvoicenum()+"'"):"");
		List polineList = this.getMainSrv().getBaseDao().findWithQuery(Invoicetrans.class,  "invoicenum='"+invoice.getInvoicenum()+"'");
		for(int i=0;i<polineList.size();i++)
		{
			Invoicetrans invoicetrans = (Invoicetrans) polineList.get(i);
			
			
				if(idstr==null)
					idstr = invoicetrans.getId() + "";
				else
					idstr = invoicetrans.getId() + "," + idstr;
			
		}
		if(idstr!=null)
		{
			idstr = "id in(" + idstr + ")";
		}
		else
		{
			idstr = "1=2";
		}
			
		this.setQueryString(idstr);
		this.refreshData();
	}


}
