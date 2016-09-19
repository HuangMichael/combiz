package combiz.ui.corp;

import combiz.domain.corp.Laborgroup;
import combiz.domain.corp.Laborgrps;
import combiz.system.ui.ListWindow;

public class LaborgrpsList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public LaborgrpsList()
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
		LaborgroupWindow parentWnd = (LaborgroupWindow)ownerWnd;
		Laborgroup parent = (Laborgroup)parentWnd.getMainObject();
		
		Laborgrps newobj = new Laborgrps();
		newobj.setGroupname(parent.getGroupname());
		
		this.mainObject = newobj;
		return true;
	}
	
}
