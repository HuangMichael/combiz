package combiz.ui.location;
 

import combiz.domain.location.Locations;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;

public class Locations2Window extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public Locations2Window()
	{
		super();
	}


	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Locations newobj = new Locations();
		newobj.setType("����λ��");
		newobj.setStatus("������");
		newobj.setStatusdate(new Date());
		newobj.setChangeby("�޸���");
		newobj.setChangedate(new Date());
		mainObject = newobj;
		return true;
	}

}
