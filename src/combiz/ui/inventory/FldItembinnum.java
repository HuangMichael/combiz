package combiz.ui.inventory;


import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.invoice.Invoice;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;
import combiz.system.util.Util;

import java.util.List;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

public class FldItembinnum extends FieldAdapter
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
		//得到主窗体类
		Invusetrans inv = (Invusetrans)this.mainObject;
		//得到控件
		Bandbox argbin = (Bandbox)component;
		String binnumin = argbin.getValue();
		List Itemlist = null;
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");

		//else
		{
			Double quantity= (Double) this.getValueByColname("quantity");//发放数量
			
			//通过控件的value值来查询结果
			if(Util.isNull(binnumin))
			{
				Itemlist = this.mainSrv.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse='"+warehouse+"' ");
			}
			else
			{
				String binnum = (String) this.getValueByColname("binnum");//箱柜编号
				Itemlist = this.mainSrv.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and binnum='"+binnum+"' and warehouse='"+warehouse+"'");
			}
			
			
//			//如果结果集的大小>=1
//			if (Itemlist.size() > 0)
//			{
//				Invstock item = (Invstock)Itemlist.get(0);
//			//将结果的一个属性值绑定给主窗体类的一个属性
//			Double curbal = item.getCurbal();
//			if(curbal-quantity<0)
//			{
//				this.setValueByColname("binnum", null);
//				Messagebox.show("要发放数量大于该箱柜库存余量，将无法发放，请核实！");
//			}
//			
//			//this.setValueByColname("description", it.getDescription());
//			//this.setValueByColname("stocktype", it.getStocktype());
//			}
//			else
//				Messagebox.show("没有库存余量，请核对库存项目和箱柜");
			
		}
		

	}

	@Override
	public String getListWhere(Component ibandbox) 
	{
		//从编辑窗口获取对应的LIST窗口
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invusetrans inv =(Invusetrans) this.getMainObject();
        String itemnum = inv.getItemnum();
        String warehouse = inv.getWarehouse();
        if(warehouse.trim()!= null)
        {
        	if (itemnum != null  && warehouse != null)
        		//在应用程序PDXL下面才生效
        		{
        			String whereStr = "itemnum = '" + itemnum +"' and warehouse = '" + warehouse +"' and curbal >0";
        			return whereStr;
        		}
        		else
        			return "1=2";
        }
        else
 
        return "1=2";
       
		
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		String itemnum = (String) this.getValueByColname("itemnum");
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		String lottype = null;
		if(itemlist.size()>0)
		{
			Item item = (Item) itemlist.get(0);
			lottype = item.getLottype();
		}
		Double qty = 0d;
		String warehouse = (String) this.getValueByColname("warehouse");
		Double quantity = (Double) this.getValueByColname("quantity");
		Double reqqty = (Double) this.getValueByColname("reqqty");
		if(reqqty==null)
		{
			reqqty = 0d;
		}
		String binnum = (String) this.getValueByColname("binnum");
		String lotnum = (String) this.getValueByColname("lotnum");
		Invstock invstock = null;
		List curballist = null;
		Double curbal = 0d;
		if(lottype.equals("非批次管理"))
		{
			if(binnum == null)
				//List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and nvl(binnum,'空') = '" + binnum +"'");
			{
				curballist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum is null");
			}
			else
			{
				curballist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum = '" + binnum +"'");
			}
			if(curballist.size()>0)
			{
				invstock =  (Invstock) curballist.get(0);
				curbal = invstock.getCurbal();
			}


		}
		else
		{
			List invlotlist =this.getMainSrv().getBaseDao().findWithQuery(Invlot.class, "itemnum = '"+itemnum+"' and warehouse ='"+warehouse+"' and lotnum = '"+lotnum+"'");
			if(invlotlist.size()>0)
			{
				Invlot invlot = (Invlot) invlotlist.get(0);
				Double lotcost = invlot.getLotcost();
				this.setValueByColname("unitcost", lotcost);
				this.setValueByColname("linecost", lotcost * quantity );
			}
			curballist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and lotnum = '" + lotnum +"'");
			if(curballist.size()>0)
			{
				invstock =  (Invstock) curballist.get(0);
				curbal = invstock.getCurbal();
			}
		}
		if(reqqty - curbal <0)
		{
			qty = reqqty;
		}
		else
		{
			qty = curbal;
		}
		
		this.setValueByColname("quantity", qty);
			
	}
}
