package combiz.ui.contract;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;

import combiz.domain.contract.Contline;
import combiz.system.FieldAdapter;

public class Fldtaxrate extends FieldAdapter {

	public void action(Component component) throws Exception {
		Contline contline = (Contline) this.mainObject;
		Doublebox dx = (Doublebox) component;
		Double taxunitcost = contline.getUnitcost() / (1 - dx.getValue());
		contline.setTaxunitcost(taxunitcost);
	}

	public void init(Component component) throws Exception {

	}

	public void validate(Component component) throws Exception {

	}

}
