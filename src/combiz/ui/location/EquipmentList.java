package combiz.ui.location;

import combiz.domain.equipment.Equipment;
import combiz.domain.location.Locations;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class EquipmentList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EquipmentList()
	{
		super();
	}
	///////////////////////////////////////////////////////////////////
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
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
		newobj.setLargetype("配电设备");
		newobj.setIsrunning("是");
		newobj.setChangeby(this.getUserInfo().getLabornum());
		//newobj.setSitenum(this.getLaborInfo().getSitenum());
		newobj.setChangedate(new Date());
		mainObject = newobj;
		return true;
	}


}
