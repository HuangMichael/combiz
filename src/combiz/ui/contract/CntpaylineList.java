package combiz.ui.contract;

import combiz.domain.contract.Contpay;
import combiz.domain.contract.Contract;
import combiz.system.ui.ListWindow;

public class CntpaylineList extends ListWindow {
	public CntpaylineList() {
		super();
	}

	public boolean addNew() throws Exception {
		ContractWindow cntwin = (ContractWindow) ownerWnd;
		Contract cnt = (Contract) cntwin.getMainObject();
		Contpay cntpay = new Contpay();
		cntpay.setCntnum(cnt.getCntnum());
		int ContInt = this.mainSrv.getRowCountByWhere(cntpay, "cntnum='"+cnt.getCntnum()+"'");
		cntpay.setPayline((long)ContInt +1);
		this.mainObject = cntpay;
		return true;
	}
}
