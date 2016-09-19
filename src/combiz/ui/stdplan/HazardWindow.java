package combiz.ui.stdplan;
 
import combiz.business.stdplan.HazardSrv;
import combiz.domain.stdplan.Hazard;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class HazardWindow extends MainWindow
implements InfWindow
{
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public HazardWindow()
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
		Hazard newobj = new Hazard();
		newobj.setHazmat("否");
		newobj.setHazprec("否");
		newobj.setHaztagout("否");

		mainObject = newobj;
		return true;
	}

}
