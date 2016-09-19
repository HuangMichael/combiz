package combiz.ui.po;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import java.util.List;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

public class FldItem extends FieldAdapter
{	

	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) 
	{

	}

	/**
	 * 
	 * @TODO �ֶ�������ƿ�����ø÷������������������ǽ���Ӧ�̵���ϵ�˸�ֵ����һ���ı���
	 * @param component
	 * @throws Exception
	 * @����� 2007-8-7 ����03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	{

		//�õ���������
		Poline pl = (Poline)this.mainObject;
		//�õ��ؼ�
		IBandbox itembox = (IBandbox) component;
		String itemnum = itembox.getValue();
		//ͨ���ؼ���valueֵ����ѯ���
		Item item = (Item) this.mainSrv.getBaseDao().findUniqueBy(Item.class, "itemnum",itemnum);
		List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+pl.getWarehouse()+"' ");
		//���������Ĵ�С>=1
		if (item!=null)
		{
			//�������һ������ֵ�󶨸����������һ������
			this.setValueByColname("description", item.getDescription());
			this.setValueByColname("orderunit", item.getOrderunit());
			this.setValueByColname("modelnum", item.getModelnum());
			this.setValueByColname("orderunit", item.getOrderunit());
			if(inventorylist.size()>0)
			{
				Inventory inventory = (Inventory) inventorylist.get(0);
				this.setValueByColname("stocktype", inventory.getStocktype());
			}
		}

	

	}

}
