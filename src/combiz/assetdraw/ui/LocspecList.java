package combiz.assetdraw.ui;

import combiz.domain.location.Locations;
import combiz.domain.location.Locspec;
import combiz.system.assetdraw.ListDataWindow;
import combiz.system.ui.RecWindow;

import java.util.Date;


public class LocspecList extends ListDataWindow
{
	
	/**
	 * 添加记录
	 * @throws Exception
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		Locations parent = (Locations)this.getOwnerWnd().getMainObject();
		Locspec locspec = new Locspec();
		locspec.setLocation(parent.getLocation());
		locspec.setChangeby("ADMIN");
		locspec.setChangedate(new Date());
		locspec.setIsmustbe("否");
		
		this.setMainObject(locspec);
		return true;
	}
}
