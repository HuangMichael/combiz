package combiz.ui.workorder;

import java.util.List;

import combiz.business.budget.BudgetitemSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.IBSServer;
import combiz.system.ui.CommonListWindow;


/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2:56:43 PM  Nov 3, 2008 
 * 功能：弹出的库存表，根据预留信息，将库存中可用的周转件罗列出来。
 */
public class Eqinvrecline extends CommonListWindow {


	public Eqinvrecline() {
		super();
	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：点确定后触发该事件。
	 * 日期：Nov 5, 2008 11:08:18 AM
	 *
	 */
	public void genequse() throws Exception 
	{
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		List retList = this.getSelectObjects();


		WorkorderSrv workorderSrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
		workorderSrv.genequse(retList, wo);
		this.getOwnerWnd().refreshData();
	}
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr2 = null;
		String idstr = null;
		Workorder wo = (Workorder) this.getOwnerWnd().getMainObject();
		String wonum = wo.getWonum();
		
		List InvrList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+ wonum +"'");
		if(InvrList.size() > 0)
		{
		for(int i=0;i<InvrList.size();i++)
		{
			Invreserve invreserve = (Invreserve) InvrList.get(i);
			Double waitqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='待确认'");
			if (waitqty == null) {
				waitqty = 0D;
			}
			if(invreserve.getReservedqty()- waitqty > 0)
			{
				if(idstr2==null)
					idstr2 = invreserve.getId() + "";
				else
					idstr2 = invreserve.getId() + "," + idstr2;
			}
		}
	
		if(idstr2!=null)
			idstr2 = "id in(" + idstr2 + ")";
		else
			idstr2 = "1=2";
		
		List InvrlineList;
		if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
		{
			InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, idstr2+" and wonum = '"+ wonum +"'and wonum in (select t.wonum from Invreserve t where (t.reservedqty - isnull((select sum(i.quantity) from Invusetrans i where i.state ='待确认' and i.itemnum = t.itemnum and i.wonum = t.wonum),0)) >0 and (t.wonum is not null and DATALENGTH(t.wonum) <> 0))");
		}
		else
		{
			InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, idstr2+" and wonum = '"+ wonum +"'and wonum in (select t.wonum from Invreserve t where (t.reservedqty - nvl((select sum(i.quantity) from Invusetrans i where i.state ='待确认' and i.itemnum = t.itemnum and i.wonum = t.wonum),0)) >0 and t.wonum is not null)");
		}
		
		if(InvrlineList.size() > 0)
		{
			for(int i=0;i<InvrlineList.size();i++)
			{
				Invreserve invreserve = (Invreserve) InvrlineList.get(i);
				String itemnum = invreserve.getItemnum();
				/*
				 * 2009-02-19备份 
				 * List invrectranslist = this.getMainSrv().getBaseDao().findWithQuery(Invrectrans.class, "status ='已检验'  and eqnum is not null and rectype  ='接收' and itemnum='"+itemnum+"' and towarehouse ='"+invreserve.getWarehouse()+"'and sitenum='"+invreserve.getSitenum()+"' and corpnum='"+invreserve.getCorpnum()
						+"'and eqnum not in (select t.eqnum from Invusetrans t  where t.issuetype='发放' and eqnum is not null and state ='已完成' and t.itemnum = '"+itemnum+"')");*/
				System.out.println("status ='已检验' and eqnum in(select t.eqnum from Equipment t where  t.itemnum ='"+itemnum+"' and t.location = '0109') and id not in(select distinct t.invrectransid from Invusetrans t where t.state = '待确认' and t.issuetype = '发放'  and t.itemnum ='"+itemnum+"' and t.invrectransid is not null) and rectype  ='接收' and itemnum='"+itemnum+"' and towarehouse ='"+invreserve.getWarehouse()+"'");
				List invrectranslist = this.getMainSrv().getBaseDao().findWithQuery(Invrectrans.class, "status ='已检验' and eqnum in(select t.eqnum from Equipment t where  t.itemnum ='"+itemnum+"' and t.location in(select w.warehouse from Warehouse w)) and id not in(select distinct t.invrectransid from Invusetrans t where t.state = '待确认' and t.issuetype = '发放'  and t.itemnum ='"+itemnum+"' and t.invrectransid is not null) and rectype  ='接收' and itemnum='"+itemnum+"' and towarehouse ='"+invreserve.getWarehouse()+"'");
				if(invrectranslist.size()>0)
				{
					for(int j=0;j<invrectranslist.size();j++)
					{
						Invrectrans invrectrans = (Invrectrans) invrectranslist.get(j);
						String eqnum = invrectrans.getEqnum();
						String lotnum = invrectrans.getTolot();
						//对于此接收行已经发放（归还）的总量。
						Double hasissueqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.issuetype = '发放' and t.eqnum = '"+eqnum+"' and t.lotnum = '"+lotnum+"'");
						//对于此接收行已经归还的总量。
						Double hasrebackqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where (t.issuetype = '归还' or  t.issuetype = '退回') and t.eqnum = '"+eqnum+"' and t.lotnum = '"+lotnum+"'");
						if(hasissueqty==null)
						{
							hasissueqty = 0d;
						}
						if(hasrebackqty==null)
						{
							hasrebackqty = 0d;
						}
						if(invrectrans.getQuantity() - hasissueqty - hasrebackqty >0)
						{
							if(idstr==null)
								idstr = invrectrans.getId() + "";
							else
								idstr = invrectrans.getId() + "," + idstr;
						}
						
					}

				}
			}

			if(idstr!=null)
				idstr = "id in(" + idstr + ")";
			else
				idstr = "1=2";

		}
		else
		{
			idstr = "1=2";
		}

		this.setQueryString(idstr);
		this.refreshData();


	}

	}
}
