package combiz.ui.workorder;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.FieldAdapter;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

public class FldItem extends FieldAdapter {

	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。 如果
	 */
	public void init(Component component) {

	}

	/**
	 * @author 李阳 功能：在工单里填写物料申请，在值列表里选择库存项目编号后，将描述信息带到描述字段上。
	 * @param component
	 * @throws Exception
	 *             2008-1-27上午11:02:15
	 */
	public void validate(Component component) throws Exception {

	}

	/**
	 * @throws throws
	 *             Exception
	 * 
	 */
	public void action(Component component) throws Exception {

		Wpmaterial wpmaterial = (Wpmaterial)this.mainObject;
		String itemnum = wpmaterial.getItemnum();
		if(Util.isNotNull(itemnum))
		{
			String  getapp = this.getRecWnd().getOwnerWnd().getApp();
			int count = 0;
			if (getapp.equals("EQREQ") || getapp.equals("MATREQ")||getapp.equals("BORROWEQU")) {
				Matreq matreq = (Matreq) this.getRecWnd().getOwnerWnd().getMainObject();
				String matreqnum = matreq.getMatreqnum();
				
				if(wpmaterial.getId()==null)
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Wpmaterial w where w.itemnum = '"+itemnum+"' and w.matreqnum ='"+matreqnum+"' and id is not null");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Wpmaterial w where w.itemnum = '"+itemnum+"' and w.matreqnum ='"+matreqnum+"' and id<>"+wpmaterial.getId());
				}
			} else {
				Workorder workorder = (Workorder) this.getRecWnd().getOwnerWnd().getMainObject();
				String wonum = workorder.getWonum();
				
				if(workorder.getId()==null)
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Wpmaterial w where w.itemnum = '"+itemnum+"' and w.wonum ='"+wonum+"' and id is not null");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Wpmaterial w where w.itemnum = '"+itemnum+"' and w.wonum ='"+wonum+"' and id<>"+wpmaterial.getId());
				}
			}
			
			
			if(count>0)
			{
				throw new Exception("编号为:'"+itemnum+"'的采购项目已经在申请行中存在，请确认！");
			}
			ListWindow listwnd = null;
			if (getapp.equals("EQREQ") || getapp.equals("MATREQ")||getapp.equals("BORROWEQU")) {
				listwnd = (ListWindow) this.getRecWnd().getOwnerWnd().getFellow("wpmaterial");
			}else {
				listwnd = (ListWindow) this.getRecWnd().getOwnerWnd().getFellow("wpmaterialTable");
			}
			List addnewlist = listwnd.getAddedObjectList();
			int a =0;
			for(int i=0;i<addnewlist.size();i++)
			{
				Wpmaterial addwpmaterial = (Wpmaterial) addnewlist.get(i);
				if(itemnum.equals(addwpmaterial.getItemnum()))
				{
					a = a + 1;
				}
				if(a>1)
				{
					throw new Exception("编号为:'"+itemnum+"'的采购项目已经在申请行中存在，请确认！");
				}
			}
			
			List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
			if(Itmlist.size()>0)
			{
				Item it = (Item)Itmlist.get(0);
				this.setValueByColname("description", it.getDescription());
				this.setValueByColname("orderunit", it.getOrderunit());
				this.setReadonlyByColname("description");
				String app = this.getEditWnd(component).getOwnerWnd().getApp();
				if(app.equals("EQREQ")||app.equals("MATREQ")||getapp.equals("BORROWEQU"))
				{
					this.setValueByColname("modelnum", it.getModelnum());
					this.setValueByColname("classid", it.getClassid());
					this.setReadonlyByColname("modelnum");
					this.setReadonlyByColname("classid");
				}
			}else
			{
				Messagebox.show("该库存项目不存在，请核实！");
				this.setValueByColname("itemnum", "");
			}
			/******判断该库存项目是否在仓库中存在，如果存在的话，自动赋值仓库****/
			List invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"'");
			if(invstocklist.size()>=1)
			{
				Invstock invstock = (Invstock) invstocklist.get(0);
				this.setValueByColname("warehouse", invstock.getWarehouse());
			}
		}
		else
		{
			this.setNoReadonlyByColname("description");
			this.setNoReadonlyByColname("modelnum");
			this.setNoReadonlyByColname("classid");
		}
		
		
	
		
	}

	/*
	 * 功能：在资产的领用程序里，选择物资的时候，只能选择周转件 作者：李阳 日期：Nov 3, 2008 11:10:39 AM
	 */
	@Override
	public String getListWhere(Component arg0) {
		// TODO Auto-generated method stub
		String itemnum = (String) this.getValueByColname("itemnum");
		String  app = this.getRecWnd().getOwnerWnd().getApp();
		if(app.equals("EQREQ") || app.equals("BORROWEQU"))
		{
			return "itemnum in (select t.itemnum from Item t where t.rotating='是')";
		}
		else
		{
			return "itemnum in (select t.itemnum from Item t)";
		}
/*		String app = this.getRecWnd().getOwnerWnd().getApp();
			if (app!=null && !app.equals("") && app.equals("EQREQ")) {
				return "itemnum in (select t.itemnum from Item t where t.rotating='是')";
			} else if (app!=null && !app.equals("") && app.equals("MATREQ")){
				return "itemnum in (select t.itemnum from Item t where t.rotating='否')";
			}else {
				return "";
			}*/
	}

}
