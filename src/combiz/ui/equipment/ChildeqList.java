package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;


public class ChildeqList extends ListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ChildeqList()
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
		EquipmentWindow parentWnd = (EquipmentWindow)ownerWnd;
		Equipment parent = (Equipment) parentWnd.getMainObject();
		
		Equipment newobj = new Equipment();
		newobj.setParent(parent.getEqnum());

		this.mainObject = newobj;
		return true;
	}
	
	
}
