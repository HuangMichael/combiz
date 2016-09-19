package combiz.ui.common.lookup;

import combiz.business.corp.DepartmentSrv;
import combiz.domain.corp.Department;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.common.LookupTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindDepartmentTree extends LookupTree
{
	DepartmentSrv departmentSrv;
	
	public FindDepartmentTree() 
	{
		super();
	}

	public void onCreate() throws Exception
	{
		this.setHeight("300px");
		this.setVflex(true);
		
		departmentSrv = (DepartmentSrv)IBOSrvUtil.getSrv("department");
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
		
		/****************************************************
		 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
		 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
		 */
		String whereStr = "parent is null";
		if(Util.isNotNull(this.getQueryString()))
			whereStr = whereStr + " and " + this.getQueryString();
		List list = departmentSrv.findWithQuery(whereStr);
		departmentSrv.setOrderBy("orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有分类数据！"));
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
			Department department = (Department)list.get(i);
			ti = new Treeitem(department.getDescription()+"["+department.getDeptnum()+"]");
			ti.setValue(department);
			if(Util.getBoolean(department.getIscrewid()))
				ti.setImage("/images/nav_parent.gif");
			else
				ti.setImage("/images/node_image_dept.gif");
			ti.setOpen(true);
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(department.getHaschild()))
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
	 * 创建带有原始值的树
	 * @throws Exception
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void createRoot(String value)
	throws Exception
	{
		this.getChildren().clear();
		Department department = (Department) departmentSrv.findUniqueBy("deptnum", value);
		if(department==null)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("不存在部门["+value+"]！"));
			this.appendChild(tc);
			return;
		}

		Treeitem ti = new Treeitem(department.getDescription()+"["+department.getDeptnum()+"]");
		ti.setValue(department);
		if(Util.getBoolean(department.getIscrewid()))
			ti.setImage("/images/nav_parent.gif");
		else
			ti.setImage("/images/node_image_dept.gif");
		ti.setOpen(true);
		//很重要！这段不加上，树的自动滚动就出不来。
		Treerow treerow = (Treerow)ti.getChildren().get(0);
		Treecell treecell = (Treecell)treerow.getChildren().get(0);
		treecell.setStyle("white-space:nowrap;");
		//////////////////////////////////////////////
		if(Util.getBoolean(department.getHaschild()))
		{
			Treechildren nextChild = new Treechildren();
			ti.appendChild(nextChild);
			ti.addEventListener("onOpen", new openNode());
			this.expand(ti);
		}
		//最底层的tc
		Treechildren bottomTc = new Treechildren();
		bottomTc.appendChild(ti);

		//递归调用创建父级
		Treechildren topTc = null;
		Department parent = this.getParent(department);
		while(parent!=null)
		{
			topTc = this.createUpItem(parent, bottomTc);
			parent = this.getParent(parent);
			bottomTc = topTc;
		}

		//最顶层的tc
		if(topTc==null)
			topTc = bottomTc;
		this.appendChild(topTc);
		this.selectItem(ti);
	}
	
	/**
	 * 获取位置父级
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	private Department getParent(Department department)
	throws Exception
	{
		List parentList = departmentSrv.findWithQuery("deptnum = '" + department.getParent() + "'");
		if (parentList.size() > 0)
		{
			Department parent = (Department) parentList.get(0);
			return parent;
		}
		return null;
	}
	
	//由createRoot方法调用递归
	private Treechildren createUpItem(Department department, Treechildren childTc)
	throws Exception
	{
		Treechildren tc = new Treechildren();
		Treeitem ti = new Treeitem(department.getDescription()+"["+department.getDeptnum()+"]");
		ti.setValue(department);
		if(Util.getBoolean(department.getIscrewid()))
			ti.setImage("/images/nav_parent.gif");
		else
			ti.setImage("/images/node_image_dept.gif");
		ti.setOpen(true);
		//很重要！这段不加上，树的自动滚动就出不来。
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
			
			Department lochparent = (Department)parentitem.getValue();
			/****************************************************
			 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
			 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
			 */
			String whereStr = "parent = '"+lochparent.getDeptnum()+"'";
			if(this.getQueryString()!=null)
				whereStr = whereStr + " and " + this.getQueryString();
			List list = departmentSrv.findWithQuery(whereStr);
			departmentSrv.setOrderBy("orderby");
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Department department = (Department)list.get(i);
				ti = new Treeitem(department.getDescription()+"["+department.getDeptnum()+"]");
				ti.setValue(department);
				if(Util.getBoolean(department.getIscrewid()))
					ti.setImage("/images/nav_parent.gif");
				else
					ti.setImage("/images/node_image_dept.gif");
				ti.setOpen(false);
				//很重要！这段不加上，树的自动滚动就出不来。
				Treerow treerow = (Treerow)ti.getChildren().get(0);
				Treecell treecell = (Treecell)treerow.getChildren().get(0);
				treecell.setStyle("white-space:nowrap;");
				//////////////////////////////////////////////
				if(Util.getBoolean(department.getHaschild()))
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
}
