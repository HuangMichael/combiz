package combiz.assetdraw.ui;

import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.system.assetdraw.ListDataWindow;

import java.util.Date;


public class EqspecList extends ListDataWindow
{
	
	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		Equipment parent = (Equipment)this.getOwnerWnd().getMainObject();
		Eqspec eqspec = new Eqspec();
		eqspec.setEqnum(parent.getEqnum());
		eqspec.setChangeby("ADMIN");
		eqspec.setChangedate(new Date());
		eqspec.setIsmustbe("��");
		
		this.setMainObject(eqspec);
		return true;
	}
}
