package combiz.ui.corp;

import combiz.business.corp.DepartmentSrv;
import combiz.domain.corp.Corporation;
import combiz.domain.corp.Department;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.MainWindow;
import combiz.system.ui.TopWindow;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zk.ui.UiException;
import com.inbasis.zk.ui.event.Event;
import com.inbasis.zul.Tree;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

/**
 * 
 * @author brianhong
 *
 */
public class DeptAuthCustTree extends Tree
{
	DepartmentSrv departmentSrv;
	MainWindow mainWnd;
	
	public DeptAuthCustTree() 
	{
		super();
		this.setId("deptAuthCustTree_tree");
		this.setMultiple(true);
		this.setCheckmark(true);
	}


	
	/**
	 * 
	 * 
	 * ���ߣ���С�� ���ڣ�2007-6-6
	 * @throws Exception 
	 */
	public void onCreate()
	throws Exception
	{
		this.setVflex(true);
	
		Page topPage = this.getDesktop().getPage("topPage");
		TopWindow topWnd = (TopWindow) topPage.getFellow("topWnd");
		this.setHeight((topWnd.getDesktopHeight() - 180) +"px");
	}
	
	
	/**
	 * 
	 * 
	 * ���ߣ���С�� ���ڣ�2007-6-6
	 * @throws Exception 
	 */
	public void createNavTree() 
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		mainWnd = (MainWindow) this.getFellow("mainWnd");
		Corporation corporation = (Corporation) mainWnd.getMainObject();
		departmentSrv = (DepartmentSrv)IBOSrvUtil.getSrv("department");
		departmentSrv.setOrderBy("orderby");
		List list = departmentSrv.findWithQuery("parent is null");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			this.appendChild(tc);
			return;
		}
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Department department = (Department)list.get(i);
			ti = new Treeitem(department.getDescription());  // + "[" + department.getDeptnum() + "]"
			ti.setValue(department);
			ti.setAttribute("type", "department");
			if(Util.getBoolean(department.getIscrewid()))
				ti.setImage("/images/nav_parent.gif");
			//else
			//	ti.setImage("/images/node_image_dept.gif");
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
		
	}
	
	
	/**
	 * չ�����ڵ�
	 * @param parentitem
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void expand(Treeitem parentitem)
	throws Exception
	{
		//*********��һ�֣�ÿ�ζ�ɾ�������´����ݿ���ȡ��
		Treechildren tc = parentitem.getTreechildren();
		if(tc!=null && tc.getChildren().size()>0)
			tc.getChildren().clear();

		Department parent = (Department)parentitem.getValue();
		//�г��Ӳ���
		List list = departmentSrv.findWithQuery("parent = '"+parent.getDeptnum()+"'");
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Department department = (Department)list.get(i);
			ti = new Treeitem(department.getDescription());  // + "[" + department.getDeptnum() + "]"
			ti.setValue(department);
			ti.setAttribute("type", "department");
			if(Util.getBoolean(department.getIscrewid()))
				ti.setImage("/images/nav_parent.gif");
			//else
			//	ti.setImage("/images/node_image_dept.gif");
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
			}
			tc.appendChild(ti);
			//�ݹ�������Ӽ�
			if(Util.getBoolean(department.getHaschild()))
			{
				this.expand(ti);
			}
		}
	}
	
	
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
	 * 
	 * @TODO ѡ���¼�
	 * @throws Exception
	 * @��С��  2007-8-16  ����03:44:11
	 */
	public void onSelect()
	throws Exception
	{
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////
	/**
	 *����
	 */
	private class NullClick
	{
		private void onEvent(Event event) throws UiException 
		{
			try {
			} catch (Exception e) {
				try {
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		private boolean isAsap() 
		{
			return true;
		}
	}
}
