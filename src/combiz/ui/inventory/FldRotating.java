package combiz.ui.inventory;

import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;
import combiz.system.ui.common.ICombobox;

import com.inbasis.zk.ui.Component;
public class FldRotating extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}
	/* 
	 * 功能：
	 * 作者：李建红
	 * 日期：2008-11-2下午03:37:06
	 */
	public void validate(Component component) 
	throws Exception 
	{
			
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		Item inv =(Item) this.getMainObject();
		ICheckbox checkrot = (ICheckbox) component;
		ICombobox com = (ICombobox) component.getFellow("item.lottype");

		if (!(checkrot.getValue().equals("否"))) {
			this.setValueByColname("lottype","批次管理");
			this.setReadonly(com);
		}else{
			this.setNoReadonly(com);
		}
			
	}

}
