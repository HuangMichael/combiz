package combiz.ui.inventory;


import java.util.List;

import combiz.domain.inventory.Invreserve;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.CommonListWindow;


/**
 * 功能：在发票行里复制采购单的时候，将满足条件的采购单行罗列出来。
 *@author 李阳
 *2008-1-24下午12:58:21
 */
public class Invreserveline extends CommonListWindow {


	public Invreserveline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		String wonum = wo.getWonum();
		List InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+ wonum +"'");
		for(int i=0;i<InvrlineList.size();i++)
		{
			Invreserve invreserve = (Invreserve) InvrlineList.get(i);
			Double inuqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "'");
			if(inuqty == null)
				inuqty = 0d;
			if(invreserve.getReservedqty() -  inuqty > 0)
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


}
