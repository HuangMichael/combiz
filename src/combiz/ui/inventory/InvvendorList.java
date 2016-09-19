package combiz.ui.inventory;

import java.util.Date;

import combiz.business.po.InvvendorSrv;
import combiz.domain.po.Invvendor;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvvendorList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvvendorList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		InventoryWindow parentWnd = (InventoryWindow)ownerWnd;
		Inventory parent = (Inventory)parentWnd.getMainObject();
		
		Invvendor newobj = new Invvendor();
		newobj.setItemnum(parent.getItemnum());
		this.mainObject = newobj;
		return true;
	}
}
