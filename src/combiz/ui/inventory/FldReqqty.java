package combiz.ui.inventory;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldReqqty extends FieldAdapter {

	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。 如果
	 */
	public void init(Component component) {

	}
	/* 
	 * 功能：
	 * 作者：李建红
	 * 日期：Oct 8, 200810:24:32 AM
	 */
	public void action(Component com) throws Exception {
		// 得到主窗体类
		Double quantity = (Double) this.getValueByColname("quantity");
		if (quantity < 0 ) {
			this.setValueByColname("quantity", 0D);
			throw new Exception ("交易数量不能小于0 ！");
		}
		if (quantity == null ) {
			this.setValueByColname("quantity", 0D);
		}
		Double reqqty = (Double) this.getValueByColname("reqqty");
		if(reqqty==null)
		{
			reqqty = 0d;
		}
		if (reqqty < 0 ) {
			this.setValueByColname("reqqty", 0D);
			throw new Exception ("申请数量不能小于0 ！");
		}
		if (reqqty != null ) {
			if ((reqqty-quantity)<0) {
				throw new Exception("交易数量不能大于申请数量，请核实!");
			}
		}
		
		

	}

	/*
	 * 功能： 作者：李建红 日期：Sep 25, 20086:22:54 PM
	 */
	public void validate(Component component) {

	}

}
