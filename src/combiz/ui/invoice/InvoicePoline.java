package combiz.ui.invoice;

import java.util.List;

import combiz.domain.invoice.Invoice;
import combiz.domain.po.Poline;
import combiz.system.ui.CommonListWindow;


/**
 * 功能：在发票行里复制采购单的时候，将满足条件的采购单行罗列出来。
 *@author 李阳
 *2008-1-24下午12:58:21
 */
public class InvoicePoline extends CommonListWindow {


	public InvoicePoline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		
		Invoice invoice = (Invoice) this.getOwnerWnd().getMainObject();
		String idstr = null;
		String ponum = (invoice.getPonum()!=null?(" and ponum='"+invoice.getPonum()+"'"):"");
		List polineList = this.getMainSrv().getBaseDao().findWithQuery(Poline.class, "ponum in(select t.ponum from Po t where t.vendor='"+invoice.getVendor()+"' and t.status is not null)" + ponum);
		for(int i=0;i<polineList.size();i++)
		{
			Poline poline = (Poline) polineList.get(i);
			Double recQty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(i.quantity) from Invoicematch i where i.ponum='"+poline.getPonum()+"' and i.polinenum="+poline.getPolinenum());
			
			Double invqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.invoiceqty) from Invoiceline t where t.ponum ='" + poline.getPonum() + "' and t.polinenum ='" + poline.getPolinenum() + "'");
			Double reqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + poline.getPonum() + "' and t.polinenum ='" + poline.getPolinenum() + "'");
			if(recQty==null)
				recQty=0D;
			if(invqty==null)
				invqty=0D;
			if(reqty==null)
				reqty=0D;
			if(poline.getReceivedqty()!=null && poline.getReceivedqty() > recQty &&(reqty-invqty)>0 && reqty > 0)
			{
				if(idstr==null)
					idstr = poline.getId() + "";
				else
					idstr = poline.getId() + "," + idstr;
			}
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
