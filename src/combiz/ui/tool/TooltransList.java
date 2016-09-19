package combiz.ui.tool;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Tooltrans;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class TooltransList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public TooltransList()
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
		Tool parent = (Tool) this.getOwnerWnd().getMainObject();
		
		Tooltrans newobj = new Tooltrans();
		newobj.setToolnum(parent.getToolnum());
		newobj.setEnterby(this.getUserInfo().getLabornum());
		newobj.setEnterdate(new Date());
		this.mainObject = newobj;
		return true;
	}

}
