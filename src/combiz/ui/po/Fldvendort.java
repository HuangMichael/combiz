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
		///得到主窗体类
		Po po = (Po)this.mainObject;
		//得到控件
		String vendor = (String) this.getValueByColname("vendor");
		//通过控件的value值来查询结果
		List comlist = this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+vendor+"'");
		//如果结果集的大小>=1
		if(comlist.size()>=1)
		{
			Companies comp = (Companies)comlist.get(0);
			//将结果的一个属性值绑定给主窗体类的一个属性
			this.setValueByColname("contact", comp.getContact());

		}
		else
		{
			po.setContact("");
		}
		//值绑定，如果不绑定，则之前的赋值将白费工夫
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
