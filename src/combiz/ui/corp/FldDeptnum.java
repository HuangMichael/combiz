package combiz.ui.corp;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.Executions;

public class FldDeptnum extends FieldAdapter {

	@Override
	public void action(Component component)
	throws Exception 
	{
		String deptnum = (String) this.getValueByColname("deptnum");
		this.setValueByColname("sitenum", deptnum);
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
		return null;
	}
	
}
