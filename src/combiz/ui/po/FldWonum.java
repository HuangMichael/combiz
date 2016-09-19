package combiz.ui.po;


import combiz.domain.po.Poline;
import combiz.domain.workorder.Workorder;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldWonum extends FieldAdapter
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
	 * @TODO �ֶ�������ƿ�����ø÷������������������ǽ���Ӧ�̵���ϵ�˸�ֵ����һ���ı���
	 * @param component
	 * @throws Exception
	 * @����� 2007-8-7 ����03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{
		//�õ���������
		Poline pl = (Poline)this.mainObject;
		//�õ��ؼ�
		Textbox textbox = (Textbox)component;
		
		//ͨ���ؼ���valueֵ����ѯ���
		if (textbox.getValue()!=null && textbox.getValue()!=""){
		List Wolist = this.mainSrv.getBaseDao().findWithQuery(Workorder.class, "wonum='"+textbox.getValue()+"'");
		Workorder wo = (Workorder)Wolist.get(0);
		
		
		
		
		//�������һ������ֵ�󶨸����������һ������
		//pl.setTasknum("");
		
		//ֵ�󶨣�������󶨣���֮ǰ�ĸ�ֵ���׷ѹ���
		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
		recWnd.bandData();
		}
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
