package combiz.ui.workorder;


import java.util.List;

import com.inbasis.zul.Messagebox;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Item;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonListWindow;
import combiz.system.util.Util;


/**
 * 功能：在发票行里复制采购单的时候，将满足条件的采购单行罗列出来。
 *@author 李阳
 *2008-1-24下午12:58:21
 */
public class Woinvreserveline extends CommonListWindow {


	public Woinvreserveline() {
		super();
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：点确定后触发该事件。
	 * 日期：Nov 5, 2008 11:08:18 AM
	 *
	 */
	public void geninvuse() throws Exception 
	{
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		WorkorderSrv workorderSrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
		List retList = this.getSelectObjects();
		if (retList.size() >0)
		{
			for(int i=0;i<retList.size();i++)
			{
				Invreserve invreserve = (Invreserve) retList.get(i);
				String itemnum = invreserve.getItemnum();
				if(Util.isNotNull(itemnum))
				{
					Item item = (Item) this.getMainSrv().getBaseDao().findUniqueBy(Item.class, "itemnum", itemnum);
					if(item != null)
					{
						if(item.getRotating().equals("是"))
						{
							throw new Exception("编号为'"+itemnum+"'是周转件管理，请在发放预留里进行选择!");
						}
					}
				}
					
			}
			
			workorderSrv.geninvuse(retList, wo);
			this.refreshData();
			this.getOwnerWnd().refreshData();
			Messagebox.show("数据成功保存，确认发放数量无误后校验完成发放.如果不是全部发放，请在发放行里修改发放数量!");
			
		}
		else
		{
			throw new Exception("请选择一条记录！");
		}
	}
	
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		String wonum = wo.getWonum();
		List InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+ wonum +"'");
		if(InvrlineList.size() > 0)
		{
		for(int i=0;i<InvrlineList.size();i++)
		{
			Invreserve invreserve = (Invreserve) InvrlineList.get(i);
			Double inuqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "'");
			Double waitqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='待确认'");
			if(inuqty == null)
				inuqty = 0d;
			if (waitqty == null) {
				waitqty = 0D;
			}
			if(invreserve.getReservedqty()- waitqty > 0)
			{
				if(idstr==null)
					idstr = invreserve.getId() + "";
				else
					idstr = invreserve.getId() + "," + idstr;
			}
		}
	
		if(idstr!=null)
			idstr = "id in(" + idstr + ")";
		else
			idstr = "1=2";
		this.setQueryString(idstr);
		this.refreshData();
		}
		else
			throw new Exception("没有预留的库存项目");
	
		
	}
	
	
	


}
