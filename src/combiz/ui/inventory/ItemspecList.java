package combiz.ui.inventory;

import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemspec;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class ItemspecList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ItemspecList()
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
		Item parent = (Item)this.getOwnerWnd().getMainObject();
		
		Itemspec newobj = new Itemspec();
		newobj.setItemnum(parent.getItemnum());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setIsmustbe("��");

		this.mainObject = newobj;
		return true;
	}
}
