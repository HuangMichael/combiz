package combiz.ui.po;
 
import combiz.domain.po.Po;
import combiz.domain.po.Orderunit;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class OrderunitWindow extends MainWindow
implements InfWindow
{

	public OrderunitWindow()
	{
		super();
	}
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：高群凯 日期：2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Orderunit newobj = new Orderunit();
		mainObject = newobj;
		return true;
	}
}
