package combiz.ui.contract;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

import combiz.domain.contract.Contline;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

public class Flditemnum extends FieldAdapter {

	public void action(Component component) throws Exception {
		Contline contline = (Contline) this.mainObject;
		Textbox tx = (Textbox) component;
		List list = this.mainSrv.getBaseDao().findWithQuery(Item.class,
				"itemnum='" + tx.getValue() + "'");
		if (list.size() >= 0) {
			Item item = (Item) list.get(0);
			contline.setDescription(item.getDescription());
			contline.setOrderunit(item.getOrderunit());
			contline.setTaxcode(item.getTaxcode());
			List list1 = this.mainSrv.getBaseDao().findWithQuery(
					Inventory.class, "itemnum='" + item.getItemnum() + "'");
			if (list1.size() > 0) {
				Inventory inven = (Inventory) list1.get(0);
				contline.setUnitcost(inven.getAvgcost());
				//contline.setTaxunitcost(inven.getStdcost());
				contline.setReceivedunitcost(inven.getLastcost());
				
			}
			
		}
		RecWindow recWnd = (RecWindow) component.getFellow("mainWnd");
		recWnd.bandData();
	}

	public void init(Component component) throws Exception {

	}

	public void validate(Component component) throws Exception {

	}

}
