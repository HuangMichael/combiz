package combiz.ui.po;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;


import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * @version 创建时间：2008-1-12下午04:50:42
 * 类说明
 */
public class Fldcost extends FieldAdapter {
	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{

	}

	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 计算行总价
	 */
	public void action(Component component)
	throws Exception 
	{    
		Double orderqty = (Double) this.getValueByColname("orderqty");//取出界面上的订购数量字段orderqty;
		Double unitcost = (Double) this.getValueByColname("unitcost");//取出界面上的单价字段unitcost;
		Double num = 0d;
		
		if(unitcost == null || unitcost<=0) {
			this.throwException(component, "订购含税单价不能为空且不能小于零，请核实！");
			this.setValueByColname("unitcost", 0D);
			return;
		}
		if(orderqty!=null && unitcost!=null)
		{
			num = orderqty * unitcost;  //算当前新增加的采购申请行的行成本；
			this.setValueByColname("linecost", num);//将合计赋值到数据库中PR.totalcost。
			//this.setValueByColname("loadedcost", num);
		}
		
	}
	
	

}
