package combiz.ui.pr;

import combiz.business.pr.PrSrv;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;

public class UnitePrline extends CommonListWindow
{


	public UnitePrline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{

		super.onCreate();
		String idstr = null;
		List copylist = this.getMainSrv().getBaseDao().findWithQuery(Prline.class, "toprnum is null and toprlinenum is null and prnum in(select t.prnum from Pr t where t.prtype='需求计划')");
		if(copylist.size() > 0)
		{
			for(int i=0;i<copylist.size();i++)
			{
				Prline prline = (Prline) copylist.get(i);
				String prnum = prline.getPrnum();
				List Prlist = this.getMainSrv().getBaseDao().findWithQuery(Pr.class, "prnum = '"+ prnum+"'");
				if(Prlist.size() >0)
				{
					Pr pr = (Pr) Prlist.get(0);
					String status = pr.getStatus();
					if(status.equals("已批准"))
					{

						if(idstr==null)
							idstr = prline.getId() + "";
						else
							idstr = prline.getId() + "," + idstr;
						
					}
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
		{
			idstr = "1=2";
			this.setQueryString(idstr);
			this.refreshData();
//			throw new Exception("没有批准过的采购申请！");
			
		}
			
		
		
	
		
	}
	
	public void uniteprline() throws Exception
	{
		Pr pr = (Pr) this.getOwnerWnd().getMainObject();
		List retList = this.getSelectObjects();
		String s = pr.getPrnum();
		if(retList.size()>0)
		{
			((PrSrv)this.getOwnerWnd().getMainSrv()).uniteprline(retList,pr);
			this.refreshData();
			
		}
		RecWindow mainwnd = this.getOwnerWnd();
		mainwnd.refreshChildData();
		
	}


}
