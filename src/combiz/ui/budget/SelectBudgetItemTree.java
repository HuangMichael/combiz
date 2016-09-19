package combiz.ui.budget;

import combiz.business.budget.BudgetitemSrv;
import combiz.domain.budget.Budgetitem;
import combiz.system.IBOSrvUtil;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zk.ui.Page;
import com.inbasis.zk.ui.UiException;
import com.inbasis.zk.ui.event.Event;
import com.inbasis.zk.ui.event.Events;
import com.inbasis.zk.ui.event.SelectEvent;
import com.inbasis.zul.Tree;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class SelectBudgetItemTree extends Tree
{   
	BudgetitemSrv budgetitemSrv;
	
	public SelectBudgetItemTree() 
	{
		super();
		this.setId("selectBudgetItemTree");
	}

	public void onCreate() throws Exception
	{
		this.setVflex(true);
		this.setMultiple(true);
		this.setCheckmark(true);

		this.createRoot();
	}
	/**
	 * 获取版本，创建树
	 * @throws Exception
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		/*String version = "";
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) this.getFellow("budgetItemVersion");
		budgetItemVersion.createList();
		if(budgetItemVersion.getItemCount()>0)
			version = (String) budgetItemVersion.getItemAtIndex(0).getValue();*/
		
		this.createTree();
	}
	
	/**
	 * 创建树
	 * brianhong  2008-11-24
	 * @param version
	 * @throws Exception
	 */
	public void createTree()
	throws Exception
	{
		//清除树，重新构建
		this.getChildren().clear();
		
		budgetitemSrv = (BudgetitemSrv)IBOSrvUtil.getSrv("budgetitem");
		String whereStr = "(parent is null or parent='') and enabled='是'";
		List list = budgetitemSrv.findWithQuery(whereStr);
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有设置预算项目或者没有启用！"));
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
		for(int i=0;i<list.size();i++)
		{
			Budgetitem budgetitem = (Budgetitem)list.get(i);
			ti = new Treeitem(budgetitem.getBuditem() + ":" + budgetitem.getDescription());
			ti.setValue(budgetitem);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			treerow.addEventListener("onClick", new ItemClick());
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
	}
	
	
	/**
	 * 点击展开节点
	 * @author brianhong
	 *
	 */
	public class openNode implements com.inbasis.zk.ui.event.EventListener {
		public void onEvent(Event event) throws UiException {
			Treeitem treeitem = (Treeitem)event.getTarget();
			try {
				expand(treeitem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public boolean isAsap() {
		return true; //Refer the following section for description
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
		//*********第一种：每次都删除后重新从数据库中取数
		Treechildren tc = parentitem.getTreechildren();
		if(tc!=null && tc.getChildren().size()>0)
			tc.getChildren().clear();
		//*********第二种：第一次点击是从数据库中取数据，以后就不删除也不取了
		//if(tc.getChildren().size()>0)
		//	return;

		Budgetitem parent = (Budgetitem)parentitem.getValue();
		String whereStr = "parent = '"+parent.getBuditem()+"' and enabled='是'";
		List list = budgetitemSrv.findWithQuery(whereStr);
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Budgetitem budgetitem = (Budgetitem)list.get(i);
			ti = new Treeitem(budgetitem.getBuditem() + ":" + budgetitem.getDescription()); //
			ti.setValue(budgetitem);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(false);
			ti.addEventListener("onClick", new ItemClick());
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			treerow.addEventListener("onClick", new ItemClick());
			//////////////////////////////////////////////
			if(Util.getBoolean(budgetitem.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
			}
			
			if(parentitem.isSelected())
				ti.setSelected(true);
			
			tc.appendChild(ti);
		}
	}
	
	
	/**
	 * 点击展开节点
	 * @author brianhong
	 *
	 */
	public class ItemClick implements com.inbasis.zk.ui.event.EventListener {
		public void onEvent(Event event) throws UiException {
			Treerow treerow = (Treerow)event.getTarget();
			try {
				itemClick(treerow.getTreeitem());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public boolean isAsap() {
		return true; //Refer the following section for description
		}
	}
	
	
	/**
	 * 点击选择树形
	 * brianhong  2008-11-22
	 */
	public void itemClick(Treeitem treeitem)
	{
		Budgetitem budgetitem = (Budgetitem) treeitem.getValue();
		if(treeitem.isOpen())
		{
			Treechildren treechildren = treeitem.getTreechildren();
			List child = treechildren.getChildren();
			if(child.size()>0)
			{
				if(treeitem.isSelected())
				{
					for(int i=0;i<child.size();i++)
						((Treeitem)child.get(i)).setSelected(true);
				}
				else
				{
					for(int i=0;i<child.size();i++)
						((Treeitem)child.get(i)).setSelected(false);
				}
			}
		}
	}
}
