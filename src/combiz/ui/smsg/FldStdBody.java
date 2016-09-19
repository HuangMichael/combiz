package combiz.ui.smsg;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICombobox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldStdBody extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception 
	{
		Textbox textbox = (Textbox)component;
		String issue = (String) this.getValueByColname("issue");
		if(issue!=null && issue.equals("��"))
		{
			//this.setReadonly(component);
			textbox.setReadonly(true);
		}
		else
		{
			//this.setNoReadonly(component);
			textbox.setReadonly(false);
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