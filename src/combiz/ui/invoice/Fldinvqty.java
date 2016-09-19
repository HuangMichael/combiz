package combiz.ui.invoice;

import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 9:40:26 AM  Oct 28, 2008 
 * ���ܣ������뷢Ʊ�����󣬽����ܼۺͺ�˰�ܼۼ��������
 * �������invoiceline
 * �����ֶΣ�invoiceqty
 */
public class Fldinvqty extends FieldAdapter {
	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{

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
	 * �������ܼ�
	 */
	public void action(Component component)
	throws Exception 
	{    
		Double invoiceqty = (Double) this.getValueByColname("invoiceqty");//ȡ�������ϵĶ��������ֶ�orderqty;
		Double taxunitcost = (Double) this.getValueByColname("taxunitcost");//ȡ�������ϵĵ����ֶ�unitcost;
		Double num = 0d;
		if(invoiceqty!=null && taxunitcost!=null)
		{
			num = invoiceqty * taxunitcost;  
			this.setValueByColname("taxlinecost", num);
		}
		
	}
	
	

}