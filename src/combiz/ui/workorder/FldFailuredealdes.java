package combiz.ui.workorder;

import combiz.domain.workorder.Wofaildeal;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldFailuredealdes extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		Wofaildeal wfail  = (Wofaildeal) this.getMainObject();
		String failuredeal = (String) this.getValueByColname("failuredeal");
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Failuredeal t where t.failuredeal = '"+failuredeal+"' and t.failurecause = '"+wfail.getFailurecause()+"'");
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
