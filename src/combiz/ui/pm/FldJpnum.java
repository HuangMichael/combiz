package combiz.ui.pm;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2008-3-20上午11:01:23
 * 功能：在预防性维护中，如果选择使用作业序列，则将字段“标准作业计划”置为可编辑状态，否则置为不可编辑状态。
 * 捆绑表：premaint
 * 捆绑字段：jpnum
 */
public class FldJpnum extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
		ICheckbox arg = (ICheckbox) this.getFellow("premaint.usejpseq");
		if(arg.getValue().equals("否"))
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
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
