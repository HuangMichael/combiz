package combiz.ui.failure;

import combiz.domain.failure.Failurecause;
import combiz.domain.failure.Failuredeal;
import combiz.system.ui.ListWindow;

public class FailuredealList extends ListWindow {
	public FailuredealList() {
		super();
	}

	public boolean addNew() throws Exception {

		ListWindow listwin = (ListWindow) this.getParent().getFellow(
				"failproblem");
		Failurecause parent = (Failurecause) listwin.getSelectObject();
		Failuredeal faildeal = new Failuredeal();
		faildeal.setFailurecause(parent.getFailurecause());

		this.mainObject = faildeal;
		return true;
	}
}
