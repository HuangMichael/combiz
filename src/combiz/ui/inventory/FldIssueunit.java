package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Inventory;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 11:15:07 AM  Oct 24, 2008 
 * 功能：如果库存中有余量的话，就不能修改该库存的发放单位（通过设置只读字段来实现）。
 * 捆绑表：inventory
 * 捆绑字段：issueunit
 */
public class FldIssueunit extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
		Inventory inventory = (Inventory) this.getMainObject();
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Invstock t where t.itemnum ='"+itemnum+"' and warehouse = '"+warehouse+"' and curbal >0");
		if(count >0)
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
