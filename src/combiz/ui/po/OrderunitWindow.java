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
	 * ������¼
	 * @throws Exception
	 * ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Orderunit newobj = new Orderunit();
		mainObject = newobj;
		return true;
	}
}
