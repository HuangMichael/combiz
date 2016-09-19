package combiz.ui.contract;

import combiz.domain.contract.Contchange;
import combiz.domain.contract.Contract;
import combiz.system.ui.ListWindow;

public class CntchangelineList extends ListWindow {
	public CntchangelineList() {
		super();
	}

	public boolean addNew() throws Exception {
		ContractWindow contwin = (ContractWindow) ownerWnd;
		Contract cont = (Contract) contwin.getMainObject();
		Contchange contchange = new Contchange();
		contchange.setCntnum(cont.getCntnum());
		this.mainObject = contchange;
		return true;
	}
}
