package combiz.ui.stdplan;
 
import java.util.Date;

import combiz.business.stdplan.JobplanSrv;
import combiz.domain.stdplan.Jobplan;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class JobplanWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public JobplanWindow()
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
		Jobplan newobj = new Jobplan();
		newobj.setStatus("����");
		newobj.setStatusdate(new Date());
		newobj.setJpduration(0d);
		mainObject = newobj;
		return true;
	}
}
