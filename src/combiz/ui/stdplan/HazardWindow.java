package combiz.ui.stdplan;
 
import combiz.business.stdplan.HazardSrv;
import combiz.domain.stdplan.Hazard;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class HazardWindow extends MainWindow
implements InfWindow
{
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public HazardWindow()
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
		Hazard newobj = new Hazard();
		newobj.setHazmat("��");
		newobj.setHazprec("��");
		newobj.setHaztagout("��");

		mainObject = newobj;
		return true;
	}

}
