package combiz.ui.pr;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldWarehouse extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}

	/**
	 * 
	 * @TODO 字段上鼠标移开后调用该方法。本方法的作用是将供应商的联系人赋值给另一个文本框
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	{

		Prline pl = (Prline)this.getMainObject();
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		String orderunit = null;
		Double avgcost = 0d;
		
		List itemlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		if(itemlist.size()>0)
		{
			Item item = (Item) itemlist.get(0);
			orderunit = item.getOrderunit();
		}
		
		if(Util.isNotNull(warehouse))
		{
			List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+warehouse+"' ");
			if(inventorylist.size()>0)
			{
				Inventory inventory = (Inventory) inventorylist.get(0);
				orderunit = inventory.getOrderunit();
				avgcost = inventory.getAvgcost();
			}
		}
		this.setValueByColname("orderunit", orderunit);
		this.setValueByColname("unitcost", avgcost);
	
		
	}

}
