package combiz.ui.inventory;

import java.util.List;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.ui.CommonListWindow;


/**
 * 功能：在发票行里复制采购单的时候，将满足条件的采购单行罗列出来。
 *@author 李阳
 *2008-1-24下午12:58:21
 */
public class Invrectransline extends CommonListWindow {


	public Invrectransline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		Po po = (Po) this.getOwnerWnd().getMainObject();
		String ponum = po.getPonum();
		List polineList = this.getMainSrv().getBaseDao().findWithQuery(Poline.class, "ponum = '"+ ponum +"'");
		for(int i=0;i<polineList.size();i++)
		{
			Poline poline = (Poline) polineList.get(i);
			Double recQty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(i.quantity) from Invrectrans i  where i.ponum='"+poline.getPonum()+"' and i.polinenum="+poline.getPolinenum()+"'");
			if(recQty == null)
				recQty = 0d;
			if(poline.getOrderqty() -  recQty > 0)
			{
				if(idstr==null)
					idstr = poline.getId() + "";
				else
					idstr = poline.getId() + "," + idstr;
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
