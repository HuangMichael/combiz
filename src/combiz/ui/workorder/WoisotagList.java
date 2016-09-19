package combiz.ui.workorder;

import combiz.domain.workorder.Wohazard;
import combiz.domain.workorder.Woisotag;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class WoisotagList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WoisotagList()
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
		RecWindow parentWnd = (RecWindow)ownerWnd;
		Workorder workorder = (Workorder)parentWnd.getOwnerWnd().getMainObject();
		Wohazard wohazard = (Wohazard)parentWnd.getMainObject();
		
		if(wohazard == null ||wohazard.getHazardid() == null || wohazard.getHazardid().equals(""))
		{
			Messagebox.show("请在安全窗口选择一危险标识后添加！");
			return false;
		}
		
		Woisotag newobj = new Woisotag();
		newobj.setHazardid(wohazard.getHazardid());
		newobj.setWonum(workorder.getWonum());

		this.mainObject = newobj;
		return true;
	}
	
}
