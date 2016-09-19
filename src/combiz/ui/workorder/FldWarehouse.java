package combiz.ui.workorder;


import combiz.domain.corp.Corpsiteauth;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.domain.user.Ibsgroups;
import combiz.domain.user.Ibsgroupusers;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2:17:02 PM  Nov 27, 2008 
 * 功能：
 * 捆绑表：
 * 捆绑字段：
 */
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
	*@author 李阳
	*功能：在工单里填写物料申请，在值列表里选择库存项目编号后，将描述信息带到描述字段上。
	*@param component
	*@throws Exception 
	*2008-1-27上午11:02:15
	*/
	/* (non-Javadoc)
	 * @see combiz.system.FieldAdapter#validate(com.inbasis.zk.ui.Component)
	 */
	public void validate(Component component) 
	throws Exception 
	{
		//得到主窗体类
		Wpmaterial wpmaterial = (Wpmaterial)this.mainObject;
		String itemnum = (String) this.getValueByColname("itemnum");
		
		
		//通过控件的value值来查询结果
		if (itemnum != null) {
		List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '" + wpmaterial.getWarehouse() +"'");
		
		//如果结果集的大小>=1
		if(Itmlist.size()>0){
			Inventory inv = (Inventory)Itmlist.get(0);
			//将结果的一个属性值绑定给主窗体类的一个属性
			this.setValueByColname("orderunit", inv.getOrderunit());
			this.setValueByColname("unitcost", inv.getLastcost());
			this.setValueByColname("vendor", inv.getVendor());
			this.setValueByColname("manufactureer", inv.getManufacturer());
		}
		else
		{
				List Itemlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
				if (Itemlist.size()<0) {
					Messagebox.show("您选择的物料不存在物资编码，请联系管理员核实！");
					return;
				}
				//Item item = (Item) Itemlist.get(0);
				this.setValueByColname("vendor", null);
				this.setValueByColname("manufactureer", null);
			}
		}
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}


	@Override
	public String getListWhere(Component arg0) {
		String whr = null;
		String whereStr = null;
		//从编辑窗口获取对应的LIST窗口
		Wpmaterial wp = (Wpmaterial) this.getMainObject();
		
		String itemnum = (String) this.getValueByColname("itemnum");
		List invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' ");
		if(invstocklist.size()>0)
		{
			for(int i=0;i<invstocklist.size();i++)
			{
				Invstock invstock = (Invstock) invstocklist.get(i);
				String warehouse = invstock.getWarehouse();
				if(whr == null)
				{
					whr = "'"+warehouse+"'";
				}
				else
				{
					whr = "'"+warehouse+"',"+whr;
				}
			}
			whereStr = "warehouse in(" + whr +") ";
		}
		else
		{
			whereStr="1=2";
		}


		return whereStr;
	}


	
	

}
