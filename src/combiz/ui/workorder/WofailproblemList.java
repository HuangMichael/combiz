package combiz.ui.workorder;

import combiz.domain.workorder.Wofailcode;
import combiz.domain.workorder.Wofailproblem;
import combiz.system.ui.ListWindow;

/**
 * ���ߣ�ljh
 * ���ܣ���������
 */
public class WofailproblemList extends ListWindow {

	public boolean addNew() throws Exception {
		Wofailcode parent = (Wofailcode)this.getOwnerWnd().getMainObject();

		Wofailproblem newobj = new Wofailproblem();
		newobj.setWonum(parent.getWonum());
		newobj.setFailurecode(parent.getFailurecode());

		this.mainObject = newobj;
		return true;
	}
}
