package combiz.ui.cal;

import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calworkperiod;
import combiz.system.ui.ListWindow;

public class CalworkperiodList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CalworkperiodList()
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
		CalendarWindow parentWnd = (CalendarWindow)ownerWnd;
		Calendar parent = (Calendar) parentWnd.getMainObject();
		
		Calworkperiod newobj = new Calworkperiod();
		
		this.mainObject = newobj;
		return true;
	}

}
