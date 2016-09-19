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
 * ֻ�г����ݵ�LISTWINDOW�����ڵ�����ʾĳ���������
 * 
 * ���ߣ���С�� ���ڣ�2007-7-2
 */
public class WFCompAssignListWindow extends CommonListWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
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
		this.setQueryString("assigncode=':{ibsusers.labornum}' and assignstatus='���'");
		//this.setOrderby("");
		
		//this.setQueryString("id in (select w.id from Wfassignment w where (w.assigncode in (select a.assigncode from Wftrans a where a.assigncode = ':{ibsusers.labornum}' and a.wfinstid = w.wfinstid)) and (w.assignstatus = '���'))");
		
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
	 * ���̻��˲���
	 * brianhong  2008-1-22
	 * @throws Exception
	 */
	public void wfreturn()
	throws Exception 
	{
		Wfassignment wfassignment = (Wfassignment)this.getSelectObject();
		if(wfassignment==null)
		{
			Messagebox.show("����ѡ��һ���Ѵ��������");
			return;
		}
		int rtn = Messagebox.show("ȷ�����˸�������","ȷ�ϣ�",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.YES) //�ش��ǣ��������ݣ���ִ����һ������
		{
			if(this.getMainSrv().wfreturn(wfassignment))
			{
				this.refreshData();
				Messagebox.show("���������˳ɹ���");
			}
		}	 
	}
}
