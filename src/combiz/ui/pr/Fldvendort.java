package combiz.ui.pr;


import combiz.business.company.CompaniesSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.company.Companies;
import combiz.domain.pr.Pr;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

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
		//�õ���������
		Pr pr = (Pr)this.mainObject;
		//�õ��ؼ�
		Textbox textbox = (Textbox)component;
		String company = textbox.getValue();
		//ͨ���ؼ���valueֵ����ѯ���
		List companies = this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+textbox.getValue()+"'");
		//���������Ĵ�С>=1
		if(companies.size()>=1){
			Companies comp = (Companies)companies.get(0);
			//�������һ������ֵ�󶨸����������һ������
			pr.setContact(comp.getContact());


		}else{
			this.setValueByColname("contact", "");
		}
	}

	/**
	 * 
	 */
	public void action(Component component)
	{

	}

}
