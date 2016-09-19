package combiz.ui.location;

import combiz.domain.location.Locations;
import combiz.domain.location.Locspec;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class LocspecList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public LocspecList()
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
		LocationsWindow parentWnd = (LocationsWindow)ownerWnd;
		Locations parent = (Locations)parentWnd.getMainObject();
		
		Locspec newobj = new Locspec();
		newobj.setLocation(parent.getLocation());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setChangedate(new Date());
		newobj.setIsmustbe("否");

		this.mainObject = newobj;
		return true;
	}
	

}
