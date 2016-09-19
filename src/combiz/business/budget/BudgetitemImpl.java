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
		
		//是否删除的是最底层
		List childList = this.findWithQuery("parent = '"+budgetitem.getBuditem()+"' and version='"+budgetitem.getVersion()+"'");
		if(childList!=null && childList.size()>0)
			throw new Exception("有子集，请选择最底层进行删除！");
		
		//修改父级分类
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
					parent.setHaschild("否");
					this.update(parent);
				}
			}
		}
		
		//删除本身
		super.delete(obj);
		//删除祖先表数据
		this.deleteAllChild(budgetitem, "budgetitemance");
	}
	
	
	/**
	 * 删除主对象版本的所有记录
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
			//删除本身
			super.delete(objList.get(i));
			//删除祖先表数据
			this.deleteAllChild(objList.get(i), "budgetitemance");
		}
			
	}
	
	
	/**
	 * 保存数据
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Budgetitem budgetitem = (Budgetitem)obj;
		if(budgetitem.getId() == null) //新建
		{
			//更新父级记录
			Budgetitem parent = this.getParent(budgetitem);
			if(parent!=null)
			{
				parent.setHaschild("是");
				super.update(parent);
			}
			
			//插入classance祖先表数据
			//产生祖先表数据
			Budgetitemance budgetitemance = new Budgetitemance();
			budgetitemance.setAncestor(budgetitem.getBuditem());
			budgetitemance.setBuditem(budgetitem.getBuditem());
			budgetitemance.setVersion(budgetitem.getVersion());
			super.save(budgetitemance);
			//循环获取父级，产生祖先表数据
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
		
		
		//保存自己
		super.save(obj);
	}

	
	/**
	 * 复制版本数据
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
			//复制祖先表数据
			Budgetitemance newobj = new Budgetitemance();
			newobj.setAncestor(budgetitemance.getAncestor());
			newobj.setBuditem(budgetitemance.getBuditem());
			newobj.setVersion(toBd.getVersion());
			super.save(newobj);
		}

	}
	
	/**
	 * 获取父级对象
	 * @param classification
	 * @return
	 * 作者：洪小林 日期：2007-6-27
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