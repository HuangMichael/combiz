package combiz.ui.location;

import combiz.domain.location.Locations;
import combiz.domain.location.Locspec;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class LocspecList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public LocspecList()
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
		LocationsWindow parentWnd = (LocationsWindow)ownerWnd;
		Locations parent = (Locations)parentWnd.getMainObject();
		
		Locspec newobj = new Locspec();
		newobj.setLocation(parent.getLocation());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setChangedate(new Date());
		newobj.setIsmustbe("��");

		this.mainObject = newobj;
		return true;
	}
	

}
