package combiz.ui.workorder;

import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofaildeal;
import combiz.system.ui.ListWindow;

/**
 * ���ߣ�ljh
 * ���ܣ����ϴ�ʩ
 */
public class WofaildealList extends ListWindow {
	public WofaildealList() {
		super();
	}

	public boolean addNew() throws Exception {

		ListWindow listwin = (ListWindow) this.getParent().getFellow(
				"wofailproblem");
		Wofailcause parent = (Wofailcause) listwin.getSelectObject();
		Wofaildeal newobj = new Wofaildeal();
		newobj.setWonum(parent.getWonum());
		newobj.setFailurecause(parent.getFailurecause());

		this.mainObject = newobj;
		return true;
	}
}
