package combiz.ui.invoice;

import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 9:40:26 AM  Oct 28, 2008 
 * 功能：当输入发票数量和含税总价后，将含税总价计算出来。
 * 捆绑表：invoiceline
 * 捆绑字段：taxlinecost
 */
public class Fldtaxunitcost extends FieldAdapter {
	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{

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
	 * 计算行总价
	 */
	public void action(Component component)
	throws Exception 
	{    
		Double invoiceqty = (Double) this.getValueByColname("invoiceqty");//取出界面上的订购数量字段orderqty;
		Double taxunitcost = (Double) this.getValueByColname("taxunitcost");//取出界面上的单价字段unitcost;
		Double taxlinecost = 0d;
		if(invoiceqty!=null && taxunitcost!=null)
		{
			taxlinecost = taxunitcost*invoiceqty;  
			this.setValueByColname("taxlinecost", taxlinecost);
		}
		
	}
	
	

}
