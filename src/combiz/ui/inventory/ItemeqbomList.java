package combiz.ui.inventory;

import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemeqbom;
import combiz.system.ui.ListWindow;

public class ItemeqbomList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���� ���ڣ�2009-2-24
	 */
	public ItemeqbomList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���� ���ڣ�2009-2-24
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Item parent = (Item)this.getOwnerWnd().getMainObject();
		
		Itemeqbom newobj = new Itemeqbom();
		newobj.setParent(parent.getItemnum());
		
		this.mainObject = newobj;
		return true;
	}
}
