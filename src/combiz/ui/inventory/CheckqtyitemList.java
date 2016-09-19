package combiz.ui.inventory;

import combiz.domain.inventory.Checkqty;
import combiz.domain.inventory.Checkqtyitem;
import combiz.system.ui.ListWindow;

public class CheckqtyitemList extends ListWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CheckqtyitemList()
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
//		Checkqty parent = (Checkqty) this.getOwnerWnd().getMainObject();
//		
//		Checkqtyitem newobj= new Checkqtyitem();
//		//newobj.setXXXX(parent.getXXXX()); //必须添加关联字段值
//
//		this.mainObject = newobj;
//		return true;
		throw new Exception("不能添加明细行，请在操作菜单下选择生成盘点明细进行操作！");
	}
}
