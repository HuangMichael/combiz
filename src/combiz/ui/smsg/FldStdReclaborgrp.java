package combiz.ui.smsg;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;

public class FldStdReclaborgrp extends FieldAdapter
{	

	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception 
	{
		Bandbox bandbox = (Bandbox)component;

		if(bandbox.getValue()!=null && !bandbox.getValue().equals(""))
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
		}		
	}

	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
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
