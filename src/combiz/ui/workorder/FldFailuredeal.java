package combiz.ui.workorder;

import java.util.List;

import combiz.domain.failure.Failurecause;
import combiz.domain.failure.Failurecode;
import combiz.domain.failure.Failuredeal;
import combiz.domain.failure.Failureproblem;
import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofaildeal;
import combiz.domain.workorder.Wofailproblem;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2:17:09 PM  Dec 17, 2008 
 * 功能：如果输入的措施代码在措施代码中存在，赋值描述信息，并设置为只读，否则设置为必填。
 * 捆绑表：WOFAILPROBLEM
 * 捆绑字段：FAILURECODE
 */
public class FldFailuredeal extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根

		Wofaildeal wofaildeal = (Wofaildeal) this.getMainObject();
		String failuredeal = (String) this.getValueByColname("failuredeal");
		ITextbox arg1 = (ITextbox) this.getFellow("wofaildeal.description");
		if (Util.isNotNull(failuredeal)) 
		{
			List failuredeallist = this.getMainSrv().getBaseDao().findWithQuery(Failuredeal.class, "failurecause ='"+wofaildeal.getFailurecause()+"' and failuredeal = '"+failuredeal+"'");
			if(failuredeallist.size()>0)
			{
				Failuredeal faildeal = (Failuredeal) failuredeallist.get(0);
				String desc = faildeal.getDescription();
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
