package combiz.ui.tool;

import combiz.domain.classattr.Classification;
import combiz.domain.tool.Tool;
import combiz.system.ui.ListWindow;

public class ToolTreeList extends ListWindow {
	public ToolTreeList() {
		super();
	}

	public boolean addNew() throws Exception {
		Classification parent = (Classification) this.getOwnerWnd().getMainObject();

		Tool newobj = new Tool();
		newobj.setClassid(parent.getClassid());
		newobj.setOutside("·ñ");
		newobj.setToolrate(1.0d);
		newobj.setDisabled("ÊÇ");

		this.mainObject = newobj;
		return true;
	}
}
