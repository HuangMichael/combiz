package combiz.ui.contract;

import combiz.domain.contract.Contract;
import combiz.domain.contract.Contterms;
import combiz.system.ui.ListWindow;

public class CnttermslineList extends ListWindow {
	public CnttermslineList() {
		super();
	}

	public boolean addNew() throws Exception {
		ContractWindow contwin = (ContractWindow) ownerWnd;
		Contract cont = (Contract) contwin.getMainObject();
		Contterms cntterm = new Contterms();
		String termnum=this.genAutokey("termnum");
		cntterm.setTermnum(termnum);
		cntterm.setCntnum(cont.getCntnum());
		this.mainObject = cntterm;
		return true;
	}
}
