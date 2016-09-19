package combiz.ui.inventory;

import java.util.List;

import combiz.business.inventory.RejectSrv;
import combiz.business.po.PoSrv;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.IBSServer;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;


/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 1:58:24 PM  Feb 13, 2009 
 * ���ܣ����ݱ������������ˣ����ʲ�̨���й������ݡ�������������𱨷����뵥��������ط���ֲ�������룬������ϸ�ӿ��������ʲ���ѡ�񣬷��򣬴���������ڵĲ���ѡ���ʲ���
 */
public class Eqrejectline extends CommonListWindow {


	public Eqrejectline() {
		super();
	}
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���ѡ�еı����м��뵽����������
	 * ���ڣ�2:41:15 PM  Feb 13, 2009 
	 *
	 */
	public void genrejectline() throws Exception
	{
		Reject reject = (Reject) this.getOwnerWnd().getMainObject();
		List equiplist = this.getSelectObjects();
		String s = reject.getRejectnum();
		if(equiplist.size()>0)
		{
			((RejectSrv) this.getOwnerWnd().getMainSrv()).genrejectline(equiplist,s);
			
		}
		RecWindow mainwnd = this.getOwnerWnd();
		mainwnd.refreshChildData();
		
		
		
	}
	
	
	/* 
	 * ���ܣ�������������ˣ����ʲ�̨���й����������������ݡ�
	 * ���ߣ�����
	 * ���ڣ�Feb 13, 2009  2:02:37 PM
	 */
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		String idstr2 = null;
		Reject reject = (Reject) this.getOwnerWnd().getMainObject();
		String rejectnum = reject.getRejectnum();
		String dept = reject.getReqdept();
		List rejectitemlist = this.getMainSrv().getBaseDao().findWithQuery(Rejectitem.class, "rejectnum='"+rejectnum+"'");
		if(rejectitemlist.size()>0)
		{
			for(int j=0;j<rejectitemlist.size();j++)
			{
				Rejectitem rejectitem = (Rejectitem) rejectitemlist.get(j);
				Long eqid = rejectitem.getInvstockid();
				if(idstr2==null)
				{
					idstr2 = eqid + "";
				}
				else
				{
					idstr2 = eqid + "," + idstr2;
				}
			}
			
			
		}
		
		List EqList;
		if(Util.isNotNull(idstr2))//�Ƿ��Ѿ������˲��ֱ�����ϸ�С�
		{
			if(dept.equals("���ط���ֲ�"))
			{
				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class," id not in(" + idstr2 + ") and status != '����'");
			}
			else
			{
				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class," id not in(" + idstr2 + ") and status != '����' and deptnum ='"+dept+"'");
			}
		}
		else
		{
			if(dept.equals("���ط���ֲ�"))
			{
				idstr = " status != '����'";
				
//				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class,"sitenum='"+reject.getSitenum()+"' and status = '����'");
			}
			else
			{
				idstr = " status != '����' and deptnum ='"+dept+"'";
//				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class,"sitenum='"+reject.getSitenum()+"' and status = '����' and deptnum ='"+dept+"'");
			}
		}
		
		
		/*if(EqList.size() > 0)
		{
			for(int i=0;i<EqList.size();i++)
			{
				Equipment equip = (Equipment) EqList.get(i);
				if(idstr==null)
				{
					idstr = equip.getId() + "";
				}
				else
				{
					idstr = equip.getId() + "," + idstr;
				}

			}

			if(idstr!=null)
			{
				idstr = "id in(" + idstr + ")";
			}
				
			else
			{
				idstr = "1=2";
			}
				

		}
		else
		{
			idstr = "1=2";
		}
*/
		this.setQueryString(idstr);
		this.refreshData();


	}


}
