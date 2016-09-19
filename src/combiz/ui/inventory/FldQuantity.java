package combiz.ui.inventory;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldQuantity extends FieldAdapter
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
		Double itemqty = (Double) this.getValueByColname("itemqty");
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if(itemqty!=null && unitcost!=null)
		{
			Component target = component.getFellow("wpmaterial.linecost");
			this.setValueByColname("linecost", itemqty * unitcost);
		}
	}
	
	

}
