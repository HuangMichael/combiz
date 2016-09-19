package combiz.ui.stdplan;

import combiz.domain.stdplan.Isolockout;
import combiz.domain.stdplan.Isotag;
import combiz.system.ui.ListWindow;

public class IsolockoutList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public IsolockoutList()
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
		Isotag parent = (Isotag) this.ownerWnd.getMainObject();
		
		Isolockout newobj = new Isolockout();
		newobj.setIsotagid(parent.getIsotagid());
		newobj.setAplyseq(parent.getAplyseq());
		newobj.setState(parent.getState());
		newobj.setLocation(parent.getLocation());
		newobj.setEqnum(parent.getEqnum());

		this.mainObject = newobj;
		return true;
	}
	

}
