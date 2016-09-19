package combiz.ui.location;

import combiz.domain.equipment.Equipment;
import combiz.domain.location.Locations;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class EquipmentList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EquipmentList()
	{
		super();
	}
	///////////////////////////////////////////////////////////////////
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		//Locations2Window parentWnd = (Locations2Window)ownerWnd;
		Locations parent = (Locations)this.getOwnerWnd().getMainObject();
		
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		newobj.setInvcost(0.0D);
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		newobj.setLocation(parent.getLocation());
		newobj.setLargetype("����豸");
		newobj.setIsrunning("��");
		newobj.setChangeby(this.getUserInfo().getLabornum());
		//newobj.setSitenum(this.getLaborInfo().getSitenum());
		newobj.setChangedate(new Date());
		mainObject = newobj;
		return true;
	}


}
