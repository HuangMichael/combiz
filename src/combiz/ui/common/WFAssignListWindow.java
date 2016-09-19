package combiz.ui.common;

import combiz.domain.ibs.Ibsapps;
import combiz.domain.workflow.Wfassignment;
import combiz.system.ui.CommonListWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zk.ui.UiException;
import com.inbasis.zk.ui.event.Event;
import com.inbasis.zul.Listbox;
import com.inbasis.zul.Listitem;
import com.inbasis.zul.Window;

/***
 * 只列出数据的LISTWINDOW，用于单独显示某个表的数据
 * 
 * 作者：洪小林 日期：2007-7-2
 */
public class WFAssignListWindow extends CommonListWindow
{
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	public WFAssignListWindow()
	{
		super();
	}
	
	
	/**
	 * 
	 * @TODO  
	 * @throws Exception
	 * @brianhong  2007-8-4  下午04:14:07
	 */
	@Override
	public void onCreate()
	throws Exception
	{
		//wfassignWnd.setMainTable("WFASSIGNMENT");
		//wfassignWnd.setQueryString("");
		//wfassignWnd.setOrderby("");
		
		this.setMainTable("WFASSIGNMENT");
		this.setQueryString("assigncode=':{ibsusers.labornum}' and assignstatus='活动'");
		//this.setOrderby("");
		
		//
		super.onCreate();
	}
	

	/**
	 * 
	 * 响应resultlist行双击事件监听类,打开链接的应用程序，并且定位到指定的记录
	 * @throws Exception
	 * @洪小林  2007-8-9  下午02:28:02
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
				Window cp = (Window)Executions.createComponents(ibsapp.getPage(), this.getRandomWindow(), param);
				int height = this.getTopWindow().getDesktopHeight();
				cp.setWidth("100%");
				cp.setHeight(height+"px");
				cp.doModal();
				//2008.1.22
				this.refreshData();
			}
		}
	}
	
}
