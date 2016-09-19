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
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		/****************************************************
		 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
		 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
		 */
		String whereStr = "parent is null";
		if(Util.isNotNull(this.getQueryString()))
			whereStr = whereStr + " and " + this.getQueryString();
		List list = departmentSrv.findWithQuery(whereStr);
		departmentSrv.setOrderBy("orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�з������ݣ�"));
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
			//����Ҫ����β����ϣ������Զ������ͳ�������
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
	 * ��������ԭʼֵ����
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot(String value)
	throws Exception
	{
		this.getChildren().clear();
		Department department = (Department) departmentSrv.findUniqueBy("deptnum", value);
		if(department==null)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("�����ڲ���["+value+"]��"));
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
		//����Ҫ����β����ϣ������Զ������ͳ�������
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
		//��ײ��tc
		Treechildren bottomTc = new Treechildren();
		bottomTc.appendChild(ti);

		//�ݹ���ô�������
		Treechildren topTc = null;
		Department parent = this.getParent(department);
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
	
	/**
	 * ��ȡλ�ø���
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
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
	
	//��createRoot�������õݹ�
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
			
			Department lochparent = (Department)parentitem.getValue();
			/****************************************************
			 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
			 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
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
				//����Ҫ����β����ϣ������Զ������ͳ�������
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
		else //����ˢ����
		{
			this.createRoot();
		}
	}
}
