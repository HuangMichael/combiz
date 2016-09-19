package combiz.ui.inventory;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.system.ui.ListWindow;

import java.util.Date;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

public class ProvideList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ProvideList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：高群凯 日期：2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Matreq parent = (Matreq)ownerWnd.getMainObject();
		if(this.getOwnerWnd().getApp().equals("ISSUEEQ")||this.getOwnerWnd().getApp().equals("PROVIDE"))
		{
			throw new Exception("不能新建记录，请从选择操作菜单下的选择发放行进行操作！");
		}
		Invusetrans newobj = new Invusetrans();
		newobj.setMatreqnum(parent.getMatreqnum());
		newobj.setEnterby(this.getLaborInfo().getLabornum());
		newobj.setActualdate(new Date());
		newobj.setIssuetype("未提交");
		newobj.setRequestdate(parent.getRequestdate());
		newobj.setUsedate(parent.getUsedate());
		newobj.setTransdate(new Date());
		newobj.setConversion(1D);
		
		this.mainObject = newobj;
		return true;
	}
	
	
	
}
