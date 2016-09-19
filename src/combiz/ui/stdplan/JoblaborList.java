package combiz.ui.stdplan;

import combiz.domain.stdplan.Joblabor;
import combiz.domain.stdplan.Jobplan;
import combiz.system.ui.ListWindow;

public class JoblaborList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public JoblaborList()
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
		
		Joblabor newobj = new Joblabor();
		newobj.setJpnum(parent.getJpnum());

		this.mainObject = newobj;
		return true;
	}

}
