package combiz.ui.contract;

import combiz.domain.contract.Contline;
import combiz.domain.contract.Contract;
import combiz.system.ui.ListWindow;

public class ContlineList extends ListWindow {
	public ContlineList() {
		super();
	}

	public boolean addNew() throws Exception {
		ContractWindow contwin = (ContractWindow) ownerWnd;
		Contract cont = (Contract) contwin.getMainObject();
		Contline contline = new Contline();
		contline.setCntnum(cont.getCntnum());
		this.mainObject = contline;
		return true;
	}
}
