package combiz.ui.assetscard;

import java.util.List;

import combiz.business.assetscard.AllocationSrv;
import combiz.business.inventory.RejectSrv;
import combiz.business.po.PoSrv;
import combiz.domain.assetscard.Allocation;
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
public class Equipline extends CommonListWindow {


	public Equipline() {
		super();
	}
	public void insertline() throws Exception
	{
		Allocation allocation = (Allocation) this.getOwnerWnd().getMainObject();
		List invstocklist = this.getSelectObjects();
		String s = allocation.getAllocationnum();
		if(invstocklist.size()>0)
		{
			((AllocationSrv) this.getOwnerWnd().getMainSrv()).insertline(invstocklist,allocation);
			
		}
		RecWindow mainwnd = this.getOwnerWnd();
		mainwnd.refreshChildData();
		
		
		
	}


}
