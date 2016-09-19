package combiz.business.budget;

import combiz.domain.budget.Budgetline;
import combiz.system.IBOBaseImpl;

import java.util.List;

public class BudgetlineImpl extends IBOBaseImpl
implements BudgetlineSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		Budgetline budgetline = (Budgetline)obj;

		//修改父级分类
		if(budgetline.getParent()!=null)
		{
			int count = this.getBaseDao().selectCountByWhere(Budgetline.class, "budnum='"+budgetline.getBudnum()+"' and parent = '"+budgetline.getParent()+"' and buditem<>'"
					+ budgetline.getBuditem() + "' and version='"+budgetline.getVersion()+"'");
			if(count<=0)
			{
				List parentList = this.findWithQuery("budnum='"+budgetline.getBudnum()+"' and buditem = '"+budgetline.getParent()+"' and version='"+budgetline.getVersion()+"'");
				if(parentList!=null && parentList.size()>0)
				{
					Budgetline parent = (Budgetline)parentList.get(0);
					parent.setHaschild("否");
					super.save(parent);
				}
			}
		}
		
		//计算父级预算合计
		Double budgetDB = (Double) this.getBaseDao().selectSumByHql("select sum(t.budget) from Budgetline t where t.budnum='"
				+ budgetline.getBudnum() +"' and t.buditem='"+budgetline.getBuditem()+"'" + " and t.version='"+budgetline.getVersion()+"'");
		double curbudget = 0,budgetdb = 0;
		if(budgetDB!=null)
			this.updateParentBudget(budgetline, 0, budgetDB.doubleValue());
		
		//删除自己
		super.delete(obj);
	}
	
	
	/**
	 * 保存数据
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Budgetline budgetline = (Budgetline)obj;
		if(budgetline.getId() == null) //新建
		{
			//更新父级记录
			Budgetline parent = this.getParent(budgetline);
			if(parent!=null)
			{
				parent.setHaschild("是");
				super.update(parent);
			}
		}
		
		//计算父级预算合计
		Double budgetDB = (Double) this.getBaseDao().selectSumByHql("select sum(t.budget) from Budgetline t where t.budnum='"
				+ budgetline.getBudnum()+"' and t.buditem='"+budgetline.getBuditem()+"' and t.version='"+budgetline.getVersion()+"'");
		double curbudget = 0,budgetdb = 0;
		if(budgetline.getBudget()!=null)
			curbudget = budgetline.getBudget().doubleValue();
		if(budgetDB!= null)
			budgetdb = budgetDB.doubleValue();
		if(curbudget!=budgetdb)	//修改数据了
			this.updateParentBudget(budgetline,curbudget,budgetdb);
		
		//保存自己
		super.save(obj);
	}
	
	/**
	 * 更新父级预算费用
	 * brianhong  2008-11-21
	 * @param budgetline
	 * @throws Exception
	 */
	private void updateParentBudget(Budgetline budgetline,double curbudget,double budgetdb)
	throws Exception
	{
		Budgetline parent = this.getParent(budgetline);
		if(parent!=null)
		{
			double parentBd = (parent.getBudget()==null?0:parent.getBudget().doubleValue());
			parent.setBudget(parentBd - budgetdb + curbudget);
			super.save(parent);
			//递归更新父级
			this.updateParentBudget(parent, curbudget, budgetdb);
		}
	}
	
	
	/**
	 * 获取父级对象
	 * @param classification
	 * @return
	 * 作者：洪小林 日期：2007-6-27
	 */
	public Budgetline getParent(Budgetline budgetline)
	throws Exception 
	{
		List parentList = this.findWithQuery("budnum='"+budgetline.getBudnum()+"' and buditem = '" + budgetline.getParent() + "' and version='"+budgetline.getVersion()+"'");
		if(parentList.size()>0)
			return (Budgetline)parentList.get(0);
		
		return null;
			
	}
}