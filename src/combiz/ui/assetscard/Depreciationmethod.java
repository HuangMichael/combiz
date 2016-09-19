package combiz.ui.assetscard;


import java.math.BigDecimal;
import java.text.DecimalFormat;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class Depreciationmethod extends FieldAdapter {

	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) {

	}

	/**
	 * 
	 * @TODO 当焦点离开单价文本框时，自动计算资产折旧相应的值
	 * @param component
	 * @throws Exception
	 * @王伟 2010-05-06   10:28
	 * 	 */
	public void validate(Component component) 
	throws Exception 
	{	
		Double cost = (Double) this.getValueByColname("cost");//取出界面上的原值字段cost;
		Long expectedmonth = (Long) this.getValueByColname("expectedmonth");//取出界面上的预计使用的月份字段expectedmonth;
		Long depreciationmonth = (Long) this.getValueByColname("depreciationmonth");//取出界面上的已计使用的月份字段depreciationmonth;
		
		Double residualvalues = (Double) this.getValueByColname("residualvalues");//取出界面上的残值率字段residualvalues;
		Double accdepreciation = (Double) this.getValueByColname("accdepreciation");//取出界面上的累计折旧率字段accdepreciation;
		Double impairment = (Double) this.getValueByColname("impairment");//取出界面上的减值准备字段impairment;

		Double num = 0d;
		Double sum = 0d;
	

		if(residualvalues == null || residualvalues<=0) {
			Messagebox.show("残值率不能为空，请输入！");
			this.setValueByColname("residualvalues", 0D);
			return;		
		}

		if((cost!=null || cost==null)&& (residualvalues!=null || accdepreciation==null))
		{
			if (residualvalues==null)
			{
				residualvalues=0d;
			}
			if (cost==null)
			{
				cost=0d;
			}
			num = cost * residualvalues;  //计算残值；
			this.setValueByColname("residual", num);//将残值赋给数据库表residual中。
		}




		if((cost!=null ||cost==null) && (accdepreciation!=null || accdepreciation==null))
		{
			if (accdepreciation==null)
			{
				accdepreciation=0d;
			}
			if (cost==null)
			{
				cost=0d;
			}
			num = cost - accdepreciation;  //算出当前净值；
			this.setValueByColname("networth", num);//将合计赋值到数据库净值字段。
		}

		if((cost!=null ||cost==null) && (accdepreciation!=null||accdepreciation==null) 
				&& (impairment!=null || impairment==null) 
			)
		{
			if (accdepreciation==null)
			{
				accdepreciation=0d;
			}
			if (impairment==null)
			{
				impairment=0d;
			}
			if (cost==null)
			{
				cost=0d;
			}
					
			num = cost - accdepreciation-impairment;  //算出当前净额；
			this.setValueByColname("net", num);//将合计赋值到数据库净额字段。
				}
		if((cost!=null ||cost==null) && (expectedmonth!=null || expectedmonth==null)
				&& (depreciationmonth!=null || depreciationmonth==null) && (residualvalues!=null || accdepreciation==null))
		{
			
			if (cost==null)
			{
				cost=0d;
			}
			if (depreciationmonth==null)
			{
				depreciationmonth=0l;
			}	
			if (expectedmonth==null)
			{
				expectedmonth=48l;
			}	
			
			if (residualvalues==null)
			{
				residualvalues=0d;
			}
			
			num = (1-residualvalues)/(expectedmonth - depreciationmonth);  //月折旧率；
         
			BigDecimal bg = new BigDecimal(num); //定义一个BG对象
	        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //bg对象取2位赋给f1，并定义为double型
			this.setValueByColname("mthval", f1);//将合计赋值到数据库月折旧率字段。
			sum=num*cost;
			this.setValueByColname("mthamount", sum);//将合计赋值到数据库月折旧率字段。
					
		}
		
		

	}




	/**
	 * @throws Exception 
	 * 
	 */
	public void action(Component component) throws Exception {

	}

}
