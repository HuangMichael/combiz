package combiz.ui.pr;


import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldPonum extends FieldAdapter
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
		Prline pl = (Prline)this.mainObject;
		//�õ��ؼ�
		Textbox textbox = (Textbox)component;
		
		List polinelist = this.mainSrv.getBaseDao().findWithQuery(Poline.class, "ponum='"+textbox.getValue()+"'");
		
		if(polinelist.size()>0){
			Poline pol = (Poline)polinelist.get(0);
			pl.setPolinenum(Long.parseLong(pol.getPonum()));
		}
		//ֵ�󶨣�������󶨣���֮ǰ�ĸ�ֵ���׷ѹ���
		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
		recWnd.bandData();
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
