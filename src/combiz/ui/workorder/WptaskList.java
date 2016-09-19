package combiz.ui.workorder;

import java.util.Date;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wptask;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

public class WptaskList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WptaskList()
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
		//WorkorderWindow parentWnd = (WorkorderWindow)ownerWnd;
		RecWindow  window = this.ownerWnd;
		
//		FcWorkorderWindow parentWnd = (FcWorkorderWindow) ownerWnd;
//		if(window.equals(FcWorkorderWindow))
//		{
//			FcWorkorderWindow parentWnd = (FcWorkorderWindow) ownerWnd;
//		}
		Workorder parent = (Workorder) window.getMainObject();
		
		Wptask newobj = new Wptask();
		newobj.setWonum(parent.getWonum());
		newobj.setStatusdate(new Date());
		newobj.setStatus("����δ����");
		

		this.mainObject = newobj;
		return true;
	}
}
