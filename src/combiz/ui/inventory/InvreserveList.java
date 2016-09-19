package combiz.ui.inventory;

import java.util.Date;

import combiz.business.inventory.InvusetransSrv;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Inventory;
import combiz.system.ui.ListWindow;

public class InvreserveList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvreserveList()
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
		//InventoryWindow parentWnd = (InventoryWindow)ownerWnd;
		Inventory parent = (Inventory)this.getOwnerWnd().getMainObject();
		
		Invreserve newobj = new Invreserve();
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setReqby(this.getLaborInfo().getLabornum());
		newobj.setReqdate(new Date());
		//newobj.setCorpnum(parent.getCorpnum());
		//newobj.setSitenum(parent.getSitenum());
		this.mainObject = newobj;
		return true;
	}
}
