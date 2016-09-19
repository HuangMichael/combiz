package combiz.ui.basedata;

import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.system.ui.ListWindow;

public class ClassSepcList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ClassSepcList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		//ItemClassWindow parentWnd = (ItemClassWindow)ownerWnd;
		Classification parent = (Classification) this.getOwnerWnd().getMainObject();
		
		Classspec newobj = new Classspec();
		newobj.setClassid(parent.getClassid());
		newobj.setIsmustbe("否");
		this.mainObject = newobj;
		return true;
	}
	
}
