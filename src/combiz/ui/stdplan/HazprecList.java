package combiz.ui.stdplan;

import combiz.domain.stdplan.Hazard;
import combiz.domain.stdplan.Hazardprec;
import combiz.system.ui.ListWindow;

public class HazprecList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public HazprecList()
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
		HazardWindow parentWnd = (HazardWindow)ownerWnd;
		Hazard parent = (Hazard) parentWnd.getMainObject();
		
		Hazardprec newobj = new Hazardprec();
		newobj.setHazardid(parent.getHazardid());

		this.mainObject = newobj;
		return true;
	}
	
	
}
