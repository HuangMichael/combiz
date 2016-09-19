package combiz.ui.inventory;


import combiz.system.FieldAdapter;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;
/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 10:40:36 AM  Nov 7, 2008 
 * 功能：在对采购编码中的订购单位进行维护时，如果发放单位为空的话，把发放单位置为订购单位。
 * 捆绑表：item
 * 捆绑字段：orderunit
 */
public class FldItemunit extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}

	public void validate(Component component) 
	throws Exception 
	{
		
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		String orderunit = (String) this.getValueByColname("orderunit");
		String issueunit = (String) this.getValueByColname("issueunit");
		if(Util.isNotNull(orderunit)&& Util.isNull(issueunit))
		{
			this.setValueByColname("issueunit", orderunit);
			
		}
		
	}

}
