package combiz.ui.po;


import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class FldDqty extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) 
	{
		
	}

	/**
	 * 
	 * @TODO �������뿪�����ı���ʱ��ϵͳ�Զ����㵥���������Ļ�������ֵ����һ���ı���
	 * @param component
	 * @throws Exception
	 * @����� 2007-8-8 ����11:06:49
	 */
	public void validate(Component component) 
	throws Exception 
	{	
		Double orderqty = (Double) this.getValueByColname("orderqty");//ȡ�������ϵĶ��������ֶ�orderqty;
		Double unitcost = (Double) this.getValueByColname("unitcost");//ȡ�������ϵĵ����ֶ�unitcost;
		Double taxunitcost = (Double) this.getValueByColname("taxunitcost");//ȡ�������ϵĵ����ֶ�unitcost;
		Double num = 0d;
		if(orderqty == null || orderqty<=0) {
			Messagebox.show("������������Ϊ���Ҳ���С���㣬���ʵ��");
			this.setValueByColname("orderqty", 0D);
			return;
		}
		if(orderqty!=null && unitcost!=null)
		{
			num = orderqty * unitcost;  //�㵱ǰ�����ӵĲɹ������е��гɱ���
			this.setValueByColname("linecost", num);//���ϼƸ�ֵ�����ݿ���PR.totalcost��
		}
		if(orderqty!=null && taxunitcost!=null)
		{
			num = orderqty * unitcost;  //�㵱ǰ�����ӵĲɹ������е��гɱ���
			this.setValueByColname("taxlinecost", num);//���ϼƸ�ֵ�����ݿ���PR.totalcost��
		}
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
