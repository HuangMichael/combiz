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
		
//		//��ȡ ����Ԥ�� �б� 
		ListWindow toolwnd = (ListWindow) this.getOwnerWnd().getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("��ѡ��һ����¼!");

		ListWindow thisWnd = (ListWindow) this.getFellow("tlreturnList");
		thisWnd.setQueryString("toolnum = '"+tool.getToolnum()+"'");
		thisWnd.setOrderby("toolnum desc");
		thisWnd.refreshData();
	}
	
	/**
	 * �������·��乤�߽軹�����Ľ��׼�¼
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
