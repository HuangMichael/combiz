package combiz.ui.inventory;
 
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Warehouse;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class WarehouseWindow extends MainWindow
implements InfWindow
{	

	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WarehouseWindow()
	{
		super();
	}
	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Warehouse newobj = new Warehouse();
		
		mainObject = newobj;
		return true;
	}
	


}
