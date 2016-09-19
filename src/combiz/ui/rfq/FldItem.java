package combiz.ui.rfq;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.rfq.Rfqline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

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
	 * @TODO 字段上鼠标移开后调用该方法。本方法的作用是将库存项目的描述及类型赋值给另二个文本框
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-14 上午11:14:39
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
		//得到主窗体类
		Rfqline rl = (Rfqline)this.mainObject;
		//得到控件
		String itemnum = (String) this.getValueByColname("itemnum");
		if(itemnum!=null)
		{
			//通过控件的value值来查询结果
			List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
			List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+rl.getWarehouse()+"'");
			//如果结果集的大小>=1
			if(Itmlist.size()>0)
			{
				Item it = (Item)Itmlist.get(0);
				
				this.setValueByColname("description",it.getDescription());
				this.setValueByColname("INSPECTION",it.getInspectreq());
				this.setValueByColname("Modelnum",it.getModelnum());
				this.setValueByColname("Orderunit",it.getOrderunit());
				
				if(inventorylist.size()>0)
				{
					Inventory inventory = (Inventory) inventorylist.get(0);
					this.setValueByColname("stocktype", inventory.getStocktype());
				}
			}
		}
	}

}
