package combiz.ui.equipment;

import combiz.business.equipment.EqspecSrv;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;
import java.util.Date;

public class EqspecList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EqspecList()
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
//		EquipmentWindow parentWnd = (EquipmentWindow)ownerWnd;
//		Equipment parent = (Equipment)parentWnd.getMainObject();
		Equipment parent = (Equipment) this.getOwnerWnd().getMainObject();
		
		Eqspec newobj = new Eqspec();
		newobj.setEqnum(parent.getEqnum());
		newobj.setClassid(parent.getClassid());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setIsmustbe("否");

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
