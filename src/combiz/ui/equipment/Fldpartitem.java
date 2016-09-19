package combiz.ui.equipment;

import com.inbasis.zk.ui.Component;
import combiz.system.FieldAdapter;

public class Fldpartitem extends FieldAdapter
{

	@Override
	public void action(Component arg0)
	throws Exception 
	{
		
	}

	@Override
	public void init(Component arg0)
	throws Exception 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Component arg0) 
	throws Exception 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public String getListWhere(Component arg0) {
		// TODO 自动生成方法存根
		
		String whereStr = "rotating = '否'";
		return whereStr;
		
	}	
}
