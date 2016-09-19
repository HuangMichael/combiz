package combiz.assetdraw.ui;

import combiz.domain.location.Locations;
import combiz.domain.location.Locspec;
import combiz.system.assetdraw.ListDataWindow;
import combiz.system.ui.RecWindow;

import java.util.Date;


public class LocspecList extends ListDataWindow
{
	
	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		Locations parent = (Locations)this.getOwnerWnd().getMainObject();
		Locspec locspec = new Locspec();
		locspec.setLocation(parent.getLocation());
		locspec.setChangeby("ADMIN");
		locspec.setChangedate(new Date());
		locspec.setIsmustbe("��");
		
		this.setMainObject(locspec);
		return true;
	}
}
