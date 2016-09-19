package combiz.ui.invoice;

import combiz.domain.invoice.Invoice;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 10:01:04 AM  Oct 28, 2008 
 * ���ܣ�����Ʊ�ǲɹ���Ʊ��ʱ�򣬽�������������Ϊֻ����
 * �����INVOICELINE
 * �����ֶΣ�PAYTYPE
 */
public class Fldpaytype extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		EditWindow  editwnd = (EditWindow) this.getRecWnd();
		MainWindow mainwnd = (MainWindow) editwnd.getOwnerWnd();
		Invoice invoice = (Invoice) mainwnd.getMainObject();
		String cntnum = invoice.getCntnum();
		if(Util.isNull(cntnum))//�˷�Ʊ��Ϊ�ɹ������ġ�
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
		}		
	}

	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
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
