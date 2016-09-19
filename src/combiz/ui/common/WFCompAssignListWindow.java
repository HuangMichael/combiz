package combiz.ui.common;

import combiz.domain.ibs.Ibsapps;
import combiz.domain.workflow.Wfassignment;
import combiz.system.ui.CommonListWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Listbox;
import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

/***
 * 只列出数据的LISTWINDOW，用于单独显示某个表的数据
 * 
 * 作者：洪小林 日期：2007-7-2
 */
public class WFCompAssignListWindow extends CommonListWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	public WFCompAssignListWindow()
	{
		super();
	}
	
	
	@Override
	public void onCreate()
	throws Exception
	{
		//wfassignWnd.setMainTable("WFASSIGNMENT");
		//wfassignWnd.setQueryString("");
		//wfassignWnd.setOrderby("");
		
		this.setMainTable("WFASSIGNMENT");
		this.setQueryString("assigncode=':{ibsusers.labornum}' and assignstatus='完成'");
		//this.setOrderby("");
		
		//this.setQueryString("id in (select w.id from Wfassignment w where (w.assigncode in (select a.assigncode from Wftrans a where a.assigncode = ':{ibsusers.labornum}' and a.wfinstid = w.wfinstid)) and (w.assignstatus = '完成'))");
		
		super.onCreate();
	}

	
	/**
	 * 
	 * @TODO 打开链接的应用程序，并且定位到指定的记录
	 * @throws Exception
	 * @brianhong  2007-8-4  下午03:06:31
	 */
	@Override
	public void listRowDBClick(Listitem listitem) 
	throws Exception 
	{
		if(listitem!=null && listitem.getValue()!=null)
		{
			Wfassignment wfassign = (Wfassignment)this.getObjectFromListbox(listitem, "wfassignment");
			String appname = wfassign.getApp();
			List appList = this.getMainSrv().getBaseDao().findWithQuery(Ibsapps.class, "app='"+appname+"'");
			if(appList.size()>0)
			{
				Ibsapps ibsapp = (Ibsapps) appList.get(0);
				Map param= new HashMap();
				param.put("linkKey", wfassign.getAssigncode());
				param.put("linkString", "id = " + wfassign.getOwnerid());
				Window cp = (Window)Executions.createComponents(ibsapp.getPage(), null, param);
				cp.doModal();
				//2008.1.22
				this.refreshData();
			}
		}

	}
	
	
	/**
	 * 流程回退操作
	 * brianhong  2008-1-22
	 * @throws Exception
	 */
	public void wfreturn()
	throws Exception 
	{
		Wfassignment wfassignment = (Wfassignment)this.getSelectObject();
		if(wfassignment==null)
		{
			Messagebox.show("请先选中一条已处理的任务！");
			return;
		}
		int rtn = Messagebox.show("确定回退该任务吗？","确认！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.YES) //回答是，保存数据，不执行下一步动作
		{
			if(this.getMainSrv().wfreturn(wfassignment))
			{
				this.refreshData();
				Messagebox.show("工作流回退成功！");
			}
		}	 
	}
}
