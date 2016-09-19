package combiz.ui.inventory;


import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.invoice.Invoice;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;
import combiz.system.util.Util;

import java.util.List;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

public class FldItembinnum extends FieldAdapter
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
		//�õ���������
		Invusetrans inv = (Invusetrans)this.mainObject;
		//�õ��ؼ�
		Bandbox argbin = (Bandbox)component;
		String binnumin = argbin.getValue();
		List Itemlist = null;
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");

		//else
		{
			Double quantity= (Double) this.getValueByColname("quantity");//��������
			
			//ͨ���ؼ���valueֵ����ѯ���
			if(Util.isNull(binnumin))
			{
				Itemlist = this.mainSrv.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse='"+warehouse+"' ");
			}
			else
			{
				String binnum = (String) this.getValueByColname("binnum");//�����
				Itemlist = this.mainSrv.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and binnum='"+binnum+"' and warehouse='"+warehouse+"'");
			}
			
			
//			//���������Ĵ�С>=1
//			if (Itemlist.size() > 0)
//			{
//				Invstock item = (Invstock)Itemlist.get(0);
//			//�������һ������ֵ�󶨸����������һ������
//			Double curbal = item.getCurbal();
//			if(curbal-quantity<0)
//			{
//				this.setValueByColname("binnum", null);
//				Messagebox.show("Ҫ�����������ڸ���������������޷����ţ����ʵ��");
//			}
//			
//			//this.setValueByColname("description", it.getDescription());
//			//this.setValueByColname("stocktype", it.getStocktype());
//			}
//			else
//				Messagebox.show("û�п����������˶Կ����Ŀ�����");
			
		}
		

	}

	@Override
	public String getListWhere(Component ibandbox) 
	{
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invusetrans inv =(Invusetrans) this.getMainObject();
        String itemnum = inv.getItemnum();
        String warehouse = inv.getWarehouse();
        if(warehouse.trim()!= null)
        {
        	if (itemnum != null  && warehouse != null)
        		//��Ӧ�ó���PDXL�������Ч
        		{
        			String whereStr = "itemnum = '" + itemnum +"' and warehouse = '" + warehouse +"' and curbal >0";
        			return whereStr;
        		}
        		else
        			return "1=2";
        }
        else
 
        return "1=2";
       
		
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		String itemnum = (String) this.getValueByColname("itemnum");
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		String lottype = null;
		if(itemlist.size()>0)
		{
			Item item = (Item) itemlist.get(0);
			lottype = item.getLottype();
		}
		Double qty = 0d;
		String warehouse = (String) this.getValueByColname("warehouse");
		Double quantity = (Double) this.getValueByColname("quantity");
		Double reqqty = (Double) this.getValueByColname("reqqty");
		if(reqqty==null)
		{
			reqqty = 0d;
		}
		String binnum = (String) this.getValueByColname("binnum");
		String lotnum = (String) this.getValueByColname("lotnum");
		Invstock invstock = null;
		List curballist = null;
		Double curbal = 0d;
		if(lottype.equals("�����ι���"))
		{
			if(binnum == null)
				//List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and nvl(binnum,'��') = '" + binnum +"'");
			{
				curballist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum is null");
			}
			else
			{
				curballist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum = '" + binnum +"'");
			}
			if(curballist.size()>0)
			{
				invstock =  (Invstock) curballist.get(0);
				curbal = invstock.getCurbal();
			}


		}
		else
		{
			List invlotlist =this.getMainSrv().getBaseDao().findWithQuery(Invlot.class, "itemnum = '"+itemnum+"' and warehouse ='"+warehouse+"' and lotnum = '"+lotnum+"'");
			if(invlotlist.size()>0)
			{
				Invlot invlot = (Invlot) invlotlist.get(0);
				Double lotcost = invlot.getLotcost();
				this.setValueByColname("unitcost", lotcost);
				this.setValueByColname("linecost", lotcost * quantity );
			}
			curballist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and lotnum = '" + lotnum +"'");
			if(curballist.size()>0)
			{
				invstock =  (Invstock) curballist.get(0);
				curbal = invstock.getCurbal();
			}
		}
		if(reqqty - curbal <0)
		{
			qty = reqqty;
		}
		else
		{
			qty = curbal;
		}
		
		this.setValueByColname("quantity", qty);
			
	}
}
