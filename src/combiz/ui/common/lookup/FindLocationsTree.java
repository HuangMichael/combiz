package combiz.ui.common.lookup;

import combiz.business.location.LocstructSrv;
import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.LookupTree;
import combiz.system.ui.common.MainTree.openNode;
import combiz.system.util.Util;

import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Execution;
import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Listbox;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;
import com.inbasis.zul.Window;

public class FindLocationsTree extends LookupTree
{

	LocstructSrv locstructSrv;
	private RecWindow recWindow;
	private String lookupID;
	
	public FindLocationsTree() 
	{
		super();
		Execution exec = Executions.getCurrent();
		Map param = exec.getArg();
		//�����ֶ����ڵ�������
		recWindow = (RecWindow) param.get("mainWnd");
		//�����ֶ�
		lookupID = (String)param.get("lookupID");
	}

	public void onCreate() throws Exception
	{
		this.setHeight("400px");
		this.setVflex(true);
		
		locstructSrv = (LocstructSrv)IBOSrvUtil.getSrv("locstruct");
	}
	
	
	/**
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		Listbox listbox = (Listbox) this.getFellow("locsystemlistbox");
		if(listbox.getItemCount()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�д���λ��ϵͳ��"));
			this.appendChild(tc);
			return;
		}
		String systemid = (String)listbox.getSelectedItem().getValue();
		String whereStr = "systemid='"+systemid+"' and parent is null";
		/****************************************************
		 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
		 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
		 */
		if(Util.isNotNull(this.getQueryString()))
			whereStr = whereStr + " and " + this.getQueryString();
		/******************************************************/
		List locslist = locstructSrv.findWithQuery(whereStr,"orderby");
		if(locslist==null || locslist.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û��λ�����ݣ�"));
			this.appendChild(tc);
			return;
		}
		
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//��������ʾ������ַ���
		//tcols.appendChild(tcol);
		//this.appendChild(tcols);
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<locslist.size();i++)
		{
			Locstruct locstruct = (Locstruct)locslist.get(i);
			int count = locstructSrv.getBaseDao().selectCountByWhere(Locstruct.class, "location='"+locstruct.getLocation()+"'");
			Locations locations = (Locations) locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", locstruct.getLocation());
			String locdesc = locations.getDescription();
			if(locdesc==null)
				locdesc = "";
			ti = new Treeitem(locdesc);
			ti.setAttribute("locstruct", locstruct);
			ti.setValue(locations);
			if(count>1)
				ti.setImage("/images/img_location_system.gif");
			else
				ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			//����Ҫ����β����ϣ������Զ������ͳ�������
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(locstruct.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
				this.expand(ti);
			}
			tc.appendChild(ti);
		}
		this.appendChild(tc);
		
		if(this.getItemCount()>0)
		{
			Treeitem treeitem = (Treeitem)this.getItems().iterator().next();
			this.selectItem(treeitem);
		}
	}
	
	/**
	 * ��������ԭʼֵ����
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot(String value)
	throws Exception
	{
		Locations locations = (Locations) this.locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", value);
		this.getChildren().clear();
		String whereStr = "location='"+value+"' and systemid=(select t.systemid from Locsystem t where t.isdefault='��')";
		List locslist = locstructSrv.findWithQuery(whereStr,"orderby");
		if(locations==null || locslist.size()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("Ĭ��ϵͳ�в�����λ��["+value+"]��"));
			this.appendChild(tc);
			return;
		}
		for(int i=0;i<locslist.size();i++)
		{
			Locstruct locs = (Locstruct) locslist.get(i);
			Treeitem ti = new Treeitem(locations.getDescription());
			ti.setAttribute("locstruct", locs);
			ti.setValue(locations);
			int count = locstructSrv.getBaseDao().selectCountByWhere(Locstruct.class, "location='"+locs.getLocation()+"'");
			if(count>1)
				ti.setImage("/images/img_location_system.gif");
			else
				ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			//����Ҫ����β����ϣ������Զ������ͳ�������
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(locs.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
				this.expand(ti);
			}
			//��ײ��tc
			Treechildren bottomTc = new Treechildren();
			bottomTc.appendChild(ti);

			//�ݹ���ô�������
			Treechildren topTc = null;
			Locstruct parent = this.getParent(locs);
			while(parent!=null)
			{
				topTc = this.createUpItem(parent, bottomTc);
				parent = this.getParent(parent);
				bottomTc = topTc;
			}

			//����tc
			if(topTc==null)
				topTc = bottomTc;
			this.appendChild(topTc);
			this.selectItem(ti);
		}
	}
	
	/**
	 * ��ȡλ�ø���
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	private Locstruct getParent(Locstruct locstruct)
	throws Exception
	{
		List parentList = locstructSrv.findWithQuery("location = '" + locstruct.getParent()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
		if (parentList.size() > 0)
		{
			Locstruct parent = (Locstruct) parentList.get(0);
			return parent;
		}
		return null;
	}
	
	//��createRoot�������õݹ�
	private Treechildren createUpItem(Locstruct locstruct, Treechildren childTc)
	throws Exception
	{
		Treechildren tc = new Treechildren();
		Locations locations = (Locations) locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", locstruct.getLocation());
		Treeitem ti = new Treeitem(locations.getDescription());
		ti.setAttribute("locstruct", locstruct);
		ti.setValue(locations);
		int count = locstructSrv.getBaseDao().selectCountByWhere(Locstruct.class, "location='"+locstruct.getLocation()+"'");
		if(count>1)
			ti.setImage("/images/img_location_system.gif");
		else
			ti.setImage("/images/img_location.gif");
		ti.setOpen(true);
		//����Ҫ����β����ϣ������Զ������ͳ�������
		Treerow treerow = (Treerow)ti.getChildren().get(0);
		Treecell treecell = (Treecell)treerow.getChildren().get(0);
		treecell.setStyle("white-space:nowrap;");
		//////////////////////////////////////////////
		ti.addEventListener("onOpen", new openNode());
		ti.appendChild(childTc);
		tc.appendChild(ti);
		
		return tc;
	}
	
	
	/**
	 * չ�����ڵ�
	 * @param parentitem
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void expand(Treeitem parentitem)
	throws Exception
	{
		if(parentitem!=null && parentitem.getValue() != null) //���û�и���
		{
			//*********��һ�֣�ÿ�ζ�ɾ�������´����ݿ���ȡ��
			Treechildren tc = parentitem.getTreechildren();
			if(tc!=null && tc.getChildren().size()>0)
				tc.getChildren().clear();
			//*********�ڶ��֣���һ�ε���Ǵ����ݿ���ȡ���ݣ��Ժ�Ͳ�ɾ��Ҳ��ȡ��
			//if(tc.getChildren().size()>0)
			//	return;
			
			Locstruct locstructParent = (Locstruct) parentitem.getAttribute("locstruct");
			String whereStr = "parent = '"+locstructParent.getLocation()+"' and systemid='" + locstructParent.getSystemid() + "'";
			/****************************************************
			 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
			 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
			 */
			//�������������������
			if(Util.isNotNull(this.getQueryString()))
				whereStr = whereStr + " and " + this.getQueryString();
			/******************************************************/
			List locslist = locstructSrv.findWithQuery(whereStr,"orderby");
			Treeitem ti;
			for(int i=0;i<locslist.size();i++)
			{
				Locstruct locstruct = (Locstruct)locslist.get(i);
				int count = locstructSrv.getBaseDao().selectCountByWhere(Locstruct.class, "location='"+locstruct.getLocation()+"'");
				Locations locations = (Locations) locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", locstruct.getLocation());
				if(locations==null)
					continue;
				String locdesc = locations.getDescription();
				if(locdesc==null)
					locdesc = "";
				ti = new Treeitem(locdesc);
				ti.setAttribute("locstruct", locstruct);
				ti.setValue(locations);
				if(count>1)
					ti.setImage("/images/img_location_system.gif");
				else
					ti.setImage("/images/img_location.gif");
				ti.setOpen(false);
				//����Ҫ����β����ϣ������Զ������ͳ�������
				Treerow treerow = (Treerow)ti.getChildren().get(0);
				Treecell treecell = (Treecell)treerow.getChildren().get(0);
				treecell.setStyle("white-space:nowrap;");
				//////////////////////////////////////////////
				if(Util.getBoolean(locstruct.getHaschild()))
				{
					Treechildren nextChild = new Treechildren();
					ti.appendChild(nextChild);
					ti.addEventListener("onOpen", new openNode());
				}
				tc.appendChild(ti);
			}
		}
		else //����ˢ����
		{
			this.createRoot();
		}
	}

	
	/**
	 * ѡ���¼�
	 * 
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void onSelect() 
	throws Exception
	{
		//Treeitem item = getSelectedItem();
	}
}
