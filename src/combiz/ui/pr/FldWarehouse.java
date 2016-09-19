package combiz.ui.pr;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldWarehouse extends FieldAdapter
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

		Prline pl = (Prline)this.getMainObject();
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		String orderunit = null;
		Double avgcost = 0d;
		
		List itemlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		if(itemlist.size()>0)
		{
			Item item = (Item) itemlist.get(0);
			orderunit = item.getOrderunit();
		}
		
		if(Util.isNotNull(warehouse))
		{
			List inventorylist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+warehouse+"' ");
			if(inventorylist.size()>0)
			{
				Inventory inventory = (Inventory) inventorylist.get(0);
				orderunit = inventory.getOrderunit();
				avgcost = inventory.getAvgcost();
			}
		}
		this.setValueByColname("orderunit", orderunit);
		this.setValueByColname("unitcost", avgcost);
	
		
	}

}
