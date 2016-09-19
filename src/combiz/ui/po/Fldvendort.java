package combiz.ui.po;


import combiz.domain.company.Companies;
import combiz.domain.po.Po;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IBandbox;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class Fldvendort extends FieldAdapter
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
		///�õ���������
		Po po = (Po)this.mainObject;
		//�õ��ؼ�
		String vendor = (String) this.getValueByColname("vendor");
		//ͨ���ؼ���valueֵ����ѯ���
		List comlist = this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+vendor+"'");
		//���������Ĵ�С>=1
		if(comlist.size()>=1)
		{
			Companies comp = (Companies)comlist.get(0);
			//�������һ������ֵ�󶨸����������һ������
			this.setValueByColname("contact", comp.getContact());

		}
		else
		{
			po.setContact("");
		}
		//ֵ�󶨣�������󶨣���֮ǰ�ĸ�ֵ���׷ѹ���
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
