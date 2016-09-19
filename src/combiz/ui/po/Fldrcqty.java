package combiz.ui.po;


import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;

public class Fldrcqty extends FieldAdapter
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
//		double orderqty=0.0,rcqty=0.0,receivedunitcost=0.0;
//		//得到主窗体类
//		Poline pl = (Poline)this.mainObject;
//		//接收单价
//		Doublebox dbox = (Doublebox)component;
//		if(dbox.getValue()==null){
//			receivedunitcost=0;
//		}
//		else{receivedunitcost=dbox.getValue();}
//		
////		//总数量
////		Doublebox dbox1 = (Doublebox)dbox.getFellow("poline.orderqty");
//		if(dbox1.getValue()==null){
//			orderqty=0;
//		}else{
//			orderqty = dbox1.getValue();
//		}
//		//接收数量
//		Doublebox dbox2 = (Doublebox)dbox.getFellow("poline.receivedqty");
//		if(dbox2.getValue()==null){
//			rcqty=0;
//		}else{
//			rcqty = dbox2.getValue();
//		}
//		//接收总价
//		double rcnum = receivedunitcost * rcqty;
//		pl.setReceivedtotalcost(rcnum);
//		
//		//拒收数量
//		double norcnum = orderqty - rcqty;
//		pl.setRejectedqty(norcnum);
//		
//		//值绑定，如果不绑定，则之前的赋值将白费工夫
//		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
//		recWnd.bandData();
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
