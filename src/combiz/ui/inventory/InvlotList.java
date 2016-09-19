package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.system.ui.ListWindow;

public class InvlotList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvlotList()
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
		Inventory parent = (Inventory)ownerWnd.getMainObject();
		
		Invlot newobj = new Invlot();
		//newobj.setCorpnum(parent.getCorpnum());
		//newobj.setSitenum(parent.getSitenum());
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setLotnum(this.genAutokey("lotnum"));
		this.mainObject = newobj;
		return true;
	}
}
