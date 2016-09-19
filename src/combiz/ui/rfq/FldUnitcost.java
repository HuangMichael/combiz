package combiz.ui.rfq;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldUnitcost extends FieldAdapter
{	
	
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
		Double orderqty = (Double) this.getValueByColname("ORDERQTY");
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if(orderqty!=null && unitcost!=null)
		{
			this.setValueByColname("linecost", orderqty * unitcost);
		}
	}
	
	

}
