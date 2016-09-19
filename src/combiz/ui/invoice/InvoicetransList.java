package combiz.ui.invoice;

import java.util.Date;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.invoice.Invoicetrans;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

/**
 * @author 李建红 E-mail:superyang4208731@yahoo.com.cn
 * Oct 17, 200910:23:12 AM
 * 功能：
 * 捆绑表：
 * 捆绑字段：
 */
public class InvoicetransList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	
	public InvoicetransList()
	{
		super();
	}


	/* ljh
	 * @see combiz.system.ui.ListWindow#addNew()
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		//InvoiceWindow parentWnd = (InvoiceWindow)ownerWnd;
		Invoiceline inv = (Invoiceline)this.getOwnerWnd().getMainObject();
		
		Invoicetrans il = new Invoicetrans();
		il.setEnterby(this.getLaborInfo().getLabornum());
		il.setTransdate(new Date());
		il.setInvoicenum(inv.getInvoicenum());
		il.setInvoicelinenum(inv.getInvoicelinenum());
		
		
		this.mainObject = il;
		return true;
	}

/*	@Override
	public void onRowSelected() {
		// TODO 自动生成方法存根

		ListWindow listWnd = (ListWindow) this.getFellow("invoiceline");
		Invoiceline invoiceline = (Invoiceline) this.getMainObject();
		if(invoiceline.getState().equals("已校验"))
		{
			listWnd.setReadonlyList(true);
		}
		else
		{
			listWnd.setReadonlyList(false);
		}
//		super.onRowSelected();
	}*/
	
	
	
}
