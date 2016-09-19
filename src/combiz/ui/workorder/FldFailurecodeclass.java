package combiz.ui.workorder;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2:47:45 PM  Dec 17, 2008 
 * 功能：如果故障代码存在的话，初始化为只读。
 * 捆绑表：wofailcode
 * 捆绑字段：classid
 */
public class FldFailurecodeclass extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
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
