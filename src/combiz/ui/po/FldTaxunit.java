package combiz.ui.po;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class FldTaxunit extends FieldAdapter {

	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) {

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
		Double orderqty = (Double) this.getValueByColname("orderqty");//取出界面上的订购数量字段orderqty;
		Double taxunitcost = (Double) this.getValueByColname("taxunitcost");//取出界面上的单价字段unitcost;
		Double num = 0d;
		if(taxunitcost == null || taxunitcost<=0) {
			Messagebox.show("订购含税单价不能为空且不能小于零，请核实！");
			this.setValueByColname("unitcost", 0D);
			return;
		}
		if(orderqty!=null && taxunitcost!=null)
		{
			num = orderqty * taxunitcost;  //算当前新增加的采购申请行的行成本；
			this.setValueByColname("taxlinecost", num);//将合计赋值到数据库中PR.totalcost。
		}
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void action(Component component) throws Exception {
	    
	}

}
