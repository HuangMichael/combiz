package combiz.ui.tool;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolspec;
import combiz.system.ui.ListWindow;
import java.util.Date;

import com.inbasis.zul.Messagebox;

public class ToolspecList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ToolspecList()
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
		
		Toolspec newobj = new Toolspec();
		newobj.setToolnum(parent.getToolnum());
		newobj.setClassid(parent.getClassid());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setIsmustbe("否");

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
