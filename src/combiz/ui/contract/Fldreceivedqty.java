package combiz.ui.contract;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Doublebox;

import combiz.domain.contract.Contline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

public class Fldreceivedqty extends FieldAdapter {

	public void action(Component component) throws Exception {
		Contline contline = (Contline) this.mainObject;
		Doublebox dx = (Doublebox) component;
		Double receivedtotalcost = contline.getReceivedunitcost()
				* dx.getValue();
		Double rejectedqty=contline.getOrderqty()-contline.getReceivedqty();
		contline.setReceivedtotalcost(receivedtotalcost);
		contline.setRejectedqty(rejectedqty);
		RecWindow recWnd = (RecWindow) component.getFellow("mainWnd");
		recWnd.bandData();
	}

	public void init(Component component) throws Exception {

	}

	public void validate(Component component) throws Exception {

	}

}
