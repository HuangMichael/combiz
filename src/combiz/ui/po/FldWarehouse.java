package combiz.ui.po;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

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
	 * @throws Exception 
	 * 
	 */
	public void action(Component component) throws Exception
	{

		//得到主窗体类
		Poline pl = (Poline)this.mainObject;
		String itemnum = pl.getItemnum();

		//得到控件
		
		//通过控件的value值来查询结果
		List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		
		//如果结果集的大小>=1
		if(Itmlist.size()>0){
		Item it = (Item)Itmlist.get(0);
		//将结果的一个属性值绑定给主窗体类的一个属性
		String orderunit = it.getOrderunit();
		
		this.setValueByColname("orderunit", orderunit);
		}else{
			this.setValueByColname("orderunit", "");
			
		}
//		//值绑定，如果不绑定，则之前的赋值将白费工夫
//		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
//		try {
//			recWnd.bandData();
//		} catch (Exception e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
	
		
	}

}
