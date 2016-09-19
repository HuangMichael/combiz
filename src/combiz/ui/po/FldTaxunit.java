package combiz.ui.po;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class FldTaxunit extends FieldAdapter {

	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) {

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
		Double taxunitcost = (Double) this.getValueByColname("taxunitcost");//ȡ�������ϵĵ����ֶ�unitcost;
		Double num = 0d;
		if(taxunitcost == null || taxunitcost<=0) {
			Messagebox.show("������˰���۲���Ϊ���Ҳ���С���㣬���ʵ��");
			this.setValueByColname("unitcost", 0D);
			return;
		}
		if(orderqty!=null && taxunitcost!=null)
		{
			num = orderqty * taxunitcost;  //�㵱ǰ�����ӵĲɹ������е��гɱ���
			this.setValueByColname("taxlinecost", num);//���ϼƸ�ֵ�����ݿ���PR.totalcost��
		}
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void action(Component component) throws Exception {
	    
	}

}
