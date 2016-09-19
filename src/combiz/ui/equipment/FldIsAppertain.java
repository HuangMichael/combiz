package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.domain.rfq.Rfqquoteline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ICombobox;
import combiz.system.ui.common.IDoublebox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Combobox;

public class FldIsAppertain extends FieldAdapter
{

	@Override
	public void action(Component com)
	throws Exception 
	{
		
		Combobox combobox = (Combobox) com;
		String value = combobox.getValue();
		IBandbox target =(IBandbox) com.getFellow("equipment.parent");
		if(Util.isNotNull(value) && value.equals("ÊÇ"))
		{
			this.setNoReadonly(target);
			this.setRequired(target);
			
		}
		else
		{
			this.setReadonly(target);
		    this.setNoRequired(target);
		}
		

	}

	@Override
	public void init(Component com) throws Exception {
		// TODO Auto-generated method stub
		
		Combobox combobox = (Combobox) com;
		String value = combobox.getValue();
		IBandbox target =(IBandbox) com.getFellow("equipment.parent");
		if(Util.isNotNull(value) && value.equals("ÊÇ"))
		{
			this.setNoReadonly(target);
			this.setRequired(target);
			
		}
		else
		{
			this.setReadonly(target);
		    this.setNoRequired(target);
		}
		
	
			
		
	}

	@Override
	public void validate(Component com)
	throws Exception 
	{
/*		Combobox combobox = (Combobox) com;
		String value = combobox.getValue();
		IBandbox target =(IBandbox) com.getFellow("equipment.parent");
		if(Util.isNotNull(value) && value.equals("ÊÇ"))
		{
			this.setRequired(target);
		}
		else
			this.setNoRequired(target);*/
/*		if(Util.isNotNull(value) && value.equals("·ñ"))
		{
			this.setReadonly(target);
		}*/
		
		
		
	}
	
}
