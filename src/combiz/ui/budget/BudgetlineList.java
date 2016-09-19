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
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public BudgetlineList()
	{
		super();
	}
	

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Budget parent = (Budget)this.getOwnerWnd().getMainObject();
		Budgetline parentLine = (Budgetline) this.getSelectObject();

		Budgetline newobj= new Budgetline();
		if(parentLine!=null)
			newobj.setParent(parentLine.getBuditem());
		newobj.setHaschild("否");
		newobj.setEnabled(parent.getEnabled());
		newobj.setBudnum(parent.getBudnum());
		newobj.setBudperiod("年度");
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
		//是否删除的是最底层
		List childList = this.getMainSrv().findWithQuery("budnum='"+budgetline.getBudnum()+"' and parent = '"+budgetline.getBuditem()+"' and version='"+budgetline.getVersion()+"'");
		if(childList!=null && childList.size()>0)
		{
			Messagebox.show("有子集，请选择最底层进行删除！");
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
