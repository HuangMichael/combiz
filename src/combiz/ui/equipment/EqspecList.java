package combiz.ui.equipment;

import combiz.business.equipment.EqspecSrv;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;
import java.util.Date;

public class EqspecList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EqspecList()
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
//		EquipmentWindow parentWnd = (EquipmentWindow)ownerWnd;
//		Equipment parent = (Equipment)parentWnd.getMainObject();
		Equipment parent = (Equipment) this.getOwnerWnd().getMainObject();
		
		Eqspec newobj = new Eqspec();
		newobj.setEqnum(parent.getEqnum());
		newobj.setClassid(parent.getClassid());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setIsmustbe("��");

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
