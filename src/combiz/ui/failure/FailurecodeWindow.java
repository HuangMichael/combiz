package combiz.ui.failure;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

import combiz.domain.classattr.Classification;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

public class FailurecodeWindow extends TMainWindow implements InfWindow {
	public FailurecodeWindow() {
		super();
	}

	public boolean addNew() throws Exception {
		Classification newobj = new Classification();
		newobj.setClasstype("故障代码");
		newobj.setHaschild("否");
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}

		mainObject = newobj;

		return true;
	}

	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			String classid = classification.getClassid();
			String sql = "select count(a.id) from Failurecode a where a.classid ='"+classid+"'";
			
			int getpm = this.getMainSrv().getBaseDao().selectCountByHql(sql);
			if (getpm >0) {
				Messagebox.show("该分类已关联数据，不能删除该分类！");
				return;
			}
			// 调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}
}
