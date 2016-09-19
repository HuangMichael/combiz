package combiz.ui.workorder;

import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofailproblem;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldFailurecaudes extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		Wofailcause wfail  = (Wofailcause) this.getMainObject();
		String failurecause = (String) this.getValueByColname("failurecause");
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Failurecause t where t.failureproblem = '"+wfail.getFailureproblem()+"' and t.failurecause = '"+failurecause+"'");
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
