package combiz.ui.budget;

import combiz.business.budget.BudgetitemSrv;
import combiz.domain.budget.Budgetitem;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.TopWindow;
import combiz.system.ui.common.MainTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treecol;
import com.inbasis.zul.Treecols;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class BudgetItemTree extends MainTree
{
	BudgetitemWindow mainWnd;
	BudgetitemSrv budgetitemSrv;
	
	public BudgetItemTree() 
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
	 * 创建初始树
	 * @throws Exception
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		String version = "";
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) this.getFellow("budgetItemVersion");
		budgetItemVersion.createList();
		if(budgetItemVersion.getItemCount()>0)
			version = (String) budgetItemVersion.getItemAtIndex(0).getValue();
		this.createTree(version);
	}
	
	
	public void createTree(String version)	
	throws Exception
	{
		//清除树，重新构建
		this.getChildren().clear();
		
		mainWnd = (BudgetitemWindow)this.getFellow("mainWnd");
		budgetitemSrv = (BudgetitemSrv)IBOSrvUtil.getSrv("budgetitem");
		budgetitemSrv.setOrderBy("orderby");
		List list = budgetitemSrv.findWithQuery("parent is null and version='"+version+"'");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
			return;
		}
		
		Treecols tcols = new Treecols();
		Treecol tcol = new Treecol();
		tcol.setMaxlength(20);//设置列显示的最大字符数
		tcols.appendChild(tcol);
		this.appendChild(tcols);
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Budgetitem budgetitem = (Budgetitem)list.get(i);
			ti = new Treeitem(budgetitem.getBuditem() +":"+ budgetitem.getDescription());
			ti.setTooltiptext(budgetitem.getBuditem() +":"+ budgetitem.getDescription());
			ti.setValue(budgetitem);
			ti.setImage("/images/node_image_dept.gif");
			ti.setOpen(true);
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(budgetitem.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
				this.expand(ti);
			}
			tc.appendChild(ti);
		}
		this.appendChild(tc);
		
		//选中记录
		if(this.getItemCount()>0)
		{
			Treeitem treeitem = (Treeitem)this.getItems().iterator().next();
			this.selectItem(treeitem);
			mainWnd.fetchData(treeitem);
		}
		else
			mainWnd.fetchData(null);
	}
	
	/**
	 * 展开树节点
	 * @param parentitem
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void expand(Treeitem parentitem)
	throws Exception
	{
		//*********第一种：每次都删除后重新从数据库中取数
		Treechildren tc = parentitem.getTreechildren();
		if(tc!=null && tc.getChildren().size()>0)
			tc.getChildren().clear();
		//*********第二种：第一次点击是从数据库中取数据，以后就不删除也不取了
		//if(tc.getChildren().size()>0)
		//	return;

		Budgetitem parent = (Budgetitem)parentitem.getValue();
		List list = budgetitemSrv.findWithQuery("parent = '"+parent.getBuditem()+"' and version='"+parent.getVersion()+"'","orderby");
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Budgetitem budgetitem = (Budgetitem)list.get(i);
			ti = new Treeitem(budgetitem.getBuditem() +":"+ budgetitem.getDescription());
			ti.setTooltiptext(budgetitem.getBuditem() +":"+ budgetitem.getDescription());
			ti.setValue(budgetitem);
			ti.setImage("/images/node_image_dept.gif");
			ti.setOpen(true);
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(budgetitem.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
			}
			tc.appendChild(ti);
			
			//递归打开所有子集
			if(this.isOpenchild() && Util.getBoolean(budgetitem.getHaschild()))
			{
				this.expand(ti);
			}
		}
		

	}
	
	/**
	 * 选择事件
	 * 
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void onSelect() 
	throws Exception
	{
		Treeitem item = getSelectedItem();
		mainWnd.fetchData(item);
	}

	
	/**
	 * 重新构建树节点
	 * 
	 * 作者：洪小林 日期：2007-4-25
	 * @throws Exception 
	 */
	public void afterModifyItem()
	throws Exception
	{
		Treeitem item = getSelectedItem();
		if(item==null)
			return;
		Budgetitem budgetitem = (Budgetitem)mainWnd.getMainObject();
		item.setValue(budgetitem);
		item.setLabel(budgetitem.getBuditem() +":"+ budgetitem.getDescription());
		item.setTooltiptext(budgetitem.getBuditem() +":"+ budgetitem.getDescription());
		this.onSelect();
	}
	
}
