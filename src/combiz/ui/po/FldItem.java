package combiz.ui.po;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import java.util.List;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
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

		//得到主窗体类
		Poline pl = (Poline)this.mainObject;
		//得到控件
		IBandbox itembox = (IBandbox) component;
		String itemnum = itembox.getValue();
		//通过控件的value值来查询结果
		Item item = (Item) this.mainSrv.getBaseDao().findUniqueBy(Item.class, "itemnum",itemnum);
		List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+pl.getWarehouse()+"' ");
		//如果结果集的大小>=1
		if (item!=null)
		{
			//将结果的一个属性值绑定给主窗体类的一个属性
			this.setValueByColname("description", item.getDescription());
			this.setValueByColname("orderunit", item.getOrderunit());
			this.setValueByColname("modelnum", item.getModelnum());
			this.setValueByColname("orderunit", item.getOrderunit());
			if(inventorylist.size()>0)
			{
				Inventory inventory = (Inventory) inventorylist.get(0);
				this.setValueByColname("stocktype", inventory.getStocktype());
			}
		}

	

	}

}
