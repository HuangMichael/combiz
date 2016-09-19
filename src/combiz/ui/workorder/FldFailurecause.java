package combiz.ui.workorder;

import java.util.List;

import combiz.domain.failure.Failurecause;
import combiz.domain.failure.Failurecode;
import combiz.domain.failure.Failureproblem;
import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofailproblem;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2:17:09 PM  Dec 17, 2008 
 * 功能：如果输入的原因代码在故障代码的原因代码中存在，赋值描述信息，并设置为只读，否则设置为必填。
 * 捆绑表：WOFAILPROBLEM
 * 捆绑字段：FAILURECODE
 */
public class FldFailurecause extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根

		Wofailcause wofailcau = (Wofailcause) this.getMainObject();
		String failurecause = (String) this.getValueByColname("failurecause");
		ITextbox arg1 = (ITextbox) this.getFellow("wofailcause.description");
		if (Util.isNotNull(failurecause)) 
		{
			List failurecaublist = this.getMainSrv().getBaseDao().findWithQuery(Failurecause.class, "failurecause ='"+failurecause+"' and failureproblem = '"+wofailcau.getFailureproblem()+"'");
			if(failurecaublist.size()>0)
			{
				Failurecause failcaus = (Failurecause) failurecaublist.get(0);
				String desc = failcaus.getDescription();
				this.setValueByColname("description", desc);
				this.setReadonly(arg1);
				this.setNoRequired(arg1);
			}
			else
			{
				this.setNoReadonly(arg1);
				this.setRequired(arg1);
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
