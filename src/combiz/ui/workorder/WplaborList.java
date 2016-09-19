package combiz.ui.workorder;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wplabor;
import combiz.system.ui.ListWindow;

public class WplaborList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WplaborList()
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
		Workorder parent = (Workorder) this.getOwnerWnd().getMainObject();
		
		Wplabor newobj = new Wplabor();
		newobj.setWonum(parent.getWonum());

		this.mainObject = newobj;
		return true;
	}
	
}
