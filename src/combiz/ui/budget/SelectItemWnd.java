package combiz.ui.budget;

import combiz.business.budget.BudgetSrv;
import combiz.domain.budget.Budget;
import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetitemance;
import combiz.domain.budget.Budgetline;
import combiz.system.IBOBaseDao;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.RecWindow;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class SelectItemWnd extends CommonDialog
{

	/**
	 * 生成预算行记录
	 * brianhong  2008-11-24
	 */
	public void genBudgetLine()
	throws Exception
	{
		RecWindow ownerWnd = (RecWindow) this.getOwnerWnd();
		Budget budget = (Budget) this.getMainObject();
		
		SelectBudgetItemTree selectBudgetItemTree = (SelectBudgetItemTree) this.getFellow("selectBudgetItemTree");
		Iterator iterator = selectBudgetItemTree.getSelectedItems().iterator();
		String ver = null;
		String itemStr = null;
		int i=0;
		while(iterator.hasNext())
		{
			Treeitem treeitem = (Treeitem) iterator.next();
			Budgetitem budgetitem = (Budgetitem) treeitem.getValue();
			if(budgetitem!=null)
			{
				ver = budgetitem.getVersion();
				if(itemStr==null)
					itemStr = "'" + budgetitem.getBuditem() + "'";
				else
					itemStr = itemStr + ",'" + budgetitem.getBuditem() + "'";
				i++;
			}
		}
		if(i<=0)
		{
			Messagebox.show("请选择有效的预算行！");
			return;
		}
		
		//版本是否一致
		int count = ownerWnd.getMainSrv().getBaseDao().selectCountByHql("select count(t.buditem) from Budgetline t where t.budnum='"
				+budget.getBudnum()+"' and version!='"+ver+"'");
		if(count>0)
		{
			Messagebox.show("预算行中已经存在了其他版本的预算项目，请选择正确的版本或者删除已有的预算行！");
			return;
		}
		((BudgetSrv)ownerWnd.getMainSrv()).genBudgetLine(budget, itemStr, ver);
		
		this.onClose();
		ownerWnd.refreshData();
		Messagebox.show("已经为选择的预算项目生成预算行！");
		
	}
}
