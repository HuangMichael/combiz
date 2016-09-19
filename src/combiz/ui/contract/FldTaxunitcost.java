package combiz.ui.contract;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

import combiz.domain.contract.Contline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IDoublebox;

public class FldTaxunitcost extends FieldAdapter {

	public void action(Component component) throws Exception {
		//Double unitcost = (Double) this.getValueByColname("unitcost");//不含税单价
		Double orderqty = (Double) this.getValueByColname("orderqty");//含税单价
		IDoublebox order = (IDoublebox) component;
		Double taxunitcost = 0D;
		if (order.getValue() != null) {
			taxunitcost = order.getValue();
		}
		if (orderqty == null) {
			orderqty = 0D;
		}
		if(taxunitcost <=0) {
			Messagebox.show("含税单价不能小于零，请核实！");
			return;
		}
		//this.setValueByColname("linecost", orderqty * unitcost);
		this.setValueByColname("taxlinecost", orderqty * taxunitcost);
		
	}

	public void init(Component component) throws Exception {

	}

	public void validate(Component component) throws Exception {

	}

}
