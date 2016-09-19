package combiz.ui.rfq;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.rfq.Rfqline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
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
	 * @TODO �ֶ�������ƿ�����ø÷������������������ǽ������Ŀ�����������͸�ֵ��������ı���
	 * @param component
	 * @throws Exception
	 * @����� 2007-8-14 ����11:14:39
	 */
	public void validate(Component component) 
	throws Exception 
	{

	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		//�õ���������
		Rfqline rl = (Rfqline)this.mainObject;
		//�õ��ؼ�
		String itemnum = (String) this.getValueByColname("itemnum");
		if(itemnum!=null)
		{
			//ͨ���ؼ���valueֵ����ѯ���
			List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
			List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+rl.getWarehouse()+"'");
			//���������Ĵ�С>=1
			if(Itmlist.size()>0)
			{
				Item it = (Item)Itmlist.get(0);
				
				this.setValueByColname("description",it.getDescription());
				this.setValueByColname("INSPECTION",it.getInspectreq());
				this.setValueByColname("Modelnum",it.getModelnum());
				this.setValueByColname("Orderunit",it.getOrderunit());
				
				if(inventorylist.size()>0)
				{
					Inventory inventory = (Inventory) inventorylist.get(0);
					this.setValueByColname("stocktype", inventory.getStocktype());
				}
			}
		}
	}

}
