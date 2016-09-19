package combiz.ui.tool;

import combiz.business.tool.ToolSrv;
import java.util.*;
import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolreserve;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.ListWindow;

public class TLsendDialog 
extends CommonDialog
{
	
	public TLsendDialog()
	{
		super();
	}
	

	public void onCreate()
	throws Exception
	{
		super.onCreate();
		
		//��ȡ ����Ԥ�� �б� 
		ListWindow toolwnd = (ListWindow) this.getOwnerWnd().getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("��ѡ��һ����¼!");
		
		ListWindow thisWnd = (ListWindow) this.getFellow("tlsendcountList");
		thisWnd.setQueryString("toolnum = '" + tool.getToolnum() + "' and reservedqty != 0");
		thisWnd.setOrderby("reqdate desc");
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
		ToolSrv toolsrv = (ToolSrv) IBOSrvUtil.getSrv("tool");
		
		ListWindow thiswnd = (ListWindow) this.getFellow("tlsendcountList");
		List toollist = thiswnd.getSelectObjects();
		toolsrv.geninvuse(toollist); 
		this.detach();
	}
	
	
	
}
