package combiz.ui.rfq;


import combiz.domain.company.Companies;
import combiz.domain.company.Compcontact;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldVendor extends FieldAdapter
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
	 * ��ͨ����Ӧ�̺͹�Ӧ����ϵ�˵õ���Ӧ����ϵ�˵���ϵ��ʽ
	 * @param component
	 * @throws Exception
	 * @����� 2007-8-14 ����03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{

		//�õ���������
		Rfqvendor rv = (Rfqvendor)this.mainObject;
		//�õ��ؼ�
		Textbox textbox = (Textbox)component;
		String comp = textbox.getValue();
		if (Util.isNotNull(comp)) {
			List Vendorlist = this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+comp+"'");
			Companies cmp = (Companies)Vendorlist.get(0);
			this.setValueByColname("contact", cmp.getContact());//��ϵ������
			this.setValueByColname("freightterms", cmp.getFreightterms());//��������
			this.setValueByColname("shipvia", cmp.getShipvia());//���䷽ʽ 
			this.setValueByColname("paymentterms", cmp.getPaymentterms());//֧������
			//ͨ����ϵ�˻�ȡ��ϵ����Ϣ
			List Contlist = this.mainSrv.getBaseDao().findWithQuery(Compcontact.class, "contact='"+cmp.getContact()+"' and company='"+comp+"'");
			if(Contlist.size()>0){
				Compcontact compt = (Compcontact)Contlist.get(0);
				this.setValueByColname("phone", compt.getMobilephone());//��ϵ�˵绰
				this.setValueByColname("email", compt.getEmail());//��ϵ�˵����ʼ�
				this.setValueByColname("faxphone", compt.getFaxphone());//��ϵ�˴���
				
			}
			
		}
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
