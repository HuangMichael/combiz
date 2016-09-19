package combiz.ui.invoice;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

/**
 * @author ��� E-mail:superyang4208731@yahoo.com.cn
 * Oct 17, 200810:23:12 AM
 * ���ܣ�
 * �����
 * �����ֶΣ�
 */
public class InvoicelineList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	/* 
	 * ���ܣ��ж����Ƿ�Ϊֻ��
	 * ���ߣ����
	 * ���ڣ�Oct 17, 200810:23:19 AM
	 */
	@Override
	public void initRowData(Listitem arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		String labornum = this.getLaborInfo().getLabornum();
		Invoiceline invoiceline = (Invoiceline) arg1;
		Invoice invoice = (Invoice) this.getOwnerWnd().getMainObject();
		if(Util.isNull(invoice.getCntnum()))
		{
			if(invoiceline.getState().equals("��У��"))
			{
				this.setReadonlyObject(arg0, true);
			}
			else
			{
				//if (labornum.equals(invoiceline.getEnterby())) {
					this.setReadonlyObject(arg0, false);
				//}else{
				//this.setReadonlyObject(arg0, true);
				//}
			}
			
		}
		
		
	}

	public InvoicelineList()
	{
		super();
	}

	/**
	 * 
	 * @TODO �����Ӱ�ťʱȷ�����¼��������һ�ŷ�Ʊ��
	 * @return
	 * @throws Exception
	 * @����� 2007-8-21 ����11:02:51
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		//InvoiceWindow parentWnd = (InvoiceWindow)ownerWnd;
		Invoice inv = (Invoice)this.getOwnerWnd().getMainObject();
		if(Util.isNull(inv.getCntnum()))
		{
			throw new Exception("���������ϸ�У�����ѡ������µ����ɷ�Ʊ�������Ӳ�����");
		}
		Invoiceline il = new Invoiceline();
		
		int count = this.mainSrv.getRowCountByWhere(il, "invoicenum='"+inv.getInvoicenum()+"' and ponum='"+inv.getPonum()+"'")+1;	
		il.setInvoicenum(inv.getInvoicenum());
		il.setInvoicelinenum(Long.parseLong(Integer.toString(count)));
		//il.setSitenum(inv.getSitenum());
		//il.setCorpnum(inv.getCorpnum());
		il.setState("��У��");
		inv.setStatus("У�����");
		this.getMainSrv().getBaseDao().updateObject(inv);
		this.mainObject = il;
		return true;
	}

	@Override
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
	}
	
	
	
}
