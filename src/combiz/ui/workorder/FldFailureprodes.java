package combiz.ui.workorder;

import combiz.domain.workorder.Wofailproblem;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldFailureprodes extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
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
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
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
