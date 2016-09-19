package combiz.ui.equipment;

import java.util.Date;

import combiz.business.equipment.EquipmentSrv;
import combiz.domain.equipment.Equipment;
import combiz.domain.equipment.Eqsparepart;
import combiz.system.ui.ListWindow;

public class EqsparepartList extends ListWindow  //备件窗口
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EqsparepartList()
	{
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：高群凯 日期：2007-8-8
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
//		EquipmentWindow parentWnd = (EquipmentWindow)ownerWnd;
//		Equipment parent = (Equipment)parentWnd.getMainObject();
		Equipment parent = (Equipment) this.getOwnerWnd().getMainObject();
		
		Eqsparepart newobj = new Eqsparepart();
		newobj.setEqnum(parent.getEqnum());
		newobj.setQuantity(1D);
		
		this.mainObject = newobj;
		return true;
	}
}
