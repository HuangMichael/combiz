package combiz.ui.tool;
 
import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

import combiz.business.tool.ToolSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.tool.Tool;
import combiz.domain.tool.Tooltrans;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.TMainWindow;


/**
 * @author ljhadministrator
 *
 */
public class ToolWindow extends TMainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ToolWindow()
	{
		super();
	}

	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：ljh 日期：2008-03-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		
		Classification newobj = new Classification();
		newobj.setClasstype("工具");
		newobj.setHaschild("否");
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}

		mainObject = newobj;
		return true;
	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：发放工具
	 * 日期：2008-4-12 上午09:38:01
	 *
	 */
	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("工具操作发放操作前，请先保存已经发放的记录！");
			return;
		}
		//Classification classification = (Classification) this.mainObject;
		ListWindow toolwnd = (ListWindow) this.getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("请选择一条记录!");
		
			this.popupDialog(this
					.getMainObject(), "/tool/toolreservepopup.xul",
					"toolnum = '"+tool.getToolnum()+"'");
			this.refreshChildData();
		
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：归还工具
	 * 日期：2008-4-12 上午09:37:43
	 *
	 */
	public void verify() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("归还工具操作前，请先保存上次操作的记录！");
			return;
		}
		ListWindow toolwnd = (ListWindow) this.getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("请选择一条记录!");
		
		this.popupDialog(this
				.getMainObject(), "/tool/toolreturnpopup.xul",
				"toolnum = '"+tool.getToolnum()+"'");
		this.refreshChildData();
		
	}

	
	
	/* (non-Javadoc)
	 * @see combiz.system.ui.TMainWindow#delete()
	 */
	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			// 调用业务对象的删除方法
			String sql = "select count(t.id) from Tool t where t.classid in (select c.classid from Classance c where c.ancestor='"+classification.getClassid()+"' and c.classtype = '工具')";
			
			int count = this.getMainSrv().getBaseDao().selectCountByHql(sql);
			if (count >0) {
				Messagebox.show("该分类已关联数据，不能删除该分类！");
				return;
			}
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}

}
