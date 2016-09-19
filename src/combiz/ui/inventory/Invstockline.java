package combiz.ui.inventory;

import java.util.List;

import combiz.business.inventory.RejectSrv;
import combiz.business.po.PoSrv;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.RecWindow;


/**
 * @author ÀîÑô E-mail:superyang4208731@yahoo.com.cn
 * 10:09:31 AM  Oct 21, 2008 
 * ¹¦ÄÜ£º
 */
public class Invstockline extends CommonListWindow {


	public Invstockline() {
		super();
	}
	public void createdel() throws Exception
	{
		Reject reject = (Reject) this.getOwnerWnd().getMainObject();
		List invstocklist = this.getSelectObjects();
		String s = reject.getRejectnum();
		if(invstocklist.size()>0)
		{
			((RejectSrv) this.getOwnerWnd().getMainSrv()).createdel(invstocklist, s);
			
		}
		RecWindow mainwnd = this.getOwnerWnd();
		mainwnd.refreshChildData();
		
		
		
	}


}
