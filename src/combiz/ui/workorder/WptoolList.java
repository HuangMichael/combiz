package combiz.ui.workorder;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wptool;
import combiz.system.ui.ListWindow;

public class WptoolList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WptoolList()
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
		
		Workorder parent = (Workorder)this.getOwnerWnd().getMainObject();
		
		Wptool newobj = new Wptool();
		newobj.setWonum(parent.getWonum());

		this.mainObject = newobj;
		return true;
	}
	
}
