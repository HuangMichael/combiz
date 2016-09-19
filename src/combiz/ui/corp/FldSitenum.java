package combiz.ui.corp;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.Executions;

public class FldSitenum extends FieldAdapter {

	@Override
	public void action(Component component)
	throws Exception 
	{

	}

	@Override
	public void init(Component component) 
	throws Exception 
	{
	
	}

	@Override
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	@Override
	public String getListWhere(Component ibandbox)
	{
		//���ӵص���ȨSQL
		String allsite = (String) Executions.getCurrent().getDesktop().getSession().getAttribute("allsite");
		//��Ȩ���еص�
		if(allsite.equals("true"))
		{
			return null;
		}
		else
		{
			//����ص��ֶβ��Ǳ�����ֶΣ���ô���Բ鿴���ص��ֶ�Ϊ�յ���������
			//return "sitenum in(" + allsite + ")";
			return "sitenum in(select t.deptnum from Deptance t where t.deptance='"+allsite+"')";
		}

	}
	
}
