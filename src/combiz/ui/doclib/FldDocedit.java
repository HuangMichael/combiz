package combiz.ui.doclib;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldDocedit extends FieldAdapter
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
		String chk = (String) this.getValueByColname("docedit");
		if(chk!=null && chk.equals("��"))
		{
			this.setValueByColname("docread", "��");
		}
	}

}
