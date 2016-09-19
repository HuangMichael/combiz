package combiz.ui.inventory;


import java.util.List;

import com.inbasis.zul.Window;

import combiz.business.inventory.MatreqSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.system.IBSServer;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;


/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:56:43 PM  Nov 3, 2008 
 * ���ܣ������Ŀ���������Ԥ����Ϣ��������п��õ���ת�����г�����
 */
public class Eqinvrecline extends CommonListWindow {


	public Eqinvrecline() {
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
		Invrectrans invrec = (Invrectrans) this.getMainObject();
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		List retList = this.getSelectObjects();
		((MatreqSrv) this.getOwnerWnd().getMainSrv()).genequse(retList, matreq);
		this.getOwnerWnd().refreshData();
	}
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr2 = null;
		String idstr = null;
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		String matreqnum = matreq.getMatreqnum();
		
		List InvrList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "reqnum = '"+ matreqnum +"'");
		if(InvrList.size() > 0)
		{
		for(int i=0;i<InvrList.size();i++)
		{
			Invreserve invreserve = (Invreserve) InvrList.get(i);
			Double waitqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + matreqnum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='��ȷ��'");
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
		
		String abc = idstr2+"' and reqnum = '"+ matreqnum +"'and reqnum in (select t.reqnum from Invreserve t where (t.reservedqty - isnull((select sum(i.quantity) from Invusetrans i where i.state ='��ȷ��' and i.itemnum = t.itemnum and i.matreqnum = t.reqnum),0)) >0 and (t.reqnum is not null and DATALENGTH(t.reqnum) <> 0))";
		List InvrlineList;
		if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
		{
			InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, idstr2+" and reqnum = '"+ matreqnum +"'and reqnum in (select t.reqnum from Invreserve t where (t.reservedqty - isnull((select sum(i.quantity) from Invusetrans i where i.state ='��ȷ��' and i.itemnum = t.itemnum and i.matreqnum = t.reqnum),0)) >0 and (t.reqnum is not null and DATALENGTH(t.reqnum) <> 0))");
		}
		else
		{
			InvrlineList = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, idstr2+" and reqnum = '"+ matreqnum +"'and reqnum in (select t.reqnum from Invreserve t where (t.reservedqty - nvl((select sum(i.quantity) from Invusetrans i where i.state ='��ȷ��' and i.itemnum = t.itemnum and i.matreqnum = t.reqnum),0)) >0 and t.reqnum is not null)");
		}
		
		if(InvrlineList.size() > 0)
		{
			for(int i=0;i<InvrlineList.size();i++)
			{
				Invreserve invreserve = (Invreserve) InvrlineList.get(i);
				String itemnum = invreserve.getItemnum();
				/*
				 * 2009-02-19���� 
				 * List invrectranslist = this.getMainSrv().getBaseDao().findWithQuery(Invrectrans.class, "status ='�Ѽ���'  and eqnum is not null and rectype  ='����' and itemnum='"+itemnum+"' and towarehouse ='"+invreserve.getWarehouse()+"'and sitenum='"+invreserve.getSitenum()+"' and corpnum='"+invreserve.getCorpnum()
						+"'and eqnum not in (select t.eqnum from Invusetrans t  where t.issuetype='����' and eqnum is not null and state ='�����' and t.itemnum = '"+itemnum+"')");*/
				System.out.println("status ='�Ѽ���' and eqnum in(select t.eqnum from Equipment t where  t.itemnum ='"+itemnum+"' and t.location = '0109') and id not in(select distinct t.invrectransid from Invusetrans t where t.state = '��ȷ��' and t.issuetype = '����'  and t.itemnum ='"+itemnum+"' and t.invrectransid is not null) and rectype  ='����' and itemnum='"+itemnum+"' and towarehouse ='"+invreserve.getWarehouse()+"'");
				List invrectranslist = this.getMainSrv().getBaseDao().findWithQuery(Invrectrans.class, "status ='�Ѽ���' and eqnum in(select t.eqnum from Equipment t where  t.itemnum ='"+itemnum+"' and t.location in(select w.warehouse from Warehouse w)) and id not in(select distinct t.invrectransid from Invusetrans t where t.state = '��ȷ��' and t.issuetype = '����'  and t.itemnum ='"+itemnum+"' and t.invrectransid is not null) and rectype  ='����' and itemnum='"+itemnum+"' and towarehouse ='"+invreserve.getWarehouse()+"'");
				if(invrectranslist.size()>0)
				{
					for(int j=0;j<invrectranslist.size();j++)
					{
						Invrectrans invrectrans = (Invrectrans) invrectranslist.get(j);
						String eqnum = invrectrans.getEqnum();
						String lotnum = invrectrans.getTolot();
						//���ڴ˽������Ѿ����ţ��黹����������
						Double hasissueqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.issuetype = '����' and t.eqnum = '"+eqnum+"' and t.lotnum = '"+lotnum+"'");
						//���ڴ˽������Ѿ��黹��������
						Double hasrebackqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where (t.issuetype = '�黹' or  t.issuetype = '�˻�') and t.eqnum = '"+eqnum+"' and t.lotnum = '"+lotnum+"'");
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