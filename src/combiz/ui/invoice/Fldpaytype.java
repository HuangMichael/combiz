package combiz.ui.invoice;

import combiz.domain.invoice.Invoice;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 10:01:04 AM  Oct 28, 2008 
 * 功能：当发票是采购发票的时候，将付款类型设置为只读。
 * 捆绑表：INVOICELINE
 * 捆绑字段：PAYTYPE
 */
public class Fldpaytype extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
		EditWindow  editwnd = (EditWindow) this.getRecWnd();
		MainWindow mainwnd = (MainWindow) editwnd.getOwnerWnd();
		Invoice invoice = (Invoice) mainwnd.getMainObject();
		String cntnum = invoice.getCntnum();
		if(Util.isNull(cntnum))//此发票是为采购单开的。
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
		}		
	}

	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{

	
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		
	}

}
