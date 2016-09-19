package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
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
		Equipment parent = (Equipment)this.ownerWnd.getMainObject();
		
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
		
		newobj.setEqnum(parent.getEqnum());
		

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
