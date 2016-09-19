package combiz.ui.corp;
 
import combiz.business.corp.LaborgroupSrv;
import combiz.domain.corp.Laborgroup;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class LaborgroupWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public LaborgroupWindow()
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
		Laborgroup newobj = new Laborgroup();

		mainObject = newobj;
		return true;
	}
}
