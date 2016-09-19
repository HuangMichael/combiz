package combiz.ui.corp;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.Executions;

public class FldSitenum extends FieldAdapter {

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
		//附加地点授权SQL
		String allsite = (String) Executions.getCurrent().getDesktop().getSession().getAttribute("allsite");
		//授权所有地点
		if(allsite.equals("true"))
		{
			return null;
		}
		else
		{
			//如果地点字段不是必输的字段，那么可以查看到地点字段为空的所有数据
			//return "sitenum in(" + allsite + ")";
			return "sitenum in(select t.deptnum from Deptance t where t.deptance='"+allsite+"')";
		}

	}
	
}
