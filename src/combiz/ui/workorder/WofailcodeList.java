package combiz.ui.workorder;

import combiz.domain.workorder.Wofailcode;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.ListWindow;

/**
 * ���ߣ�ljh
 * ���ܣ����ϴ���
 */
public class WofailcodeList extends ListWindow {

	public boolean addNew() throws Exception {
		Workorder parent = (Workorder)this.getOwnerWnd().getMainObject();
		
		Wofailcode newobj = new Wofailcode();
		newobj.setWonum(parent.getWonum());

		this.mainObject = newobj;
		return true;
	}
}
