package combiz.ui.corp;
 
import combiz.business.corp.LaborgroupSrv;
import combiz.domain.corp.Laborgroup;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class LaborgroupWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public LaborgroupWindow()
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
		Laborgroup newobj = new Laborgroup();

		mainObject = newobj;
		return true;
	}
}
