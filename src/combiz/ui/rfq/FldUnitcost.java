package combiz.ui.rfq;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldUnitcost extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{

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
	 * �������ܼ�
	 */
	public void action(Component component)
	throws Exception 
	{
		Double orderqty = (Double) this.getValueByColname("ORDERQTY");
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if(orderqty!=null && unitcost!=null)
		{
			this.setValueByColname("linecost", orderqty * unitcost);
		}
	}
	
	

}
