package combiz.ui.workorder;

import java.util.Date;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wptask;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

public class WptaskList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WptaskList()
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
		//WorkorderWindow parentWnd = (WorkorderWindow)ownerWnd;
		RecWindow  window = this.ownerWnd;
		
//		FcWorkorderWindow parentWnd = (FcWorkorderWindow) ownerWnd;
//		if(window.equals(FcWorkorderWindow))
//		{
//			FcWorkorderWindow parentWnd = (FcWorkorderWindow) ownerWnd;
//		}
		Workorder parent = (Workorder) window.getMainObject();
		
		Wptask newobj = new Wptask();
		newobj.setWonum(parent.getWonum());
		newobj.setStatusdate(new Date());
		newobj.setStatus("流程未启动");
		

		this.mainObject = newobj;
		return true;
	}
}
