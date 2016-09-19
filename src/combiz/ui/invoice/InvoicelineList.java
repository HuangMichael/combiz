package combiz.ui.invoice;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

/**
 * @author 李建红 E-mail:superyang4208731@yahoo.com.cn
 * Oct 17, 200810:23:12 AM
 * 功能：
 * 捆绑表：
 * 捆绑字段：
 */
public class InvoicelineList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	/* 
	 * 功能：判断行是否为只读
	 * 作者：李建红
	 * 日期：Oct 17, 200810:23:19 AM
	 */
	@Override
	public void initRowData(Listitem arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		String labornum = this.getLaborInfo().getLabornum();
		Invoiceline invoiceline = (Invoiceline) arg1;
		Invoice invoice = (Invoice) this.getOwnerWnd().getMainObject();
		if(Util.isNull(invoice.getCntnum()))
		{
			if(invoiceline.getState().equals("已校验"))
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
	 * @TODO 点击添加按钮时确发此事件。。添加一张发票行
	 * @return
	 * @throws Exception
	 * @蒋祖兵 2007-8-21 上午11:02:51
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		//InvoiceWindow parentWnd = (InvoiceWindow)ownerWnd;
		Invoice inv = (Invoice)this.getOwnerWnd().getMainObject();
		if(Util.isNull(inv.getCntnum()))
		{
			throw new Exception("不能添加明细行，请在选择操作下的生成发票行完成添加操作！");
		}
		Invoiceline il = new Invoiceline();
		
		int count = this.mainSrv.getRowCountByWhere(il, "invoicenum='"+inv.getInvoicenum()+"' and ponum='"+inv.getPonum()+"'")+1;	
		il.setInvoicenum(inv.getInvoicenum());
		il.setInvoicelinenum(Long.parseLong(Integer.toString(count)));
		//il.setSitenum(inv.getSitenum());
		//il.setCorpnum(inv.getCorpnum());
		il.setState("已校验");
		inv.setStatus("校验完成");
		this.getMainSrv().getBaseDao().updateObject(inv);
		this.mainObject = il;
		return true;
	}

	@Override
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
	}
	
	
	
}
