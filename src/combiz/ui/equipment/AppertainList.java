package combiz.ui.equipment;


import combiz.business.equipment.EquipmentSrv;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;
import java.util.Date;

public class AppertainList extends ListWindow  //附属设备窗口
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public AppertainList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	*/ 
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Equipment parent = (Equipment) this.getOwnerWnd().getMainObject();
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		newobj.setLocation(parent.getLocation());
		newobj.setParent(parent.getEqnum());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setInvcost(0.0D);
		newobj.setIsrunning("是");
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		this.mainObject = newobj;
		return true;
	}
	
}
