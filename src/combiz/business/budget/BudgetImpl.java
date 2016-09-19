package combiz.business.budget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import combiz.domain.budget.*;
import combiz.system.IBOBaseDao;
import combiz.system.IBOBaseImpl;

public class BudgetImpl extends IBOBaseImpl
implements BudgetSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		this.deleteAllChild(obj, "budgetlineall");
		super.delete(obj);
	}
	
	/**
	 * ����Ԥ���м�¼
	 * brianhong  2008-11-24
	 */
	public void genBudgetLine(Budget budget,String itemStr,String ver)
	throws Exception 
	{
		//����ѡ�м�¼�����и���
		List buditemList = new ArrayList();
		List anceList = this.getBaseDao().selectListByHql("select distinct t.ancestor from Budgetitemance t where t.buditem in("+itemStr+") and t.version='"+ver+"' order by t.ancestor");
		for(int i=0;i<anceList.size();i++)
		{
			String itemance = (String) anceList.get(i);
			List itemList = this.getBaseDao().findWithQuery(Budgetitem.class, "buditem='"+itemance+"' and version='"+ver+"'");
			if(itemList.size()>0)
			{
				Budgetitem budgetitem = (Budgetitem) itemList.get(0);
				//�ж��Ƿ���ڼ�¼
				buditemList.add(budgetitem.getBuditem());
				//�ж����ݿ����Ƿ����
				Budgetline newobj = new Budgetline();
				int existCount = this.getRowCountByWhere(newobj,
						"budnum='"+budget.getBudnum()+"' and buditem='"+budgetitem.getBuditem()+"' and version='"+budgetitem.getVersion()+"'");
				if(existCount>0)
					continue;
				
				newobj.setBudnum(budget.getBudnum());
				newobj.setBuditem(budgetitem.getBuditem());
				newobj.setVersion(budgetitem.getVersion());
				newobj.setParent(budgetitem.getParent());
				newobj.setHaschild(budgetitem.getHaschild());
				newobj.setEnabled(budget.getEnabled());
				newobj.setChangeby(budget.getChangeby());
				newobj.setChangedate(new Date());
				newobj.setBuddept(budget.getBuddept());
				newobj.setBudclass(budgetitem.getBudclass());
				//newobj.setBudget(budget.getBudget());
				//newobj.setBudfeed();
				newobj.setBudperiod(budgetitem.getBudperiod());
				newobj.setBudtype(budgetitem.getBudtype());
				newobj.setChildclass(budgetitem.getChildclass());
				newobj.setDescription(budgetitem.getDescription());
				newobj.setMeaunit(budgetitem.getMeaunit());
				newobj.setOrderby(budgetitem.getOrderby());
				newobj.setRemark(budgetitem.getRemark());
				newobj.setSitenum(budget.getSitenum());
				newobj.setCorpnum(budget.getCorpnum());
				
				super.save(newobj);
			}
		}
		
		//����ѡ�м�¼�������Ӽ���¼
		List childList = this.getBaseDao().selectListByHql("select distinct t.buditem from Budgetitemance t where t.ancestor in("+itemStr+") and t.version='"+ver+"' and t.buditem<>t.ancestor order by t.buditem");
		for(int i=0;i<childList.size();i++)
		{
			String buditem = (String) childList.get(i);
			List itemList = this.getBaseDao().findWithQuery(Budgetitem.class, "buditem='"+buditem+"' and version='"+ver+"'");
			if(itemList.size()>0)
			{
				Budgetitem budgetitem = (Budgetitem) itemList.get(0);
				//�ж��Ƿ���ڼ�¼
				//�������Ѿ������˸�����
				if(buditemList.contains(budgetitem.getBuditem()))
					continue;
				else
					buditemList.add(budgetitem.getBuditem());
				//��֤���ݿ����Ƿ��Ѿ�����
				Budgetline newobj = new Budgetline();
				int existCount = this.getRowCountByWhere(newobj,
						"budnum='"+budget.getBudnum()+"' and buditem='"+budgetitem.getBuditem()+"' and version='"+budgetitem.getVersion()+"'");
				if(existCount>0)
					continue;
				
				newobj.setBudnum(budget.getBudnum());
				newobj.setBuditem(budgetitem.getBuditem());
				newobj.setVersion(budgetitem.getVersion());
				newobj.setParent(budgetitem.getParent());
				newobj.setHaschild(budgetitem.getHaschild());
				newobj.setEnabled(budget.getEnabled());
				newobj.setChangeby(budget.getChangeby());
				newobj.setChangedate(new Date());
				newobj.setBuddept(budget.getBuddept());
				newobj.setBudclass(budgetitem.getBudclass());
				//newobj.setBudget(budget.getBudget());
				//newobj.setBudfeed();
				newobj.setBudperiod(budgetitem.getBudperiod());
				newobj.setBudtype(budgetitem.getBudtype());
				newobj.setChildclass(budgetitem.getChildclass());
				newobj.setCorpnum(budget.getCorpnum());
				newobj.setDescription(budgetitem.getDescription());
				newobj.setMeaunit(budgetitem.getMeaunit());
				newobj.setOrderby(budgetitem.getOrderby());
				newobj.setRemark(budgetitem.getRemark());
				newobj.setSitenum(budget.getSitenum());
				
				super.save(newobj);
			}
		}
	}
	
	/**
	 * ���û��߽���Ԥ����Ŀ
	 * brianhong  2008-11-26
	 * @param obj
	 * @param enable
	 * @throws Exception
	 */
	public void enablebd(Object obj,boolean enable)
	throws Exception
	{
		Budget budget = (Budget) obj;
		if(enable)
		{
			budget.setEnabled("��");
			this.getBaseDao().executeSql("update budgetline set enabled='��' where budnum='"+budget.getBudnum()+"'");
			
			this.save(budget);
		}
		else
		{
			budget.setEnabled("��");
			this.getBaseDao().executeSql("update budgetline set enabled='��' where budnum='"+budget.getBudnum()+"'");
			this.save(budget);
		}
	}
	
	/**
	 *  ɾ������Ԥ����
	 * brianhong  2008-11-24
	 * @throws Exception
	 */
	public void deleteAllLine(Object obj)
	throws Exception 
	{
		this.deleteAllChild(obj, "budgetlineall");
	}
	
}