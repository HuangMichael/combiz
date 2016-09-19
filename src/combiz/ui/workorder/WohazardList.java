package combiz.ui.workorder;

import combiz.domain.workorder.Wohazard;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.ListWindow;

public class WohazardList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WohazardList()
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
		Workorder parent = (Workorder) ownerWnd.getMainObject();
		
		Wohazard newobj = new Wohazard();
		newobj.setWonum(parent.getWonum());

		this.mainObject = newobj;
		return true;
	}
	
}
