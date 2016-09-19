package combiz.ui.budget;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

import combiz.domain.budget.Budget;
import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetline;
import combiz.system.ui.TreeListWindow;
import combiz.system.util.Util;

public class BudgetlineList extends TreeListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public BudgetlineList()
	{
		super();
	}
	

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Budget parent = (Budget)this.getOwnerWnd().getMainObject();
		Budgetline parentLine = (Budgetline) this.getSelectObject();

		Budgetline newobj= new Budgetline();
		if(parentLine!=null)
			newobj.setParent(parentLine.getBuditem());
		newobj.setHaschild("��");
		newobj.setEnabled(parent.getEnabled());
		newobj.setBudnum(parent.getBudnum());
		newobj.setBudperiod("���");
		newobj.setChangeby(parent.getChangeby());
		newobj.setBuddept(parent.getBuddept());
		newobj.setChangedate(new Date());

		this.mainObject = newobj;
		return true;
	}


	@Override
	public boolean canRemove() throws Exception
	{
		Budgetline budgetline = (Budgetline)this.getMainObject();
		//�Ƿ�ɾ��������ײ�
		List childList = this.getMainSrv().findWithQuery("budnum='"+budgetline.getBudnum()+"' and parent = '"+budgetline.getBuditem()+"' and version='"+budgetline.getVersion()+"'");
		if(childList!=null && childList.size()>0)
		{
			Messagebox.show("���Ӽ�����ѡ����ײ����ɾ����");
			return false;
		}
		
		return super.canRemove();
	}


	@Override
	public void save() throws Exception
	{
		super.save();
	}
	
	
	
}
