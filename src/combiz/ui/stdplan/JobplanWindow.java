package combiz.ui.stdplan;
 
import java.util.Date;

import combiz.business.stdplan.JobplanSrv;
import combiz.domain.stdplan.Jobplan;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class JobplanWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public JobplanWindow()
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
		Jobplan newobj = new Jobplan();
		newobj.setStatus("启用");
		newobj.setStatusdate(new Date());
		newobj.setJpduration(0d);
		mainObject = newobj;
		return true;
	}
}
