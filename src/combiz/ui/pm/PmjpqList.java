package combiz.ui.pm;

import combiz.domain.pm.Pmjpseq;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;

public class PmjpqList extends ListWindow {
	public PmjpqList() {
		super();
	}

	public boolean addNew() throws Exception {
		Premaint parent = (Premaint) ownerWnd.getMainObject();

		Pmjpseq newobj = new Pmjpseq();
		newobj.setPmnum(parent.getPmnum());

		this.mainObject = newobj;
		return true;
	}
}
