package combiz.business.corp;

import combiz.domain.corp.Department;
import combiz.domain.corp.Deptance;
import combiz.domain.corp.Labor;
import combiz.domain.ibs.Ibstables;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.TableInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class DepartmentImpl extends IBOBaseImpl
implements DepartmentSrv {


	/**
	 * 删除部门，同时删除部门关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj)
	throws Exception 
	{
		if(!(obj instanceof Department))
			throw new Exception("要删除的对象传递不正确！");
		
		Department department = (Department)obj;
		
		//是否删除的是最底层
		List childList = this.findWithQuery("parent = '"+department.getDeptnum()+"'");
		if(childList!=null && childList.size()>0)
			throw new Exception("有子集分类，请选择最底层分类进行删除！");
		
		//修改父级分类
		if(department.getParent()!=null)
		{
			List sameParentList = this.findWithQuery("parent = '"+department.getParent()+"' and deptnum<>'"
					+ department.getDeptnum() + "'");
			if(sameParentList == null || sameParentList.size()<=0)
			{
				List parentList = this.findWithQuery("deptnum = '"+department.getParent()+"'");
				if(parentList!=null && parentList.size()>0)
				{
					Department parent = (Department)parentList.get(0);
					parent.setHaschild("否");
					this.update(parent);
				}
			}
		}
		
		/**删除子集
		List childList = this.findWithQuery("parent = '"+classification.getClassid()+"'");
		if(childList!=null && childList.size()>0)
		{
			for(int i=0; i<childList.size(); i++)
			{
				this.deleteChild(childList.get(i));
			}
		}
		**/
		
		//删除本身
		super.delete(obj);
		//删除祖先表数据
		this.deleteAllChild(department, "deptance");
	}

	/**
	 * 删除子集
	 * @param parent
	 * @throws Exception
	 * 作者：洪小林 日期：2007-6-26
	
	private void deleteChild(Object parent)
	throws Exception
	{
		Classification classification = (Classification)parent;
		List childList = this.findWithQuery("parent = '"+classification.getClassid()+"'");
		if(childList!=null && childList.size()>0)
		{
			for(int i=0; i<childList.size(); i++)
			{
				this.deleteChild(childList.get(i));
			}
		}
		
		this.delete(classification);
	}
	 */

	/**
	 * 保存数据
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Department department = (Department)obj;
		if(department.getId() == null) //新建
		{
			//更新父级记录
			Department parent = this.getParent(department);
			if(parent!=null)
			{
				parent.setHaschild("是");
				super.update(parent);
			}
			
			//插入classance祖先表数据
			//产生祖先表数据
			Deptance deptance = new Deptance();
			deptance.setDeptance(department.getDeptnum());
			deptance.setDeptnum(department.getDeptnum());
			this.getBaseDao().saveObject(deptance);
			//循环获取父级，产生祖先表数据
			Department depart = this.getParent(department);
			while(depart!=null)
			{
				deptance = new Deptance();
				deptance.setDeptnum(department.getDeptnum());
				deptance.setDeptance(depart.getDeptnum());
				this.getBaseDao().saveObject(deptance);

				depart = this.getParent(depart);
			}
		}
		else
			super.update(department);
		
		
		//保存自己
		super.save(obj);
	}

	
	/**
	 * 获取父级对象
	 * @param classification
	 * @return
	 * 作者：洪小林 日期：2007-6-27
	 */
	public Department getParent(Department department)
	throws Exception 
	{
		List parentList = this.findWithQuery("deptnum = '" + department.getParent() + "'");
		if(parentList.size()>0)
			return (Department)parentList.get(0);
		
		return null;
	}
	
	
	/**
	 * 部门调整
	 * brianhong  2009-10-12
	 * @param department
	 * @param to_deptnum
	 * @throws Exception
	 */
	public void deptchg(Department department,String orgParent)
	throws Exception
	{
		List chldList = this.getBaseDao().findWithQuery(Deptance.class, 
				"deptance='"+department.getDeptnum()+"'");
		
		for(int i=0;i<chldList.size();i++)
		{
			Deptance dpt = (Deptance) chldList.get(i);
			String chldDept = dpt.getDeptnum();
			//删除祖先表记录
			List delList = this.getBaseDao().findWithQuery(Deptance.class, "deptnum='"+chldDept+"'");
			for(int j=0;j<delList.size();j++)
			{
				this.getBaseDao().deleteObject(delList.get(j));
			}
		}
		
		for(int i=0;i<chldList.size();i++)
		{
			Deptance dpt = (Deptance) chldList.get(i);
			String chldDept = dpt.getDeptnum();
			
			//更新父级记录
			Department chldDepartment;
			if(chldDept.equals(department.getDeptnum()))
			{
				chldDepartment = department;
				//更新自己
				this.update(department);
			}
			else
			{
				chldDepartment = (Department) this.getBaseDao().findUniqueBy(Department.class, "deptnum", chldDept);
			}
			Department parent = this.getParent(chldDepartment);
			if(parent!=null)
			{
				parent.setHaschild("是");
				super.update(parent);
			}
			
			//插入classance祖先表数据
			//产生祖先表数据
			Deptance deptance = new Deptance();
			deptance.setDeptance(chldDept);
			deptance.setDeptnum(chldDept);
			super.save(deptance);
			//循环获取父级，产生祖先表数据
			Department depart = this.getParent(chldDepartment);
			while(depart!=null)
			{
				deptance = new Deptance();
				deptance.setDeptnum(chldDept);
				deptance.setDeptance(depart.getDeptnum());
				super.save(deptance);

				depart = this.getParent(depart);
			}
		}
		
		

	}
	
	/**
	 * 修改部门编码
	 * brianhong  2009-10-12
	 * @param department
	 * @param to_deptnum
	 * @throws Exception
	 */
	public void deptnumchg(Department department,String to_deptnum)
	throws Exception 
	{
		//更新祖先表
		List dptList = this.findWithQuery(Deptance.class, "deptnum='"+department.getDeptnum()+"'");
		for(int i=0;i<dptList.size();i++)
		{
			Deptance dpt = (Deptance) dptList.get(i);
			dpt.setDeptnum(to_deptnum);
			this.getBaseDao().updateObject(dpt);
		}
		dptList = this.findWithQuery(Deptance.class, "deptance='"+department.getDeptnum()+"'");
		for(int i=0;i<dptList.size();i++)
		{
			Deptance dpt = (Deptance) dptList.get(i);
			dpt.setDeptance(to_deptnum);
			this.getBaseDao().updateObject(dpt);
		}
		
		//更新业务数据
		HashMap ibstablesinfo = IBSServer.getIBSServer().getIbstablesinfo();
		Iterator itor = ibstablesinfo.values().iterator();
		while(itor.hasNext())
		{
			TableInfo tableinfo = (TableInfo) itor.next();
			Ibstables ibstables = tableinfo.getIbstables();
			if(ibstables.getAuthlevel().equals("组织级"))
			{
				String updatesql = "update "+ibstables.getTablename().toLowerCase()+" set sitenum='"+to_deptnum+"' where sitenum='"+department.getDeptnum()+"'";
				this.getBaseDao().executeSql(updatesql);
			}
		}
		
		//更新人员表中的人员部门信息
		List laborList = this.findWithQuery(Labor.class, "deptnum='"+department.getDeptnum()+"'");
		for(int i=0;i<laborList.size();i++)
		{
			Labor labor = (Labor) laborList.get(i);
			labor.setDeptnum(to_deptnum);
			this.getBaseDao().updateObject(labor);
		}
		
		
		//更新自己
		department.setDeptnum(to_deptnum);
		this.update(department);
	}
	
	
	
	
}	
