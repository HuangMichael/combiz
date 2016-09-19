package combiz.ui.po;


import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;

public class FldUnitcost extends FieldAdapter
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
	 * @TODO 当焦点离开单价文本框时，系统自动计算单价与数量的积。并赋值给另一个文本域
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-8 上午11:06:49
	 */
	public void validate(Component component) 
	throws Exception 
	{	
		double unitcost=0.0,orderqty=0.0,taxunitcost=0.0;
		//得到主窗体类
		Poline pl = (Poline)this.mainObject;
		//不含税单价
		Doublebox dbox = (Doublebox)component;
		if(dbox.getValue()==null){
			unitcost=0;
			//throw new WrongValueException(dbox1,"<font color='red'>请输入单价</font>");
		}else{
			unitcost = dbox.getValue();
		}
		
		
		//含税单价
		Doublebox dbox1 = (Doublebox)dbox.getFellow("poline.taxunitcost");
		if(dbox1.getValue()==null){
			taxunitcost = 0;
		}else{taxunitcost=dbox1.getValue();}
		
		//数量
		Doublebox dbox2 = (Doublebox)dbox.getFellow("poline.orderqty");
		if(dbox2.getValue()==null){
			orderqty=0;
			//throw new WrongValueException(dbox,"<font color='red'>请输入订购数量</font>");
		}else{
		orderqty = dbox2.getValue();
		}
		
		
		//不含税总价
		
		double num = unitcost * orderqty;
		pl.setLinecost(num);
		
		//含税总价
		double taxnum = orderqty * taxunitcost;
		pl.setTaxlinecost(taxnum);
	
		//值绑定，如果不绑定，则之前的赋值将白费工夫
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
