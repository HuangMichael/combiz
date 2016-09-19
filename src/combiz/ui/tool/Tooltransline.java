package combiz.ui.tool;

import java.util.List;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolreserve;
import combiz.system.ui.CommonListWindow;


/**
 * 功能：在工具里复制工具预留的时候，将满足条件的采购单行罗列出来。
 *@author ljh
 *2008-03-14 上午  11:00:21
 */
public class Tooltransline extends CommonListWindow {


	public Tooltransline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		//String idstr = null;
		Tool tool = (Tool) this.getOwnerWnd().getMainObject();
		String toolnum = tool.getToolnum();
/*		List toolreserveList = this.getMainSrv().getBaseDao().findWithQuery(Toolreserve.class, "toolnum = '"+ toolnum +"'");
		for(int i=0;i<toolreserveList.size();i++)
		{
			Toolreserve toolreserve = (Toolreserve) toolreserveList.get(i);
			Double recQty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.qty) from tool t  where t.toolnum='"+toolreserve.getToolnum()+"'");
			if(recQty == null)
				recQty = 0d;
			if(recQty - toolreserve.getReservedqty() > 0)
			{
				if(idstr==null)
					idstr = toolreserve.getId() + "";
				else
					idstr = toolreserve.getId() + "," + idstr;
			}
		}
		if(idstr!=null)
			idstr = "id in(" + idstr + ")";
		else
			idstr = "1=2";*/
		this.setQueryString("toolnum = '"+toolnum+"' and state = '已申请'");
		this.refreshData();
	}


}
