package combiz.ui.stdplan;
 
import combiz.domain.stdplan.Isotag;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class IsotagWindow extends MainWindow
implements InfWindow
{
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IsotagWindow()
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
		Isotag newobj = new Isotag();
		newobj.setAplyseq(0L);
		
		mainObject = newobj;
		return true;
	}

}
