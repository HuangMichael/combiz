package combiz.ui.stdplan;
 
import combiz.domain.stdplan.Isotag;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class IsotagWindow extends MainWindow
implements InfWindow
{
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public IsotagWindow()
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
		Isotag newobj = new Isotag();
		newobj.setAplyseq(0L);
		
		mainObject = newobj;
		return true;
	}

}
