package combiz.ui.inventory;


import combiz.system.FieldAdapter;
import combiz.system.util.Util;
import com.inbasis.zk.ui.Component;
/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 10:40:36 AM  Nov 7, 2008 
 * ���ܣ��ڶԲɹ������еĶ�����λ����ά��ʱ��������ŵ�λΪ�յĻ����ѷ��ŵ�λ��Ϊ������λ��
 * �����item
 * �����ֶΣ�orderunit
 */
public class FldItemunit extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) 
	{
		
	}

	public void validate(Component component) 
	throws Exception 
	{
		
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		String orderunit = (String) this.getValueByColname("orderunit");
		String issueunit = (String) this.getValueByColname("issueunit");
		if(Util.isNotNull(orderunit)&& Util.isNull(issueunit))
		{
			this.setValueByColname("issueunit", orderunit);
			
		}
		
	}

}
