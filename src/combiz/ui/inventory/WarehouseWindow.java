package combiz.ui.inventory;
 
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Warehouse;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class WarehouseWindow extends MainWindow
implements InfWindow
{	

	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WarehouseWindow()
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
		Warehouse newobj = new Warehouse();
		
		mainObject = newobj;
		return true;
	}
	


}
