package combiz.ui.pr;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldItem extends FieldAdapter
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

		Prline pl = (Prline)this.mainObject;
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		String orderunit = null;
		Double avgcost = 0d;
		List Itemlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		//如果结果集的大小>=1
		if(Itemlist.size()>0){
			Item it = (Item)Itemlist.get(0);
			pl.setDescription(it.getDescription());
			pl.setInspection(it.getInspectreq());
			if(Util.isNotNull(warehouse))
			{
				List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+warehouse+"' ");
				if(inventorylist.size()>0)
				{
					Inventory inventory = (Inventory) inventorylist.get(0);
					orderunit = inventory.getOrderunit();
					avgcost = inventory.getAvgcost();
				}
				else
				{
					orderunit = it.getOrderunit();
				}
				this.setValueByColname("orderunit", orderunit);
				this.setValueByColname("unitcost", avgcost);


			}

		}else
		{
			this.setValueByColname("description", "");
		}
		
	}

}
