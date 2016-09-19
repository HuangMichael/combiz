package combiz.ui.common.lookup;

import combiz.business.corp.DepartmentSrv;
import combiz.domain.corp.Department;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.ListWindow;
import combiz.system.ui.common.LookupTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindLaborOfDepartmentTree extends LookupTree
{
	DepartmentSrv departmentSrv;
	
	public FindLaborOfDepartmentTree() 
	{
		super();
	}

	public void onCreate() throws Exception
	{
		this.setHeight("300px");
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
		
		departmentSrv = (DepartmentSrv)IBOSrvUtil.getSrv("department");
		/****************************************************
		 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
		 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
		 */
		String whereStr = "parent is null";
		if(this.getTreeQueryString("LABOR")!=null)
			whereStr = whereStr + " and " + this.getTreeQueryString("LABOR");
		/**
		 * 
		 *****************************************************/
		List list = departmentSrv.findWithQuery(whereStr);
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
			ti = new Treeitem(department.getDeptnum());  //+":"+lochiery.getLocation()
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
			
			Department lochparent = (Department)parentitem.getValue();
			/****************************************************
			 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
			 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
			 */
			String whereStr = "parent = '"+lochparent.getDeptnum()+"'";
			if(this.getTreeQueryString("LABOR")!=null)
				whereStr = whereStr + " and " + this.getTreeQueryString("LABOR");
			/*******************************************************/
			List list = departmentSrv.findWithQuery(whereStr);
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Department department = (Department)list.get(i);
				ti = new Treeitem(department.getDeptnum()); //
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
	
	/**
	 * 
	 * @TODO
	 * @throws Exception
	 * @��С��  2007-8-10  ����01:29:16
	 */
	public void onSelect() throws Exception
	{
		Treeitem treeitem = this.getSelectedItem();
		String detpnum = treeitem.getLabel();
		ListWindow mainWnd = (ListWindow)this.getFellow("laborOfDepartment");
		mainWnd.setTitle(detpnum);
		/****************************************************
		 * ������м�����������Աѡ���ʱ����Ҫ�ڸô�������Ա�Ĺ�������this.getQueryString()
		 * 
		 */
		String whereStr = "DEPTNUM='"+detpnum+"'";
		if(this.getQueryString()!=null)
			whereStr = whereStr + " and " + this.getQueryString();
		/**
		 * 
		 *****************************************************/
		mainWnd.setQueryString(whereStr);
		mainWnd.setOrderby("");
		mainWnd.refreshData();
	}
}
