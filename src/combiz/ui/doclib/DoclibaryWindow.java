package combiz.ui.doclib;

import combiz.business.doclib.DoclibarySrv;
import combiz.domain.doclib.Doclibary;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import java.io.File;
import java.util.Date;

import com.inbasis.zk.ui.Execution;
import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class DoclibaryWindow extends TMainWindow implements InfWindow {

	// ///////////////////////////
	// ///该类的主对象为 Classification
	// //
	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public DoclibaryWindow() {
		super();
	}

	/**
	 * 新增记录
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Doclibary newobj = new Doclibary();
		
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Doclibary doclibary = (Doclibary) ti.getValue();
			newobj.setParent(doclibary.getLibnum());
			newobj.setCreator(this.getLaborInfo().getLabornum());
			newobj.setCreatedate(new Date());
			newobj.setLibpath(doclibary.getLibpath());
		}
		else
		{
			newobj.setCreator(this.getLaborInfo().getLabornum());
			newobj.setCreatedate(new Date());
			newobj.setLibpath("/");
		}
		newobj.setHaschild("否");
		mainObject = newobj;
		
		return true;
	}

	/**
	 * @TODO 字段类中使用，得到父目录的libpath值
	 * @return 父目录的libpath值
	 * yuanjq 2007-8-17 上午11:13:07
	 */ 
	public String getParentPathStr()
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Doclibary doclibary = (Doclibary) ti.getValue();
			return doclibary.getLibpath(); 
		}
		return "/";
	}
	/**
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2007-3-17
	 */
	public void delete() throws Exception {
		if (Messagebox.show("确定删除此目录及以下的所有文档、关联应用程序、文档版本、文档权限等相关信息?", "提示",
				Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.NO) {
			return;
		}
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Doclibary Doclibary = (Doclibary) ti.getValue();
		if (Doclibary != null) {
			// 调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}
}
