package combiz.ui.budget;

import combiz.domain.budget.Budgetline;
import combiz.domain.ibs.Ibsglobal;
import combiz.system.FieldAdapter;
import combiz.system.IBSServer;

import com.inbasis.zk.ui.Component;

public class FldBudgetFeed
extends FieldAdapter 
{
	
	
	@Override
	public Object initValue() throws Exception
	{
		Object budfeed = 0.0D;
		String navpage = null;
		Budgetline bud = (Budgetline) this.getMainObject();	
		Ibsglobal ibsglobal = (Ibsglobal) IBSServer.getIBSServer().getIbsglobalinfo().get("index_budget");
		if(ibsglobal!=null)
		navpage = ibsglobal.getIbsvalue();
		if(navpage.equals("�ɹ�")){		
		//Ԥ�����
		//Double budget = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.budget) from Budgetline t where  t.budnum = '"+bud.getBudnum()+"'");
		//ʵ�ʽ��շ���
		Double sumbudget = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.linecost) from Invrectrans t where  t.budnum = '"+bud.getBudnum()+"'and t.buditem ='"+bud.getBuditem()+"' and t.rectype ='����' and t.status ='�Ѽ���'");
		/*if(budget == null)
			budget = 0d;*/
		if(sumbudget == null)
			sumbudget = 0d;
		budfeed = sumbudget;
		}else{
		//���ݷ���
		Double fafang = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.linecost) from Invrectrans t where  t.budnum = '"+bud.getBudnum()+"'and t.buditem ='"+bud.getBuditem()+"' and t.rectype ='����' and t.status ='�Ѽ���'");
		/*if(budget == null)
			budget = 0d;*/
		if(fafang == null)
			fafang = 0d;
		budfeed = fafang;		
		}
		return budfeed;
	}

	@Override
	public void action(Component component) 
	throws Exception 
	{
	}

	@Override
	public void init(Component component)
	throws Exception 
	{
		
	}

	@Override
	public void validate(Component component) 
	throws Exception
	{
	}
}
