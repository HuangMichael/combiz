package combiz.ui.stdplan;

import combiz.domain.stdplan.Hazard;
import combiz.domain.stdplan.Hazardprec;
import combiz.system.ui.ListWindow;

public class HazprecList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public HazprecList()
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
		HazardWindow parentWnd = (HazardWindow)ownerWnd;
		Hazard parent = (Hazard) parentWnd.getMainObject();
		
		Hazardprec newobj = new Hazardprec();
		newobj.setHazardid(parent.getHazardid());

		this.mainObject = newobj;
		return true;
	}
	
	
}
