package combiz.ui.workorder;

import combiz.domain.stdplan.Hazardtag;
import combiz.domain.workorder.Wohazard;
import combiz.system.ui.ListWindow;

public class WohazardtagList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WohazardtagList()
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
		Wohazard wohazard = (Wohazard)this.getOwnerWnd().getMainObject();
		if(wohazard == null ||wohazard.getHazardid() == null || wohazard.getHazardid().equals(""))
		{
			throw new Exception("请在安全窗口选择一危险标识后添加");
		}
		
		Hazardtag newobj = new Hazardtag();
		newobj.setHazardid(wohazard.getHazardid());

		this.mainObject = newobj;
		return true;
	}
	
}
