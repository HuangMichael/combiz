package combiz.ui.pr;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.system.ui.CommonListWindow;
import java.util.List;

public class CopyPrline extends CommonListWindow
{


	public CopyPrline() {
		super();
	}
	
	
	public void onCreate() throws Exception
	{
		super.onCreate();
		String idstr = null;
		List copylist = this.getMainSrv().getBaseDao().findWithQuery(Prline.class, "ponum is null and polinenum is null and prnum in(select p.prnum from Pr p where p.prtype ='采购申请')");
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


}
