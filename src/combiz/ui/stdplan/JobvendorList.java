package combiz.ui.stdplan;

import combiz.domain.stdplan.Jobplan;
import combiz.domain.stdplan.Jobvendor;
import combiz.system.ui.ListWindow;

public class JobvendorList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public JobvendorList()
	{
		super();
	}
			

	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		JobplanWindow parentWnd = (JobplanWindow)ownerWnd;
		Jobplan parent = (Jobplan) parentWnd.getMainObject();
		
		Jobvendor newobj = new Jobvendor();
		newobj.setJpnum(parent.getJpnum());
		//在这里添加新建初始化数据

		this.mainObject = newobj;
		return true;
	}	


}
