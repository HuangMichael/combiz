package combiz.ui.pm;

import combiz.domain.pm.Pmgenhistory;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;

public class PmOldList extends ListWindow {
	public PmOldList() {
		super();
	}

	public boolean addNew() throws Exception {
		Premaint parent = (Premaint) ownerWnd.getMainObject();

		Pmgenhistory newobj = new Pmgenhistory();
		newobj.setPmnum(parent.getPmnum());

		this.mainObject = newobj;
		return true;
	}
}
