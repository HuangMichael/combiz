package combiz.ui.pm;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2008-3-20����11:01:23
 * ���ܣ���Ԥ����ά���У����ѡ��ʹ����ҵ���У����ֶΡ���׼��ҵ�ƻ�����Ϊ�ɱ༭״̬��������Ϊ���ɱ༭״̬��
 * �����premaint
 * �����ֶΣ�jpnum
 */
public class FldJpnum extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		ICheckbox arg = (ICheckbox) this.getFellow("premaint.usejpseq");
		if(arg.getValue().equals("��"))
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
