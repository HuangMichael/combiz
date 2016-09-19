package combiz.ui.workorder;

import combiz.domain.workorder.Woisolockout;
import combiz.domain.workorder.Woisotag;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class WoisolockoutList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WoisolockoutList()
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
		RecWindow woisotagWnd = (RecWindow)ownerWnd;
		//RecWindow WorkorderWnd = (RecWindow)ownerWnd.getOwnerWnd().getOwnerWnd();
		
		Woisotag woisotag = (Woisotag)woisotagWnd.getMainObject();
		//Workorder workorder = (Workorder)WorkorderWnd.getMainObject();
		
		if(woisotag == null ||woisotag.getHazardid() == null || woisotag.getHazardid().equals(""))
		{
			Messagebox.show("请在安全窗口选择一危险标识后添加！");
			return false;
		}
		
		Woisolockout newobj = new Woisolockout();
		newobj.setWonum(woisotag.getWonum());
		newobj.setIsotagid(woisotag.getIsotagid());
		newobj.setAplyseq(woisotag.getAplyseq());
		newobj.setState(woisotag.getState());
		newobj.setLocation(woisotag.getLocation());
		newobj.setEqnum(woisotag.getEqnum());

		this.mainObject = newobj;
		return true;
	}
	
}
