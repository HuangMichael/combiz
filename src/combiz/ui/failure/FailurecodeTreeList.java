package combiz.ui.failure;

import combiz.domain.classattr.Classification;
import combiz.domain.failure.Failurecode;
import combiz.system.ui.ListWindow;

public class FailurecodeTreeList extends ListWindow {
	public FailurecodeTreeList() {
		super();
	}

	public boolean addNew() throws Exception {
		FailurecodeWindow parentWnd = (FailurecodeWindow) ownerWnd;
		Classification parent = (Classification) parentWnd.getMainObject();

		Failurecode newobj = new Failurecode();
		newobj.setClassid(parent.getClassid());

		this.mainObject = newobj;
		return true;
	}
}
