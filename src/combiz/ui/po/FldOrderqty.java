package combiz.ui.po;


import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;

public class FldOrderqty extends FieldAdapter
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
		double unitcost=0.0,orderqty=0.0,taxunitcost=0.0,receivedqty=0.0;
		//得到主窗体类
		Poline pl = (Poline)this.mainObject;
		//数量
		Doublebox dbox = (Doublebox)component;
		if(dbox.getValue()==null){
			orderqty=0;
			//throw new WrongValueException(dbox,"<font color='red'>请输入订购数量</font>");
		}else{
		orderqty = dbox.getValue();
		}
		//不含税单价
		Doublebox dbox1 = (Doublebox)dbox.getFellow("poline.unitcost");
		
		if(dbox1.getValue()==null){
			unitcost=0;
			//throw new WrongValueException(dbox1,"<font color='red'>请输入单价</font>");
		}else{
			unitcost = dbox1.getValue();
		}
		//含税单价
		Doublebox dbox2 = (Doublebox)dbox.getFellow("poline.taxunitcost");
		if(dbox2.getValue()==null){
			taxunitcost = 0;
		}else{taxunitcost=dbox2.getValue();}
		//不含税总价
		double num = unitcost * orderqty;
		pl.setLinecost(num);
		
		//含税总价
		double taxnum = orderqty * taxunitcost;
		pl.setTaxlinecost(taxnum);
		
		//接收数量RECEIVEDQTY
		Doublebox dbox3 = (Doublebox)dbox.getFellow("poline.receivedqty");
		if(dbox3.getValue()==null){
			receivedqty = 0.0;
		}else{receivedqty=dbox3.getValue();}
		//拒收数量
		double rejectedqty = orderqty - receivedqty;
 
		pl.setRejectedqty(rejectedqty);
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
