package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 1:38:22 PM  Nov 27, 2008 
 * ���ܣ��������ת���Ļ����������;�Ϊ�����ι�������Ϊֻ����
 * �����ITEM
 * �����ֶΣ�lottype
 */
public class FldLottype extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		String rotating = (String) this.getValueByColname("rotating");
		if(rotating.equals("��"))
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
