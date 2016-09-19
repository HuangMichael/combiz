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
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 1:58:24 PM  Feb 13, 2009 
 * 功能：根据报废申请的提出人，从资产台帐中过滤数据。申请人提出报损报废申请单，如果机关服务分部提出申请，报废明细从库存和所有资产中选择，否则，从提出人所在的部门选择资产。
 */
public class Eqrejectline extends CommonListWindow {


	public Eqrejectline() {
		super();
	}
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：将选中的报废行加入到报废申请中
	 * 日期：2:41:15 PM  Feb 13, 2009 
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
	 * 功能：根据申请提出人，从资产台帐中过滤满足条件的数据。
	 * 作者：李阳
	 * 日期：Feb 13, 2009  2:02:37 PM
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
		if(Util.isNotNull(idstr2))//是否已经拷贝了部分报废明细行。
		{
			if(dept.equals("机关服务分部"))
			{
				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class," id not in(" + idstr2 + ") and status != '报废'");
			}
			else
			{
				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class," id not in(" + idstr2 + ") and status != '报废' and deptnum ='"+dept+"'");
			}
		}
		else
		{
			if(dept.equals("机关服务分部"))
			{
				idstr = " status != '报废'";
				
//				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class,"sitenum='"+reject.getSitenum()+"' and status = '在用'");
			}
			else
			{
				idstr = " status != '报废' and deptnum ='"+dept+"'";
//				EqList = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class,"sitenum='"+reject.getSitenum()+"' and status = '在用' and deptnum ='"+dept+"'");
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
