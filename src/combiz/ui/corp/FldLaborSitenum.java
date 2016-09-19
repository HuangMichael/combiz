package combiz.ui.corp;

import java.util.List;

import combiz.business.corp.CorpsiteauthSrv;
import combiz.business.corp.LaborSrv;
import combiz.business.user.IbsgroupsSrv;
import combiz.business.user.IbsgroupusersSrv;
import combiz.domain.corp.Corpsiteauth;
import combiz.domain.corp.Labor;
import combiz.domain.user.Ibsgroups;
import combiz.domain.user.Ibsgroupusers;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.common.IBandbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.Executions;

public class FldLaborSitenum extends FieldAdapter {

	@Override
	public void action(Component component)
	throws Exception 
	{

	}

	@Override
	public void init(Component component) 
	throws Exception 
	{
	
	}

	@Override
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	@Override
	public String getListWhere(Component ibandbox)
	{
 		String whr = null;
 		String whereStr = null;
		//从编辑窗口获取对应的LIST窗口
		String labornum = this.getUserInfo().getLabornum();
		Labor labor = (Labor) this.getMainSrv().getBaseDao().findUniqueBy(Labor.class, "labornum", labornum);
		String groupname = null;
		String sitenum = labor.getSitenum();
		String userid = this.getUserInfo().getUserid();
		List ibsgroupuserslist = this.getMainSrv().getBaseDao().findWithQuery(Ibsgroupusers.class, "userid ='"+userid+"'");
		if(ibsgroupuserslist.size() > 0)
		{
			for(int j=0;j<ibsgroupuserslist.size();j++)
			{
				Ibsgroupusers bsgroupusers =(Ibsgroupusers) ibsgroupuserslist.get(j);
				groupname = bsgroupusers.getGrpname();
				Ibsgroups ibsgroups = (Ibsgroups) this.getMainSrv().getBaseDao().findUniqueBy(Ibsgroups.class, "grpname",groupname);
				if(ibsgroups != null)
				{
					String allsite = ibsgroups.getAllsite();
					if(allsite.equals("是"))
					{
						whereStr = "1=1";
						break;
					}
					else
					{
						List corpsiteauthlist = this.getMainSrv().getBaseDao().findWithQuery(Corpsiteauth.class, "grpname = '"+groupname+"'");
						if(corpsiteauthlist.size() > 0)
						{
							for(int i=0;i<corpsiteauthlist.size();i++)
		            		{
								Corpsiteauth corpsiteauth = (Corpsiteauth) corpsiteauthlist.get(i);
		            			String authsite = corpsiteauth.getSitenum();
		            			if(whr == null)
		            			{
		            				whr = "'"+authsite+"'";
		            			}
		            			else
		            			{
		            				whr = "'"+authsite+"',"+whr;
		            			}
		            		}
							
							
						}
						else
						{
							whereStr = "1=2";
						}
						if(whr == null)
	        			{
							whereStr = "1=2";
	        			}
	        			else
	        			{
	        				whereStr = "sitenum in(" + whr +")";
	        			}
						
						
					}
				}
				
			}
		}
		return whereStr;
	}
	
}
