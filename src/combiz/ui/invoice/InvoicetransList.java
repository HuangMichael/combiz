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
 * @author ��� E-mail:superyang4208731@yahoo.com.cn
 * Oct 17, 200910:23:12 AM
 * ���ܣ�
 * �����
 * �����ֶΣ�
 */
public class InvoicetransList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	
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
		//��ȡ�������������
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
		// TODO �Զ����ɷ������

		ListWindow listWnd = (ListWindow) this.getFellow("invoiceline");
		Invoiceline invoiceline = (Invoiceline) this.getMainObject();
		if(invoiceline.getState().equals("��У��"))
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
