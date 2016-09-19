package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Invreserve;

import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class InvreserveWindow extends MainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InvreserveWindow()
	{
		super();
	}

	
	/**
	 * 添加记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Invreserve newobj = new Invreserve();
		//请在下面添加对象的初始化值
		
		mainObject = newobj;
		return true;
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：清除选中记录的预留信息
	 * 日期：2:12:17 PM  Apr 9, 2009 
	 *
	 */
	public void clearinvre()
	throws Exception
	{
		List invreslist = this.getSelectObjects();
		this.mainSrv.getBaseDao().deleteBatch(invreslist);
		this.clear();
	}
	
}
