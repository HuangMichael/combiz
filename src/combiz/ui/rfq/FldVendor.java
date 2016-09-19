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
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}

	/**
	 * 
	 * @TODO 字段上鼠标移开后调用该方法。本方法的作用是将供应商的联系人赋值给另一个文本框
	 * 并通过供应商和供应商联系人得到供应商联系人的联系方式
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-14 下午03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{

		//得到主窗体类
		Rfqvendor rv = (Rfqvendor)this.mainObject;
		//得到控件
		Textbox textbox = (Textbox)component;
		String comp = textbox.getValue();
		if (Util.isNotNull(comp)) {
			List Vendorlist = this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+comp+"'");
			Companies cmp = (Companies)Vendorlist.get(0);
			this.setValueByColname("contact", cmp.getContact());//联系人姓名
			this.setValueByColname("freightterms", cmp.getFreightterms());//运输条款
			this.setValueByColname("shipvia", cmp.getShipvia());//运输方式 
			this.setValueByColname("paymentterms", cmp.getPaymentterms());//支付条款
			//通过联系人获取联系人信息
			List Contlist = this.mainSrv.getBaseDao().findWithQuery(Compcontact.class, "contact='"+cmp.getContact()+"' and company='"+comp+"'");
			if(Contlist.size()>0){
				Compcontact compt = (Compcontact)Contlist.get(0);
				this.setValueByColname("phone", compt.getMobilephone());//联系人电话
				this.setValueByColname("email", compt.getEmail());//联系人电子邮件
				this.setValueByColname("faxphone", compt.getFaxphone());//联系人传真
				
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
