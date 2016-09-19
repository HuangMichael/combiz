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
	 * ��ȡ�汾��������
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
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
	 * ������
	 * brianhong  2008-11-24
	 * @param version
	 * @throws Exception
	 */
	public void createTree()
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		budgetitemSrv = (BudgetitemSrv)IBOSrvUtil.getSrv("budgetitem");
		String whereStr = "(parent is null or parent='') and enabled='��'";
		List list = budgetitemSrv.findWithQuery(whereStr);
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û������Ԥ����Ŀ����û�����ã�"));
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
			Budgetitem budgetitem = (Budgetitem)list.get(i);
			ti = new Treeitem(budgetitem.getBuditem() + ":" + budgetitem.getDescription());
			ti.setValue(budgetitem);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			
			//����Ҫ����β����ϣ������Զ������ͳ�������
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
	 * ���չ���ڵ�
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
		//*********�ڶ��֣���һ�ε���Ǵ����ݿ���ȡ���ݣ��Ժ�Ͳ�ɾ��Ҳ��ȡ��
		//if(tc.getChildren().size()>0)
		//	return;

		Budgetitem parent = (Budgetitem)parentitem.getValue();
		String whereStr = "parent = '"+parent.getBuditem()+"' and enabled='��'";
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
			//����Ҫ����β����ϣ������Զ������ͳ�������
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
	 * ���չ���ڵ�
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
	 * ���ѡ������
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
