package combiz.ui.location;

import com.inbasis.zk.ui.Component;

import combiz.system.FieldAdapter;
import combiz.system.util.Util;

public class FldLocstructParent extends FieldAdapter
{

	@Override
	public void action(Component component) throws Exception
	{
		
	}

	@Override
	public void init(Component component) throws Exception
	{
		
	}

	@Override
	public void validate(Component component) throws Exception
	{
		
	}

	@Override
	public String getListWhere(Component component)
	{
		String systemid = (String) this.getValueByColname("systemid");
		if(Util.isNotNull(systemid))
			return "systemid='"+systemid+"'";
		else
			return "1=2";
	}
	
	

}
