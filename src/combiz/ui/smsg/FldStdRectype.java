package combiz.ui.smsg;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Combobox;

public class FldStdRectype extends FieldAdapter
{	

	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
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
			//this.setRequired(component);
		}		
	}

	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
	 */
	public void validate(Component component) 
	throws Exception 
	{
		Combobox combobox = (Combobox)component;
		if(combobox.getValue().equals("����")){
			this.setNoRequired(combobox.getFellow("msgsender.recdept"));
			this.setNoRequired(combobox.getFellow("msgsender.reclaborgrp"));
			this.setValueByColname("recdept", "");
			this.setValueByColname("reclaborgrp", "");
			this.setReadonly(combobox.getFellow("msgsender.recdept"));
			this.setReadonly(combobox.getFellow("msgsender.reclaborgrp"));
			this.setNoReadonly(combobox.getFellow("msgsender.reclabor"));
			this.setRequired(combobox.getFellow("msgsender.reclabor"));
		}else if(combobox.getValue().equals("��Ա��")){
			this.setNoRequired(combobox.getFellow("msgsender.recdept"));
			this.setNoRequired(combobox.getFellow("msgsender.reclabor"));
			this.setValueByColname("recdept", "");
			this.setValueByColname("reclabor", "");
			this.setReadonly(combobox.getFellow("msgsender.recdept"));
			this.setReadonly(combobox.getFellow("msgsender.reclabor"));
			this.setNoReadonly(combobox.getFellow("msgsender.reclaborgrp"));
			this.setRequired(combobox.getFellow("msgsender.reclaborgrp"));
		}else if(combobox.getValue().equals("����")){
			this.setNoRequired(combobox.getFellow("msgsender.reclaborgrp"));
			this.setNoRequired(combobox.getFellow("msgsender.reclabor"));
			this.setValueByColname("reclaborgrp", "");
			this.setValueByColname("reclabor", "");
			this.setReadonly(combobox.getFellow("msgsender.reclaborgrp"));
			this.setReadonly(combobox.getFellow("msgsender.reclabor"));
			this.setNoReadonly(combobox.getFellow("msgsender.recdept"));
			this.setRequired(combobox.getFellow("msgsender.recdept"));
		}else{
			this.setNoRequired(combobox.getFellow("msgsender.reclaborgrp"));
			this.setNoRequired(combobox.getFellow("msgsender.reclabor"));
			this.setReadonly(combobox.getFellow("msgsender.recdept"));
			this.setValueByColname("reclaborgrp", "");
			this.setValueByColname("reclabor", "");
			this.setValueByColname("recdept", "");
			this.setReadonly(combobox.getFellow("msgsender.reclaborgrp"));
			this.setReadonly(combobox.getFellow("msgsender.reclabor"));
			this.setReadonly(combobox.getFellow("msgsender.recdept"));
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
