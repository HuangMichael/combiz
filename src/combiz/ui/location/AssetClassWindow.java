package combiz.ui.location;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class AssetClassWindow extends TMainWindow implements InfWindow {

	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public AssetClassWindow() {
		super();
	}

	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/**********************************
		 * 创建初始值
		 **********************************/
		Classification newobj = new Classification();
		newobj.setClasstype("资产");
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		newobj.setHaschild("否");
		mainObject = newobj;

		return true;
	}

	/**
	 * 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-17
	 */
	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			//调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}

	public void technicunit() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog(this.getMainObject(), "/location/technicunit.xul", "");

	}
}
