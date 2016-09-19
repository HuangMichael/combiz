package combiz.ui.stdplan;

import combiz.domain.stdplan.Jobplan;
import combiz.domain.stdplan.Jobtask;
import combiz.system.ui.ListWindow;

public class JobtaskList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public JobtaskList()
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
		JobplanWindow parentWnd = (JobplanWindow)ownerWnd;
		Jobplan parent = (Jobplan) parentWnd.getMainObject();
		
		Jobtask newobj = new Jobtask();
		newobj.setJpnum(parent.getJpnum());
		newobj.setTaskduration(0.0D);
		this.mainObject = newobj;
		return true;
	}

}
