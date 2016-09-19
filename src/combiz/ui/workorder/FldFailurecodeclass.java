package combiz.ui.workorder;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:47:45 PM  Dec 17, 2008 
 * ���ܣ�������ϴ�����ڵĻ�����ʼ��Ϊֻ����
 * �����wofailcode
 * �����ֶΣ�classid
 */
public class FldFailurecodeclass extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		String failurecode = (String) this.getValueByColname("failurecode");
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Failurecode t where t.failurecode = '"+failurecode+"'");
		if(count>0)
		{
			this.setReadonly(component);
			this.setRequired(component);
		}
		else
		{
			this.setNoReadonly(component);
			this.setNoRequired(component);
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
