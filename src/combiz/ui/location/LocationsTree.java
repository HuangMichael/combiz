package combiz.ui.location;

import combiz.business.location.LocstructSrv;
import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.TopWindow;
import combiz.system.ui.common.MainTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Listbox;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class LocationsTree extends MainTree
{
	LocationsWindow mainWnd;
	LocstructSrv locstructSrv;
	
	public LocationsTree() 
	{
		super();
	}

	public void onCreate()
	{
		this.setVflex(true);
		
		Page topPage = this.getDesktop().getPage("topPage");
		TopWindow topWnd = (TopWindow) topPage.getFellow("topWnd");
		this.setHeight((topWnd.getDesktopHeight() - 140) +"px");
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
		
		mainWnd = (LocationsWindow)this.getFellow("mainWnd");
		locstructSrv = (LocstructSrv)IBOSrvUtil.getSrv("locstruct");
		
		Listbox listbox = (Listbox) this.getFellow("locsystemlistbox");
		if(listbox.getItemCount()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�д���λ��ϵͳ��"));
			this.appendChild(tc);
			return;
		}
		String systemid = (String) listbox.getSelectedItem().getValue();
		String whereStr = "parent is null and systemid='"+systemid+"'";
		String tq = this.getTreeQueryString();
		if(tq!=null && tq.length()>0)
			whereStr = whereStr + " and " + tq;
		List locslist = locstructSrv.findWithQuery(whereStr,"orderby");
		if(locslist==null || locslist.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û��λ�����ݣ�"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
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
			Locstruct locstruct = (Locstruct) treeitem.getAttribute("locstruct");
			mainWnd.getCurBinder().bindBean("locstruct", locstruct);
			Locations parent = (Locations) locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", locstruct.getParent());
			if(parent==null)
				mainWnd.getCurBinder().bindBean("parent", new Locations());
			else
				mainWnd.getCurBinder().bindBean("parent", parent);
			mainWnd.fetchData(treeitem);
		}
		else
			mainWnd.fetchData(null);
	}
	
	/**
	 * ����λ��ϵͳ�Ľڵ���ת������ϵͳʱ����������ϵͳ��λ����
	 * ��С��  Nov 25, 2009
	 * @param locstruct
	 * @param systemid
	 * @param locations
	 * @throws Exception
	 */
	public void createUpTree(Locstruct locstruct, String systemid, Locations locations)
	throws Exception
	{
		this.getChildren().clear();
		String whereStr = "location='"+locstruct.getLocation()+"' and systemid='"+systemid+"'";
		String tq = this.getTreeQueryString();
		if(tq!=null && tq.length()>0)
			whereStr = whereStr + " and " + tq;
		List locslist = locstructSrv.findWithQuery(whereStr,"orderby");
		if(locslist.size()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("["+systemid+"]ϵͳ������λ��["+locstruct.getLocation()+"]����ˢ�£�"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
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
			mainWnd.getCurBinder().bindBean("locstruct", locs);
			Locations parentLocation = (Locations) locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", locs.getParent());
			if(parentLocation==null)
				mainWnd.getCurBinder().bindBean("parent", new Locations());
			else
				mainWnd.getCurBinder().bindBean("parent", parentLocation);
			mainWnd.fetchData(ti);
		}
	}
	//����һ���������õݹ�
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
			if(tc==null)
			{
				tc = new Treechildren();
				tc.setParent(parentitem);
			}
			else if(tc.getChildren().size()>0)
				tc.getChildren().clear();
			//*********�ڶ��֣���һ�ε���Ǵ����ݿ���ȡ���ݣ��Ժ�Ͳ�ɾ��Ҳ��ȡ��
			//if(tc.getChildren().size()>0)
			//	return;
			Locstruct locstructParent = (Locstruct) parentitem.getAttribute("locstruct");
			//�������������������
			String whereStr = "parent = '"+locstructParent.getLocation()+"' and systemid='" + locstructParent.getSystemid() + "'";
			String tq = this.getTreeQueryString();
			if(tq!=null && tq.length()>0)
				whereStr = whereStr + " and " + tq;
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
				
				//�ݹ�������Ӽ�
				if(this.isOpenchild() && Util.getBoolean(locstruct.getHaschild()))
				{
					this.expand(ti);
				}
			
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
		Treeitem treeitem = getSelectedItem();
		if(treeitem!=null && treeitem.getValue()!=null)
		{
			Locstruct locstruct = (Locstruct) treeitem.getAttribute("locstruct");
			mainWnd.getCurBinder().bindBean("locstruct", locstruct);
			Locations parent = (Locations) locstructSrv.getBaseDao().findUniqueBy(Locations.class, "location", locstruct.getParent());
			if(parent==null)
				mainWnd.getCurBinder().bindBean("parent", new Locations());
			else
				mainWnd.getCurBinder().bindBean("parent", parent);
			mainWnd.fetchData(treeitem);
		}
		else
			mainWnd.fetchData(null);
	}

	
	/**
	 * ���¹������ڵ�
	 * 
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 * @throws Exception 
	 */
	public void afterModifyItem()
	throws Exception
	{
		Treeitem item = getSelectedItem();
		if(item==null)
			return;
		Locations locations = (Locations)mainWnd.getMainObject();
		item.setValue(locations);
		item.setLabel(locations.getDescription());
		
		this.onSelect();
	}
}
