package combiz.ui.po;


import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;

public class Fldrcqty extends FieldAdapter
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
//		double orderqty=0.0,rcqty=0.0,receivedunitcost=0.0;
//		//�õ���������
//		Poline pl = (Poline)this.mainObject;
//		//���յ���
//		Doublebox dbox = (Doublebox)component;
//		if(dbox.getValue()==null){
//			receivedunitcost=0;
//		}
//		else{receivedunitcost=dbox.getValue();}
//		
////		//������
////		Doublebox dbox1 = (Doublebox)dbox.getFellow("poline.orderqty");
//		if(dbox1.getValue()==null){
//			orderqty=0;
//		}else{
//			orderqty = dbox1.getValue();
//		}
//		//��������
//		Doublebox dbox2 = (Doublebox)dbox.getFellow("poline.receivedqty");
//		if(dbox2.getValue()==null){
//			rcqty=0;
//		}else{
//			rcqty = dbox2.getValue();
//		}
//		//�����ܼ�
//		double rcnum = receivedunitcost * rcqty;
//		pl.setReceivedtotalcost(rcnum);
//		
//		//��������
//		double norcnum = orderqty - rcqty;
//		pl.setRejectedqty(norcnum);
//		
//		//ֵ�󶨣�������󶨣���֮ǰ�ĸ�ֵ���׷ѹ���
//		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
//		recWnd.bandData();
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
