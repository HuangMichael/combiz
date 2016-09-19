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
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{

	}

	/**
	 * 
	 * @TODO 字段上鼠标移开后调用该方法。本方法的作用是将供应商的联系人赋值给另一个文本框
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{
		//得到主窗体类
		Pr pr = (Pr)this.mainObject;
		//得到控件
		Textbox textbox = (Textbox)component;
		String company = textbox.getValue();
		//通过控件的value值来查询结果
		List companies = this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+textbox.getValue()+"'");
		//如果结果集的大小>=1
		if(companies.size()>=1){
			Companies comp = (Companies)companies.get(0);
			//将结果的一个属性值绑定给主窗体类的一个属性
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
