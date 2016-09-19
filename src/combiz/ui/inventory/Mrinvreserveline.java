package combiz.ui.inventory;


import java.util.List;

import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.CommonListWindow;


/**
 * ���ܣ��ڷ�Ʊ���︴�Ʋɹ�����ʱ�򣬽����������Ĳɹ��������г�����
 *@author ����
 *2008-1-24����12:58:21
 */
public class Mrinvreserveline extends CommonListWindow {


	public Mrinvreserveline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		String matreqnum = matreq.getMatreqnum();
		List InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "reqnum = '"+ matreqnum +"'");
		if(InvrlineList.size() > 0)
		{
		for(int i=0;i<InvrlineList.size();i++)
		{
			Invreserve invreserve = (Invreserve) InvrlineList.get(i);
			Double inuqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + matreqnum + "' and t.itemnum = '" + invreserve.getItemnum() + "'");
			Double waitqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + matreqnum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='��ȷ��'");
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
