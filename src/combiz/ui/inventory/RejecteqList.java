package combiz.ui.inventory;

import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.ListWindow;

public class RejecteqList extends ListWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public RejecteqList()
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
//		//获取父级主窗体对象
//		Reject parent = (Reject) this.getOwnerWnd().getMainObject();
//		
//		Rejectitem newobj= new Rejectitem();
//		//newobj.setXXXX(parent.getXXXX()); //必须添加关联字段值
//
//		this.mainObject = newobj;
//		return true;
		throw new Exception("不能添加明细行，请在操作菜单下选择报废明细行进行操作！");
	}
}
