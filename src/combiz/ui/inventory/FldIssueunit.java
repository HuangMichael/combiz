package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Inventory;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 11:15:07 AM  Oct 24, 2008 
 * ���ܣ����������������Ļ����Ͳ����޸ĸÿ��ķ��ŵ�λ��ͨ������ֻ���ֶ���ʵ�֣���
 * �����inventory
 * �����ֶΣ�issueunit
 */
public class FldIssueunit extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		Inventory inventory = (Inventory) this.getMainObject();
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Invstock t where t.itemnum ='"+itemnum+"' and warehouse = '"+warehouse+"' and curbal >0");
		if(count >0)
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
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
