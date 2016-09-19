package combiz.ui.stdplan;

import combiz.domain.stdplan.Isolockout;
import combiz.domain.stdplan.Isotag;
import combiz.system.ui.ListWindow;

public class IsolockoutList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IsolockoutList()
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
		Isotag parent = (Isotag) this.ownerWnd.getMainObject();
		
		Isolockout newobj = new Isolockout();
		newobj.setIsotagid(parent.getIsotagid());
		newobj.setAplyseq(parent.getAplyseq());
		newobj.setState(parent.getState());
		newobj.setLocation(parent.getLocation());
		newobj.setEqnum(parent.getEqnum());

		this.mainObject = newobj;
		return true;
	}
	

}
