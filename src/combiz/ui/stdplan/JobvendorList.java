package combiz.ui.stdplan;

import combiz.domain.stdplan.Jobplan;
import combiz.domain.stdplan.Jobvendor;
import combiz.system.ui.ListWindow;

public class JobvendorList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public JobvendorList()
	{
		super();
	}
			

	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		JobplanWindow parentWnd = (JobplanWindow)ownerWnd;
		Jobplan parent = (Jobplan) parentWnd.getMainObject();
		
		Jobvendor newobj = new Jobvendor();
		newobj.setJpnum(parent.getJpnum());
		//����������½���ʼ������

		this.mainObject = newobj;
		return true;
	}	


}
