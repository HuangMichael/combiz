package combiz.ui.workorder;


import combiz.domain.corp.Corpsiteauth;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.domain.user.Ibsgroups;
import combiz.domain.user.Ibsgroupusers;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:17:02 PM  Nov 27, 2008 
 * ���ܣ�
 * �����
 * �����ֶΣ�
 */
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
	*@author ����
	*���ܣ��ڹ�������д�������룬��ֵ�б���ѡ������Ŀ��ź󣬽�������Ϣ���������ֶ��ϡ�
	*@param component
	*@throws Exception 
	*2008-1-27����11:02:15
	*/
	/* (non-Javadoc)
	 * @see combiz.system.FieldAdapter#validate(com.inbasis.zk.ui.Component)
	 */
	public void validate(Component component) 
	throws Exception 
	{
		//�õ���������
		Wpmaterial wpmaterial = (Wpmaterial)this.mainObject;
		String itemnum = (String) this.getValueByColname("itemnum");
		
		
		//ͨ���ؼ���valueֵ����ѯ���
		if (itemnum != null) {
		List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '" + wpmaterial.getWarehouse() +"'");
		
		//���������Ĵ�С>=1
		if(Itmlist.size()>0){
			Inventory inv = (Inventory)Itmlist.get(0);
			//�������һ������ֵ�󶨸����������һ������
			this.setValueByColname("orderunit", inv.getOrderunit());
			this.setValueByColname("unitcost", inv.getLastcost());
			this.setValueByColname("vendor", inv.getVendor());
			this.setValueByColname("manufactureer", inv.getManufacturer());
		}
		else
		{
				List Itemlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
				if (Itemlist.size()<0) {
					Messagebox.show("��ѡ������ϲ��������ʱ��룬����ϵ����Ա��ʵ��");
					return;
				}
				//Item item = (Item) Itemlist.get(0);
				this.setValueByColname("vendor", null);
				this.setValueByColname("manufactureer", null);
			}
		}
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}


	@Override
	public String getListWhere(Component arg0) {
		String whr = null;
		String whereStr = null;
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
		Wpmaterial wp = (Wpmaterial) this.getMainObject();
		
		String itemnum = (String) this.getValueByColname("itemnum");
		List invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' ");
		if(invstocklist.size()>0)
		{
			for(int i=0;i<invstocklist.size();i++)
			{
				Invstock invstock = (Invstock) invstocklist.get(i);
				String warehouse = invstock.getWarehouse();
				if(whr == null)
				{
					whr = "'"+warehouse+"'";
				}
				else
				{
					whr = "'"+warehouse+"',"+whr;
				}
			}
			whereStr = "warehouse in(" + whr +") ";
		}
		else
		{
			whereStr="1=2";
		}


		return whereStr;
	}


	
	

}
