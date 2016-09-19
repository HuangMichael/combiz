package combiz.ui.location;

import combiz.domain.location.Locations;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;

public class PremaintList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ�ljh ���ڣ�2008-03-10
	 */
	public PremaintList()
	{
		super();
	}

	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Locations parent = (Locations)this.ownerWnd.getMainObject();
		
		Premaint newobj = new Premaint();
		newobj.setPmcounter(0L);
		newobj.setHaschild("��");
		newobj.setAdjnextdue("��");
		newobj.setAutowf("��");
		newobj.setFrequency(0L);
		newobj.setFrequnit("��");
		newobj.setUsefrequency("��");
		newobj.setEqdown("��");
		newobj.setUsejpseq("��");
		
		newobj.setLocation(parent.getLocation());
		

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
