package combiz.ui.inventory;

import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Item;
import combiz.system.ui.ListWindow;


public class ItemTreeList extends ListWindow 
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ItemTreeList()
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
		//ItemClassWindow parentWnd = (ItemClassWindow)ownerWnd;
		Classification parent = (Classification)this.getOwnerWnd().getMainObject();
		
		Item newobj = new Item();
		//���ʱ����Զ�����
		//newobj.setItemnum(parent.getClassid()+"-");
		String getkey = this.genAutokey("itemnum");
		int a = getkey.length();
		if (a<4) {
			for(int i = 0;i<(4-a);i++) {
				getkey = "0"+getkey;
			}
		}
		newobj.setItemnum(parent.getClassid()+getkey);
		newobj.setClassid(parent.getClassid());
		if(parent.getClasstype().equals("�ʲ�"))
		{
			newobj.setRotating("��");
			newobj.setLottype("����");
		}
		else
		{
			newobj.setRotating("��");
			newobj.setLottype("������");
		}
		
		newobj.setOutside("��");
		newobj.setSpareautoadd("��");
		newobj.setInspectreq("��");

		this.mainObject = newobj;
		return true;
	}
}
