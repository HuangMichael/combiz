package combiz.ui.common.lookup;

import combiz.business.location.LocstructSrv;
import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.ListWindow;
import combiz.system.ui.common.LookupTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zul.Listbox;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindEquipmentOfLocationsTree extends LookupTree
{
	LocstructSrv locstructSrv;
	
	public FindEquipmentOfLocationsTree() 
	{
		super();
	}

	public void onCreate() throws Exception
	{
		this.setHeight("360px");
		this.setVflex(true);
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
		
		locstructSrv = (LocstructSrv)IBOSrvUtil.getSrv("locstruct");
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
		if(Util.isNotNull(this.getTreeQueryString("LOCATIONS")))
			whereStr = whereStr + " and " + this.getTreeQueryString("LOCATIONS");
		/***************************************************/
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
			this.onSelect();
		}
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
			if(Util.isNotNull(this.getTreeQueryString("LOCATIONS")))
				whereStr = whereStr + " and " + this.getTreeQueryString("LOCATIONS");
			/*****************************************************/
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
	 * 
	 * @TODO
	 * @throws Exception
	 * @��С��  2007-8-10  ����02:19:02
	 */
	public void onSelect() throws Exception
	{
		Treeitem treeitem = this.getSelectedItem();
		ListWindow mainWnd = (ListWindow)this.getFellow("equipmentOfLocations");
		if(treeitem!=null && treeitem.getValue()!=null)
		{
			Locations loc =(Locations) treeitem.getValue();
			String location = loc.getLocation()+":"+loc.getDescription();
			mainWnd.setTitle(location);
			String whereStr = "location='"+loc.getLocation()+"'";
			/****************************************************
			 * ������м�����������Աѡ���ʱ����Ҫ�ڸô�������Ա�Ĺ�������this.getQueryString()
			 */
			if(Util.isNotNull(this.getQueryString()))
				whereStr = whereStr + " and " + this.getQueryString();
			/******************************************************/
			mainWnd.setQueryString(whereStr);
			mainWnd.setOrderby("");
			mainWnd.refreshData();
		}
		else
		{
			mainWnd.setQueryString("1=2");
			mainWnd.refreshData();
		}
	}
}
