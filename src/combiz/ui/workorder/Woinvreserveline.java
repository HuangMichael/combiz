package combiz.ui.workorder;


import java.util.List;

import com.inbasis.zul.Messagebox;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Item;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonListWindow;
import combiz.system.util.Util;


/**
 * ���ܣ��ڷ�Ʊ���︴�Ʋɹ�����ʱ�򣬽����������Ĳɹ��������г�����
 *@author ����
 *2008-1-24����12:58:21
 */
public class Woinvreserveline extends CommonListWindow {


	public Woinvreserveline() {
		super();
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���ȷ���󴥷����¼���
	 * ���ڣ�Nov 5, 2008 11:08:18 AM
	 *
	 */
	public void geninvuse() throws Exception 
	{
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		WorkorderSrv workorderSrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
		List retList = this.getSelectObjects();
		if (retList.size() >0)
		{
			for(int i=0;i<retList.size();i++)
			{
				Invreserve invreserve = (Invreserve) retList.get(i);
				String itemnum = invreserve.getItemnum();
				if(Util.isNotNull(itemnum))
				{
					Item item = (Item) this.getMainSrv().getBaseDao().findUniqueBy(Item.class, "itemnum", itemnum);
					if(item != null)
					{
						if(item.getRotating().equals("��"))
						{
							throw new Exception("���Ϊ'"+itemnum+"'����ת���������ڷ���Ԥ�������ѡ��!");
						}
					}
				}
					
			}
			
			workorderSrv.geninvuse(retList, wo);
			this.refreshData();
			this.getOwnerWnd().refreshData();
			Messagebox.show("���ݳɹ����棬ȷ�Ϸ������������У����ɷ���.�������ȫ�����ţ����ڷ��������޸ķ�������!");
			
		}
		else
		{
			throw new Exception("��ѡ��һ����¼��");
		}
	}
	
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		String wonum = wo.getWonum();
		List InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+ wonum +"'");
		if(InvrlineList.size() > 0)
		{
		for(int i=0;i<InvrlineList.size();i++)
		{
			Invreserve invreserve = (Invreserve) InvrlineList.get(i);
			Double inuqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "'");
			Double waitqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='��ȷ��'");
			if(inuqty == null)
				inuqty = 0d;
			if (waitqty == null) {
				waitqty = 0D;
			}
			if(invreserve.getReservedqty()- waitqty > 0)
			{
				if(idstr==null)
					idstr = invreserve.getId() + "";
				else
					idstr = invreserve.getId() + "," + idstr;
			}
		}
	
		if(idstr!=null)
			idstr = "id in(" + idstr + ")";
		else
			idstr = "1=2";
		this.setQueryString(idstr);
		this.refreshData();
		}
		else
			throw new Exception("û��Ԥ���Ŀ����Ŀ");
	
		
	}
	
	
	


}
