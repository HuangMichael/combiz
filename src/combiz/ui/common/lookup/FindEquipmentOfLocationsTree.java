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
	 * 创建初始树
	 * @throws Exception
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		//清除树，重新构建
		this.getChildren().clear();
		
		locstructSrv = (LocstructSrv)IBOSrvUtil.getSrv("locstruct");
		Listbox listbox = (Listbox) this.getFellow("locsystemlistbox");
		if(listbox.getItemCount()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有创建位置系统！"));
			this.appendChild(tc);
			return;
		}
		String systemid = (String)listbox.getSelectedItem().getValue();
		String whereStr = "systemid='"+systemid+"' and parent is null";
		/****************************************************
		 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
		 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
		 */
		if(Util.isNotNull(this.getTreeQueryString("LOCATIONS")))
			whereStr = whereStr + " and " + this.getTreeQueryString("LOCATIONS");
		/***************************************************/
		List locslist = locstructSrv.findWithQuery(whereStr,"orderby");
		if(locslist==null || locslist.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有位置数据！"));
			this.appendChild(tc);
			return;
		}
		
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//设置列显示的最大字符数
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
			//很重要！这段不加上，树的自动滚动就出不来。
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
	 * 展开树节点
	 * @param parentitem
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void expand(Treeitem parentitem)
	throws Exception
	{
		if(parentitem!=null && parentitem.getValue() != null) //如果没有父级
		{
			//*********第一种：每次都删除后重新从数据库中取数
			Treechildren tc = parentitem.getTreechildren();
			if(tc!=null && tc.getChildren().size()>0)
				tc.getChildren().clear();
			//*********第二种：第一次点击是从数据库中取数据，以后就不删除也不取了
			//if(tc.getChildren().size()>0)
			//	return;
			Locstruct locstructParent = (Locstruct) parentitem.getAttribute("locstruct");
			String whereStr = "parent = '"+locstructParent.getLocation()+"' and systemid='" + locstructParent.getSystemid() + "'";
			/****************************************************
			 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
			 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
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
				//很重要！这段不加上，树的自动滚动就出不来。
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
		else //重新刷新树
		{
			this.createRoot();
		}
	}
	
	/**
	 * 
	 * @TODO
	 * @throws Exception
	 * @洪小林  2007-8-10  下午02:19:02
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
			 * 如果是中间树，比如人员选择的时候，需要在该处加入人员的过滤条件this.getQueryString()
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
