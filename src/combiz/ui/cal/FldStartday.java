package combiz.ui.cal;

import java.util.Date;

import com.inbasis.zk.ui.Component;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICombobox;
import combiz.system.ui.common.IFloatbox;

public class FldStartday 
extends FieldAdapter 
{

	@Override
	public void action(Component component) 
	throws Exception 
	{
	}

	@Override
	public void init(Component component)
	throws Exception 
	{
		String starday = ((ICombobox)component).getValue();
		if(starday!=null && starday.length()>0)
		{
			this.setReadonly(component);
		}
	}

	@Override
	public void validate(Component component) throws Exception {
		// TODO Auto-generated method stub

	}

}
