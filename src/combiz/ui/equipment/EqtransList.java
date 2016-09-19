package combiz.ui.equipment;

import java.util.Date;

import combiz.domain.equipment.Equipment;
import combiz.domain.equipment.Eqtrans;
import combiz.system.ui.ListWindow;

public class EqtransList extends ListWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EqtransList()
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
		
		Eqtrans newobj= new Eqtrans();
		//newobj.setXXXX(parent.getXXXX()); //必须添加关联字段值
		newobj.setEqnum(parent.getEqnum());
		newobj.setTransdate(new Date());
		

		this.mainObject = newobj;
		return true;
	}
}
