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
 * ֻ�г����ݵ�LISTWINDOW�����ڵ�����ʾĳ���������
 * 
 * ���ߣ���С�� ���ڣ�2007-7-2
 */
public class WFAssignListWindow extends CommonListWindow
{
	
	///////////////////////////////////������////////////////////////////////////////////////
	public WFAssignListWindow()
	{
		super();
	}
	
	
	/**
	 * 
	 * @TODO  
	 * @throws Exception
	 * @brianhong  2007-8-4  ����04:14:07
	 */
	@Override
	public void onCreate()
	throws Exception
	{
		//wfassignWnd.setMainTable("WFASSIGNMENT");
		//wfassignWnd.setQueryString("");
		//wfassignWnd.setOrderby("");
		
		this.setMainTable("WFASSIGNMENT");
		this.setQueryString("assigncode=':{ibsusers.labornum}' and assignstatus='�'");
		//this.setOrderby("");
		
		//
		super.onCreate();
	}
	

	/**
	 * 
	 * ��Ӧresultlist��˫���¼�������,�����ӵ�Ӧ�ó��򣬲��Ҷ�λ��ָ���ļ�¼
	 * @throws Exception
	 * @��С��  2007-8-9  ����02:28:02
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
