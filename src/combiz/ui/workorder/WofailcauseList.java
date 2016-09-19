package combiz.ui.workorder;

import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofailproblem;
import combiz.system.ui.ListWindow;

/**
 * ���ߣ�ljh
 * ���ܣ�ԭ�����
 */
public class WofailcauseList extends ListWindow {

	public boolean addNew() throws Exception {

		ListWindow listwin = (ListWindow) this.getParent()
				.getFellow("wofailcode");
		Wofailproblem parent = (Wofailproblem) listwin.getSelectObject();
		Wofailcause newobj = new Wofailcause();
		newobj.setWonum(parent.getWonum());
		newobj.setFailureproblem(parent.getFailureproblem());

		this.mainObject = newobj;
		return true;
	}
}
