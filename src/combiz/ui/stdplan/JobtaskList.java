package combiz.ui.stdplan;

import combiz.domain.stdplan.Jobplan;
import combiz.domain.stdplan.Jobtask;
import combiz.system.ui.ListWindow;

public class JobtaskList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public JobtaskList()
	{
		super();
	}
			
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		JobplanWindow parentWnd = (JobplanWindow)ownerWnd;
		Jobplan parent = (Jobplan) parentWnd.getMainObject();
		
		Jobtask newobj = new Jobtask();
		newobj.setJpnum(parent.getJpnum());
		newobj.setTaskduration(0.0D);
		this.mainObject = newobj;
		return true;
	}

}
