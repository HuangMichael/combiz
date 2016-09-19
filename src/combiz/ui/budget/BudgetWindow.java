package combiz.ui.budget;

import combiz.business.budget.BudgetSrv;
import combiz.domain.budget.Budget;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.Date;

import com.inbasis.zul.Messagebox;

public class BudgetWindow extends MainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public BudgetWindow()
	{
		super();
	}

	
	/**
	 * 添加记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Budget newobj = new Budget();
		//请在下面添加对象的初始化值
		newobj.setBudcycle("年度");
		newobj.setEnabled("否");
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setBuddept(this.getLaborInfo().getDeptnum());
		newobj.setChangedate(new Date());
		newobj.setStatus("流程未启动");
		newobj.setStatusdate(new Date());
		
		mainObject = newobj;
		return true;
	}
	
	
	
	/**
	 * 初始化数据
	 * 
	 * brianhong  2008-11-26
	 * @throws Exception
	 */
	@Override
	public void initData() throws Exception
	{
		Budget budget = (Budget) this.getMainObject();
		ListWindow listWnd = (ListWindow) this.getFellow("budgetline");
		if(Util.getBoolean(budget.getEnabled()))
		{
			listWnd.setReadonlyList(true);
			this.setReadonlyObject(true);
		}
		
		super.initData();
	}
	
	
	/**
	 * 启用或者禁用预算
	 * brianhong  2008-11-26
	 * @throws Exception
	 */
	public void enablebd()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再点击该菜单操作！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("执行该操作之前，请先保存数据！");
			return;
		}
		
		Budget budget = (Budget) this.getMainObject();
		if(Util.getBoolean(budget.getEnabled()))
		{
			int rtn = Messagebox.show("确定禁用所有预算行吗？","确认！",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
			if(rtn==Messagebox.YES)
			{
				((BudgetSrv)this.getMainSrv()).enablebd(budget, false);
			}
		}
		else
		{
			int rtn = Messagebox.show("确定启用所有预算行吗？","确认！",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
			if(rtn==Messagebox.YES)
			{
				((BudgetSrv)this.getMainSrv()).enablebd(budget, true);
			}
		}
		
		this.refreshData();
	}


	/**
	 * 拷贝预算项目
	 * brianhong  2008-11-21
	 * @throws Exception
	 */
	public void copyitem()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再点击该菜单操作！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("执行该操作之前，请先保存数据！");
			return;
		}
		this.popupDialog(mainObject, "/budget/selectbudgetitem.xul");
	}
	
	
	/**
	 * 删除所有预算行
	 * brianhong  2008-11-24
	 */
	public void deleteall()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再点击该菜单操作！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("执行该操作之前，请先保存数据！");
			return;
		}
		
		int rtn = Messagebox.show("确定删除该预算审批单的所有预算行吗？","删除确认！",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
		if(rtn==Messagebox.YES)
		{
			((BudgetSrv)this.getMainSrv()).deleteAllLine(this.getMainObject());
		}
			
		this.refreshData();
	}


	@Override
	public void save() throws Exception
	{
		super.save();
	}
	
	
	
	
}
