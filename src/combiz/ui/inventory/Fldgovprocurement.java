package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

public class Fldgovprocurement extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
				
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
		String isgov = (String) this.getValueByColname("isgovprocurement");
		if(isgov.equals("��"))
		{
			this.setRequiredByColname("supervisor");
			this.setRequiredByColname("usedept");
		}
		else
		{
			this.setNoRequiredByColname("supervisor");
			this.setNoRequiredByColname("usedept");
		}
	}

}
