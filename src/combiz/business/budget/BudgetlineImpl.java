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

		//�޸ĸ�������
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
					parent.setHaschild("��");
					super.save(parent);
				}
			}
		}
		
		//���㸸��Ԥ��ϼ�
		Double budgetDB = (Double) this.getBaseDao().selectSumByHql("select sum(t.budget) from Budgetline t where t.budnum='"
				+ budgetline.getBudnum() +"' and t.buditem='"+budgetline.getBuditem()+"'" + " and t.version='"+budgetline.getVersion()+"'");
		double curbudget = 0,budgetdb = 0;
		if(budgetDB!=null)
			this.updateParentBudget(budgetline, 0, budgetDB.doubleValue());
		
		//ɾ���Լ�
		super.delete(obj);
	}
	
	
	/**
	 * ��������
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Budgetline budgetline = (Budgetline)obj;
		if(budgetline.getId() == null) //�½�
		{
			//���¸�����¼
			Budgetline parent = this.getParent(budgetline);
			if(parent!=null)
			{
				parent.setHaschild("��");
				super.update(parent);
			}
		}
		
		//���㸸��Ԥ��ϼ�
		Double budgetDB = (Double) this.getBaseDao().selectSumByHql("select sum(t.budget) from Budgetline t where t.budnum='"
				+ budgetline.getBudnum()+"' and t.buditem='"+budgetline.getBuditem()+"' and t.version='"+budgetline.getVersion()+"'");
		double curbudget = 0,budgetdb = 0;
		if(budgetline.getBudget()!=null)
			curbudget = budgetline.getBudget().doubleValue();
		if(budgetDB!= null)
			budgetdb = budgetDB.doubleValue();
		if(curbudget!=budgetdb)	//�޸�������
			this.updateParentBudget(budgetline,curbudget,budgetdb);
		
		//�����Լ�
		super.save(obj);
	}
	
	/**
	 * ���¸���Ԥ�����
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
			//�ݹ���¸���
			this.updateParentBudget(parent, curbudget, budgetdb);
		}
	}
	
	
	/**
	 * ��ȡ��������
	 * @param classification
	 * @return
	 * ���ߣ���С�� ���ڣ�2007-6-27
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