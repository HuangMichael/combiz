package combiz.ui.tool;

import java.util.List;

import combiz.business.tool.ToolSrv;
import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolissue;
import combiz.domain.tool.Tooltrans;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.ListWindow;

public class TLreturnDialog 
extends CommonDialog
{
	
	public TLreturnDialog()
	{
		super();
	}
	

	public void onCreate()
	throws Exception
	{
		super.onCreate();
		
//		//获取 工具预留 列表 
		ListWindow toolwnd = (ListWindow) this.getOwnerWnd().getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("请选择一条记录!");

		ListWindow thisWnd = (ListWindow) this.getFellow("tlreturnList");
		thisWnd.setQueryString("toolnum = '"+tool.getToolnum()+"'");
		thisWnd.setOrderby("toolnum desc");
		thisWnd.refreshData();
	}
	
	/**
	 * 产生重新分配工具借还数量的交易记录
	 * 
	 * ljh  2008-03-21
	 * @throws Exception
	 */
	public void reassend() 
	throws Exception
	{
		
		//ToolSrv toolsrv = (ToolSrv) this.getMainSrv()
		ToolSrv toolsrv = (ToolSrv) IBOSrvUtil.getSrv("tool");
		
		ListWindow thiswnd = (ListWindow) this.getFellow("tlreturnList");
		List toollist = thiswnd.getSelectObjects();
		toolsrv.genreturn(toollist); 
		this.detach();
	}
	
	
	
}
