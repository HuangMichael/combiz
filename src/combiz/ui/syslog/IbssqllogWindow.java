package combiz.ui.syslog;
 
import combiz.domain.ibs.Ibssqllog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class IbssqllogWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IbssqllogWindow()
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
		Ibssqllog newobj = new Ibssqllog();
		mainObject = newobj;
		return true;
	}

}
