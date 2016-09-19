package combiz.ui.equipment;

import java.util.Date;

import combiz.domain.equipment.Equipment;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainTreeListWindow;


public class EquipmentsplitWindow extends MainTreeListWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EquipmentsplitWindow() {
		super();
	}


	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		Equipment parentEq = (Equipment) this.getSelectObject();
		if(parentEq!=null)
		{
			newobj.setParent(parentEq.getEqnum());
			newobj.setLocation(parentEq.getLocation());
		}
		newobj.setInvcost(0.0D);
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		newobj.setIsrunning("��");

		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setChangedate(new Date());

		mainObject = newobj;
		return true;
	}

	
}
