package combiz.ui.common.lookup;

import combiz.business.budget.BudgetSrv;
import combiz.business.budget.BudgetitemSrv;
import combiz.business.classattr.ClassificationSrv;
import combiz.business.corp.LaborSrv;
import combiz.domain.budget.Budget;
import combiz.domain.budget.Budgetitem;
import combiz.domain.corp.Department;
import combiz.domain.corp.Labor;
import combiz.domain.location.Locations;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.ListWindow;
import combiz.system.ui.LookupWindow;
import combiz.system.ui.common.LookupTree;
import combiz.system.util.Util;
import combiz.ui.budget.BudgetItemVersion;

import java.util.List;

import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindBudgetItemTree extends LookupTree
{   
	private String type;
	BudgetitemSrv budgetitemSrv = (BudgetitemSrv)IBOSrvUtil.getSrv("budgetitem");
	public FindBudgetItemTree() 
	{
		super();
	}

	public void onCreate() throws Exception
	{
		this.setHeight("300px");
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
		/*String version = "";
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) this.getFellow("budgetItemVersion");
		budgetItemVersion.createList();
		if(budgetItemVersion.getItemCount()>0)
			version = (String) budgetItemVersion.getItemAtIndex(0).getValue();*/
		
		//过滤列表窗口数据
		
		LookupWindow lookupWnd = (LookupWindow) this.getFellow("lookupWnd");
		if(lookupWnd!=null)
		{
			String labornum = lookupWnd.getUserInfo().getLabornum();
			String dept = null;
			List list = budgetitemSrv.findWithQuery(Labor.class,"labornum='"+labornum+"'");
			if(list.size()>0){
				Labor labor = (Labor)list.get(0);
				dept = labor.getDeptnum();
			}
			lookupWnd.setQueryString("version in (select t.version from Budgetitem t where t.enabled = '是') and buditem in(select t.buditem  from Budgetline t where t.budnum in(select b.budnum from Budget b where b.buddept='"+dept+"' and b.enabled ='是'))");
			lookupWnd.getResultList(true);
		}		
		//创建树
		this.createTree();
	}
	
	
	public void createTree()	
	throws Exception
	{
		//清除树，重新构建
		this.getChildren().clear();
		budgetitemSrv = (BudgetitemSrv)IBOSrvUtil.getSrv("budgetitem");
		String whereStr = null;
		if(Util.isNotNull(this.getQueryString()))
		{
			whereStr = this.getQueryString() + " and parent is null and enabled='是'";
		}
		else
		{
			 whereStr = "parent is null and enabled='是'";
		}
		List list = budgetitemSrv.findWithQuery(whereStr,"orderby");
		
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有设置预算项目！"));
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
		
		if(this.getItemCount()>0)
		{
			Treeitem treeitem = (Treeitem)this.getItems().iterator().next();
			this.selectItem(treeitem);
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
		LookupWindow lookupWnd = (LookupWindow) this.getFellow("lookupWnd");
		String deptment = null;
		if(lookupWnd!=null)
		{
			String labornum = lookupWnd.getUserInfo().getLabornum();
			
			List list = budgetitemSrv.findWithQuery(Labor.class,"labornum='"+labornum+"'");
			if(list.size()>0){
				Labor labor = (Labor)list.get(0);
				deptment = labor.getDeptnum();
			}
		}
		Budgetitem parent = (Budgetitem)parentitem.getValue();
		String whereStr = "parent = '"+parent.getBuditem()+"'and version in (select t.version from Budgetitem t where t.enabled = '是') and buditem in(select t.buditem  from Budgetline t where t.budnum in(select b.budnum from Budget b where b.buddept='"+deptment+"' and b.enabled ='是'))";
		List list = budgetitemSrv.findWithQuery(whereStr,"orderby");
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Budgetitem budgetitem = (Budgetitem)list.get(i);
			ti = new Treeitem(budgetitem.getBuditem() + ":" + budgetitem.getDescription()); //
			ti.setValue(budgetitem);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(false);
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
		}
	}
	public void onSelect() throws Exception
	{
		
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public String getType(){
		return this.type;
	}
}
