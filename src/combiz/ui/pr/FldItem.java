package combiz.ui.pr;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;

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

		Prline pl = (Prline)this.mainObject;
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		String orderunit = null;
		Double avgcost = 0d;
		List Itemlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		//���������Ĵ�С>=1
		if(Itemlist.size()>0){
			Item it = (Item)Itemlist.get(0);
			pl.setDescription(it.getDescription());
			pl.setInspection(it.getInspectreq());
			if(Util.isNotNull(warehouse))
			{
				List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+warehouse+"' ");
				if(inventorylist.size()>0)
				{
					Inventory inventory = (Inventory) inventorylist.get(0);
					orderunit = inventory.getOrderunit();
					avgcost = inventory.getAvgcost();
				}
				else
				{
					orderunit = it.getOrderunit();
				}
				this.setValueByColname("orderunit", orderunit);
				this.setValueByColname("unitcost", avgcost);


			}

		}else
		{
			this.setValueByColname("description", "");
		}
		
	}

}
