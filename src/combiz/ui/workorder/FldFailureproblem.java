package combiz.ui.workorder;

import java.util.List;

import combiz.domain.failure.Failurecode;
import combiz.domain.failure.Failureproblem;
import combiz.domain.workorder.Wofailproblem;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:17:09 PM  Dec 17, 2008 
 * ���ܣ��������Ĺ�����������ڹ�����������д��ڣ���ֵ������Ϣ��������Ϊֻ������������Ϊ���
 * �����WOFAILPROBLEM
 * �����ֶΣ�FAILURECODE
 */
public class FldFailureproblem extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������

		Wofailproblem wofailpro = (Wofailproblem) this.getMainObject();
		String failureproblem = (String) this.getValueByColname("failureproblem");
		ITextbox arg1 = (ITextbox) this.getFellow("wofailproblem.description");
		if (Util.isNotNull(failureproblem)) 
		{
			List failureproblist = this.getMainSrv().getBaseDao().findWithQuery(Failureproblem.class, "failurecode ='"+wofailpro.getFailurecode()+"' and failureproblem = '"+failureproblem+"'");
			if(failureproblist.size()>0)
			{
				Failureproblem failpro = (Failureproblem) failureproblist.get(0);
				String desc = failpro.getDescription();
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
		// TODO �Զ����ɷ������
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
