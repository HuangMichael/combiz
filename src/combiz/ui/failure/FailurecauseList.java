package combiz.ui.failure;

import combiz.domain.failure.Failurecause;
import combiz.domain.failure.Failureproblem;
import combiz.system.ui.ListWindow;

public class FailurecauseList extends ListWindow {
	public FailurecauseList() {
		super();
	}

	public boolean addNew() throws Exception {

		ListWindow listwin = (ListWindow) this.getParent()
				.getFellow("failcode");
		Failureproblem parent = (Failureproblem) listwin.getSelectObject();
		Failurecause failcause = new Failurecause();
		failcause.setFailureproblem(parent.getFailureproblem());

		this.mainObject = failcause;
		return true;
	}
}
