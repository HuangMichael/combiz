package combiz.ui.syslog;
 
import combiz.domain.ibs.Ibssqllog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class IbssqllogWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public IbssqllogWindow()
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
		Ibssqllog newobj = new Ibssqllog();
		mainObject = newobj;
		return true;
	}

}
