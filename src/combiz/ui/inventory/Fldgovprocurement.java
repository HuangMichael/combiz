package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

public class Fldgovprocurement extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
				
	}

	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{

	
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		String isgov = (String) this.getValueByColname("isgovprocurement");
		if(isgov.equals("是"))
		{
			this.setRequiredByColname("supervisor");
			this.setRequiredByColname("usedept");
		}
		else
		{
			this.setNoRequiredByColname("supervisor");
			this.setNoRequiredByColname("usedept");
		}
	}

}
