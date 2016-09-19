package combiz.ui.smsg;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICombobox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldStdTitle extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	throws Exception 
	{
		Textbox textbox = (Textbox)component;
		String issue = (String) this.getValueByColname("issue");
		if(issue!=null && issue.equals("是"))
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
			this.setRequired(component);
		}	
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
		
	}

}
