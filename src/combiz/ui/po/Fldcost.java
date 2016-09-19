package combiz.ui.po;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;


import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * @version ����ʱ�䣺2008-1-12����04:50:42
 * ��˵��
 */
public class Fldcost extends FieldAdapter {
	
	
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
		Double orderqty = (Double) this.getValueByColname("orderqty");//ȡ�������ϵĶ��������ֶ�orderqty;
		Double unitcost = (Double) this.getValueByColname("unitcost");//ȡ�������ϵĵ����ֶ�unitcost;
		Double num = 0d;
		
		if(unitcost == null || unitcost<=0) {
			this.throwException(component, "������˰���۲���Ϊ���Ҳ���С���㣬���ʵ��");
			this.setValueByColname("unitcost", 0D);
			return;
		}
		if(orderqty!=null && unitcost!=null)
		{
			num = orderqty * unitcost;  //�㵱ǰ�����ӵĲɹ������е��гɱ���
			this.setValueByColname("linecost", num);//���ϼƸ�ֵ�����ݿ���PR.totalcost��
			//this.setValueByColname("loadedcost", num);
		}
		
	}
	
	

}
