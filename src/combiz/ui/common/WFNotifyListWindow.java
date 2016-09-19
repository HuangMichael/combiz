package combiz.ui.common;

import combiz.domain.ibs.Ibsapps;
import combiz.domain.workflow.Wfassignnotify;
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
 * ֻ�г����ݵ�LISTWINDOW�����ڵ�����ʾĳ���������
 * 
 * ���ߣ���С�� ���ڣ�2007-7-2
 */
public class WFNotifyListWindow extends CommonListWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	public WFNotifyListWindow()
	{
		super();
	}
	
	
	@Override
	public void onCreate()
	throws Exception
	{
		//wfassignWnd.setMainTable("WFASSIGNNOTIFY");
		//wfassignWnd.setQueryString("");
		//wfassignWnd.setOrderby("");
		
		this.setMainTable("WFASSIGNNOTIFY");
		this.setQueryString("assigncode=':{ibsusers.labornum}' and assignstatus='�'");
		//this.setOrderby("");
		
		super.onCreate();
	}

	
	/**
	 * 
	 * @TODO �����ӵ�Ӧ�ó��򣬲��Ҷ�λ��ָ���ļ�¼
	 * @throws Exception
	 * @brianhong  2007-8-4  ����03:06:31
	 */
	@Override
	public void listRowDBClick(Listitem listitem) 
	throws Exception 
	{
		if(listitem!=null && listitem.getValue()!=null)
		{
			//AnnotateDataBinder rowBinder = (AnnotateDataBinder)listbox.getSelectedItem().getValue();
			//Wfassignment wfassign = (Wfassignment)rowBinder.get_beans().get("wfassignment");
			Wfassignnotify wfassignnotify = (Wfassignnotify)this.getObjectFromListbox(listitem, "wfassignnotify");
			String appname = wfassignnotify.getApp();
			List appList = this.getMainSrv().getBaseDao().findWithQuery(Ibsapps.class, "app='"+appname+"'");
			if(appList.size()>0)
			{
				Ibsapps ibsapp = (Ibsapps) appList.get(0);
				Map param= new HashMap();
				param.put("linkKey", wfassignnotify.getAssigncode());
				param.put("linkString", "id = " + wfassignnotify.getOwnerid());
				Window cp = (Window)Executions.createComponents(ibsapp.getPage(), null, param);
				cp.doModal();
				//2008.1.22
				this.refreshData();
			}
		}
	}

}
