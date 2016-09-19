package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;


public class ChildeqList extends ListWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ChildeqList()
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
		EquipmentWindow parentWnd = (EquipmentWindow)ownerWnd;
		Equipment parent = (Equipment) parentWnd.getMainObject();
		
		Equipment newobj = new Equipment();
		newobj.setParent(parent.getEqnum());

		this.mainObject = newobj;
		return true;
	}
	
	
}
