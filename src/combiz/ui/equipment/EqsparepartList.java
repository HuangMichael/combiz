package combiz.ui.equipment;

import java.util.Date;

import combiz.business.equipment.EquipmentSrv;
import combiz.domain.equipment.Equipment;
import combiz.domain.equipment.Eqsparepart;
import combiz.system.ui.ListWindow;

public class EqsparepartList extends ListWindow  //��������
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EqsparepartList()
	{
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���Ⱥ�� ���ڣ�2007-8-8
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
//		EquipmentWindow parentWnd = (EquipmentWindow)ownerWnd;
//		Equipment parent = (Equipment)parentWnd.getMainObject();
		Equipment parent = (Equipment) this.getOwnerWnd().getMainObject();
		
		Eqsparepart newobj = new Eqsparepart();
		newobj.setEqnum(parent.getEqnum());
		newobj.setQuantity(1D);
		
		this.mainObject = newobj;
		return true;
	}
}
