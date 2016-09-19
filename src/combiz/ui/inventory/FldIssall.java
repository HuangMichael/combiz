package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 6:49:52 PM  Jul 22, 2008 
 * 功能：在库房盘点中，如果选择盘点所有库房，则将字段“库房代码”置为不可编辑状态，同时清空库房代码字段中内容，否则置为可编辑状态。
 * 捆绑表：hgequstocktake
 * 捆绑字段：isall
 */
public class FldIssall extends FieldAdapter
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
		ICheckbox arg = (ICheckbox) component;
		Bandbox arg1 = (Bandbox) component.getFellow("checkqty.chkwarehouse");
		Textbox arg2 = (Textbox) component.getFellow("checkqty.checkbinnum");
		Textbox arg3 = (Textbox) component.getFellow("checkqty.classid");
		Textbox arg4 = (Textbox) component.getFellow("checkqty.itemnum");
		if(arg.getValue().equals("是"))
		{
			this.setReadonly(arg1);
			this.setReadonly(arg2);
			this.setReadonly(arg3);
			this.setReadonly(arg4);
			this.setValueByColname("chkwarehouse", "");
			this.setValueByColname("checkbinnum", "");
			this.setValueByColname("classid", "");
			this.setValueByColname("itemnum", "");
		}
		else
		{
			this.setNoReadonly(arg1);
			this.setNoReadonly(arg2);
			this.setNoReadonly(arg3);
			this.setNoReadonly(arg4);
		}	
		
	}

}
