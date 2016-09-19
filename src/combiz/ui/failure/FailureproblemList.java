package combiz.ui.failure;

import combiz.domain.failure.Failurecode;
import combiz.domain.failure.Failureproblem;
import combiz.system.ui.ListWindow;

public class FailureproblemList extends ListWindow {
	public FailureproblemList() {
		super();
	}

	public boolean addNew() throws Exception {
		Failurecode parent = (Failurecode) ownerWnd.getMainObject();

		Failureproblem failproblem = new Failureproblem();
		failproblem.setFailurecode(parent.getFailurecode());

		this.mainObject = failproblem;
		return true;
	}
}
