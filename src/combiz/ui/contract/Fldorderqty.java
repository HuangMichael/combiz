package combiz.ui.contract;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

import combiz.domain.contract.Contline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IDoublebox;

public class Fldorderqty extends FieldAdapter {

	public void action(Component component) throws Exception {
		Double unitcost = (Double) this.getValueByColname("unitcost");//����˰����
		Double taxunitcost = (Double) this.getValueByColname("taxunitcost");//��˰����
		IDoublebox order = (IDoublebox) component;
		Double orderqty = 0D;
		if (order.getValue() != null) {
			orderqty = order.getValue();
		}
		if (unitcost == null) {
			unitcost = 0D;
		}
		if (taxunitcost == null) {
			taxunitcost = 0D;
		}
		if(orderqty <=0) {
			Messagebox.show("������������С���㣬���ʵ��");
			return;
		}
		this.setValueByColname("linecost", orderqty * unitcost);
		this.setValueByColname("taxlinecost", orderqty * taxunitcost);
		
	}

	public void init(Component component) throws Exception {

	}

	public void validate(Component component) throws Exception {

	}

}
