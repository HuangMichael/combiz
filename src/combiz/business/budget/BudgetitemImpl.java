package combiz.business.budget;

import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetitemance;
import combiz.system.IBOBaseImpl;

import java.util.List;

public class BudgetitemImpl extends IBOBaseImpl
implements BudgetitemSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		Budgetitem budgetitem = (Budgetitem)obj;
		
		//�Ƿ�ɾ��������ײ�
		List childList = this.findWithQuery("parent = '"+budgetitem.getBuditem()+"' and version='"+budgetitem.getVersion()+"'");
		if(childList!=null && childList.size()>0)
			throw new Exception("���Ӽ�����ѡ����ײ����ɾ����");
		
		//�޸ĸ�������
		if(budgetitem.getParent()!=null)
		{
			int count = this.getBaseDao().selectCountByWhere(Budgetitem.class, "parent = '"+budgetitem.getParent()
					+"' and version='"+budgetitem.getVersion()+"' and buditem<>'"
					+ budgetitem.getBuditem() + "'");
			if(count<=0)
			{
				List parentList = this.findWithQuery("buditem = '"+budgetitem.getParent()+"' and version='"+budgetitem.getVersion()+"'");
				if(parentList.size()>0)
				{
					Budgetitem parent = (Budgetitem)parentList.get(0);
					parent.setHaschild("��");
					this.update(parent);
				}
			}
		}
		
		//ɾ������
		super.delete(obj);
		//ɾ�����ȱ�����
		this.deleteAllChild(budgetitem, "budgetitemance");
	}
	
	
	/**
	 * ɾ��������汾�����м�¼
	 * brianhong  2008-11-24
	 * @param budgetitem
	 * @throws Exception
	 */
	public void deleteall(Budgetitem budgetitem)
	throws Exception 
	{
		String version = budgetitem.getVersion();
		List objList = this.findWithQuery("version='"+version+"'");
		for(int i=0;i<objList.size();i++)
		{
			//ɾ������
			super.delete(objList.get(i));
			//ɾ�����ȱ�����
			this.deleteAllChild(objList.get(i), "budgetitemance");
		}
			
	}
	
	
	/**
	 * ��������
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Budgetitem budgetitem = (Budgetitem)obj;
		if(budgetitem.getId() == null) //�½�
		{
			//���¸�����¼
			Budgetitem parent = this.getParent(budgetitem);
			if(parent!=null)
			{
				parent.setHaschild("��");
				super.update(parent);
			}
			
			//����classance���ȱ�����
			//�������ȱ�����
			Budgetitemance budgetitemance = new Budgetitemance();
			budgetitemance.setAncestor(budgetitem.getBuditem());
			budgetitemance.setBuditem(budgetitem.getBuditem());
			budgetitemance.setVersion(budgetitem.getVersion());
			super.save(budgetitemance);
			//ѭ����ȡ�������������ȱ�����
			Budgetitem parent2 = this.getParent(budgetitem);
			while(parent2!=null)
			{
				budgetitemance = new Budgetitemance();
				budgetitemance.setBuditem(budgetitem.getBuditem());
				budgetitemance.setAncestor(parent2.getBuditem());
				budgetitemance.setVersion(budgetitem.getVersion());
				super.save(budgetitemance);

				parent2 = this.getParent(parent2);
			}
		}
		
		
		//�����Լ�
		super.save(obj);
	}

	
	/**
	 * ���ư汾����
	 * brianhong  2008-11-24
	 * @param ver
	 */
	public void copyVersion(Budgetitem fromBd,Budgetitem toBd)
	throws Exception 
	{
		List fromList = this.findWithQuery("version='"+fromBd.getVersion()+"'");
		for(int i=0;i<fromList.size();i++)
		{
			Budgetitem budgetitem = (Budgetitem) fromList.get(i);
			Budgetitem newobj = (Budgetitem) this.copyFromObject(budgetitem);
			newobj.setVersion(toBd.getVersion());
			newobj.setEnabled(toBd.getEnabled());
			super.save(newobj);
		}
		fromList = this.findWithQuery(Budgetitemance.class, "version='"+fromBd.getVersion()+"'");
		for(int i=0;i<fromList.size();i++)
		{
			Budgetitemance budgetitemance = (Budgetitemance) fromList.get(i);
			//�������ȱ�����
			Budgetitemance newobj = new Budgetitemance();
			newobj.setAncestor(budgetitemance.getAncestor());
			newobj.setBuditem(budgetitemance.getBuditem());
			newobj.setVersion(toBd.getVersion());
			super.save(newobj);
		}

	}
	
	/**
	 * ��ȡ��������
	 * @param classification
	 * @return
	 * ���ߣ���С�� ���ڣ�2007-6-27
	 */
	public Budgetitem getParent(Budgetitem budgetitem)
	throws Exception 
	{
		List parentList = this.findWithQuery("buditem = '" + budgetitem.getParent() + "' and version='"+budgetitem.getVersion()+"'");
		if(parentList.size()>0)
			return (Budgetitem)parentList.get(0);
		
		return null;
			
	}
}