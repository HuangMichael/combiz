package combiz.ui.smsg;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Combobox;

public class FldStdSendtype extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception 
	{
		Combobox combobox = (Combobox)component;

		if(combobox.getValue()!=null && !combobox.getValue().equals(""))
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
		Combobox combobox = (Combobox)component;
		if(combobox.getValue().equals("私有")){
			//this.setReadonly(combobox.getFellow("msgsender.issue"));

			this.setNoReadonly(combobox.getFellow("msgsender.rectype"));
			this.setRequired(combobox.getFellow("msgsender.rectype"));
			
		}else if(combobox.getValue().equals("公告")){
			this.setNoRequired(combobox.getFellow("msgsender.rectype"));
			this.setNoRequired(combobox.getFellow("msgsender.reclabor"));
			this.setNoRequired(combobox.getFellow("msgsender.reclaborgrp"));
			this.setNoRequired(combobox.getFellow("msgsender.recdept"));
			this.setReadonly(combobox.getFellow("msgsender.rectype"));
			this.setReadonly(combobox.getFellow("msgsender.reclabor"));
			this.setReadonly(combobox.getFellow("msgsender.recdept"));
			this.setReadonly(combobox.getFellow("msgsender.reclaborgrp"));
			this.setValueByColname("rectype", "");
			this.setValueByColname("reclabor", "");
			this.setValueByColname("recdept", "");
			this.setValueByColname("reclaborgrp", "");
		}
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception 
	{
		
	}

}
