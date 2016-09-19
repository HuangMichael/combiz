package combiz.ui.tool;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class ToolClassWindow extends TMainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数
	 * 
	 * 作者：李阳 日期：2008-2-29 下午02:30:41
	 *
	 */
	public ToolClassWindow()
	{
		super();
	}
	
	/* 
	 * 功能：
	 * 作者：李阳
	 * 日期：2008-2-29下午02:33:09
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
		if(ti!=null && ti.getValue()!=null)
		{
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		
		mainObject = newobj;
		
		return true;
	}
	

	/**
	 * 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null)
		{
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) 
		{
			//调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}

	
}
