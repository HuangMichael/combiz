package combiz.ui.smsg;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Combobox;

public class FldStdSendtype extends FieldAdapter
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
			this.setRequired(component);
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
		if(combobox.getValue().equals("˽��")){
			//this.setReadonly(combobox.getFellow("msgsender.issue"));

			this.setNoReadonly(combobox.getFellow("msgsender.rectype"));
			this.setRequired(combobox.getFellow("msgsender.rectype"));
			
		}else if(combobox.getValue().equals("����")){
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
