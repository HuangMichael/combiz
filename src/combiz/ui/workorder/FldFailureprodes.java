package combiz.ui.workorder;

import combiz.domain.workorder.Wofailproblem;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldFailureprodes extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		Wofailproblem wfail  = (Wofailproblem) this.getMainObject();
		String failureproblem = (String) this.getValueByColname("failureproblem");
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Failureproblem t where t.failureproblem  ='"+failureproblem+"' and t.failurecode = '"+wfail.getFailurecode()+"'");
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
