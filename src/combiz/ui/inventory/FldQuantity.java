package combiz.ui.inventory;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldQuantity extends FieldAdapter
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
		Double itemqty = (Double) this.getValueByColname("itemqty");
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if(itemqty!=null && unitcost!=null)
		{
			Component target = component.getFellow("wpmaterial.linecost");
			this.setValueByColname("linecost", itemqty * unitcost);
		}
	}
	
	

}
