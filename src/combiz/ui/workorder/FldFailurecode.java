package combiz.ui.workorder;

import java.util.List;

import combiz.domain.failure.Failurecode;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:17:09 PM  Dec 17, 2008 
 * ���ܣ��������Ĺ������ϴ����ڹ��ϴ����д��ڣ���ֵ������Ϣ��������Ϊֻ������������Ϊ���
 * �����wofailcode
 * �����ֶΣ�failurecode
 */
public class FldFailurecode extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������

		String failurecode = (String) this.getValueByColname("failurecode");
		ITextbox arg1 = (ITextbox) this.getFellow("wofailcode.description");
		IBandbox arg2 = (IBandbox) this.getFellow("wofailcode.classid");
		if (Util.isNotNull(failurecode)) 
		{
			List failurecodelist = this.getMainSrv().getBaseDao().findWithQuery(Failurecode.class, "failurecode ='"+failurecode+"'");
			if(failurecodelist.size()>0)
			{
				Failurecode failcode = (Failurecode) failurecodelist.get(0);
				String desc = failcode.getDescription();
				this.setValueByColname("description", desc);
				this.setValueByColname("classid", failcode.getClassid());
				this.setReadonly(arg1);
				this.setReadonly(arg2);
				this.setNoRequired(arg1);
				this.setNoRequired(arg2);
			}
			else
			{
				this.setNoReadonly(arg1);
				this.setNoReadonly(arg2);
				this.setRequired(arg1);
				this.setRequired(arg2);
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
