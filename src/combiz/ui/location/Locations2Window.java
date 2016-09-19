package combiz.ui.location;
 

import combiz.domain.location.Locations;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;

public class Locations2Window extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public Locations2Window()
	{
		super();
	}


	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Locations newobj = new Locations();
		newobj.setType("运行位置");
		newobj.setStatus("已启用");
		newobj.setStatusdate(new Date());
		newobj.setChangeby("修改人");
		newobj.setChangedate(new Date());
		mainObject = newobj;
		return true;
	}

}
