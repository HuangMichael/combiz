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
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class DepartmentImpl extends IBOBaseImpl
implements DepartmentSrv {


	/**
	 * ɾ�����ţ�ͬʱɾ�����Ź����û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj)
	throws Exception 
	{
		if(!(obj instanceof Department))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		Department department = (Department)obj;
		
		//�Ƿ�ɾ��������ײ�
		List childList = this.findWithQuery("parent = '"+department.getDeptnum()+"'");
		if(childList!=null && childList.size()>0)
			throw new Exception("���Ӽ����࣬��ѡ����ײ�������ɾ����");
		
		//�޸ĸ�������
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
					parent.setHaschild("��");
					this.update(parent);
				}
			}
		}
		
		/**ɾ���Ӽ�
		List childList = this.findWithQuery("parent = '"+classification.getClassid()+"'");
		if(childList!=null && childList.size()>0)
		{
			for(int i=0; i<childList.size(); i++)
			{
				this.deleteChild(childList.get(i));
			}
		}
		**/
		
		//ɾ������
		super.delete(obj);
		//ɾ�����ȱ�����
		this.deleteAllChild(department, "deptance");
	}

	/**
	 * ɾ���Ӽ�
	 * @param parent
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-6-26
	
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
	 * ��������
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Department department = (Department)obj;
		if(department.getId() == null) //�½�
		{
			//���¸�����¼
			Department parent = this.getParent(department);
			if(parent!=null)
			{
				parent.setHaschild("��");
				super.update(parent);
			}
			
			//����classance���ȱ�����
			//�������ȱ�����
			Deptance deptance = new Deptance();
			deptance.setDeptance(department.getDeptnum());
			deptance.setDeptnum(department.getDeptnum());
			this.getBaseDao().saveObject(deptance);
			//ѭ����ȡ�������������ȱ�����
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
		
		
		//�����Լ�
		super.save(obj);
	}

	
	/**
	 * ��ȡ��������
	 * @param classification
	 * @return
	 * ���ߣ���С�� ���ڣ�2007-6-27
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
	 * ���ŵ���
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
			//ɾ�����ȱ��¼
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
			
			//���¸�����¼
			Department chldDepartment;
			if(chldDept.equals(department.getDeptnum()))
			{
				chldDepartment = department;
				//�����Լ�
				this.update(department);
			}
			else
			{
				chldDepartment = (Department) this.getBaseDao().findUniqueBy(Department.class, "deptnum", chldDept);
			}
			Department parent = this.getParent(chldDepartment);
			if(parent!=null)
			{
				parent.setHaschild("��");
				super.update(parent);
			}
			
			//����classance���ȱ�����
			//�������ȱ�����
			Deptance deptance = new Deptance();
			deptance.setDeptance(chldDept);
			deptance.setDeptnum(chldDept);
			super.save(deptance);
			//ѭ����ȡ�������������ȱ�����
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
	 * �޸Ĳ��ű���
	 * brianhong  2009-10-12
	 * @param department
	 * @param to_deptnum
	 * @throws Exception
	 */
	public void deptnumchg(Department department,String to_deptnum)
	throws Exception 
	{
		//�������ȱ�
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
		
		//����ҵ������
		HashMap ibstablesinfo = IBSServer.getIBSServer().getIbstablesinfo();
		Iterator itor = ibstablesinfo.values().iterator();
		while(itor.hasNext())
		{
			TableInfo tableinfo = (TableInfo) itor.next();
			Ibstables ibstables = tableinfo.getIbstables();
			if(ibstables.getAuthlevel().equals("��֯��"))
			{
				String updatesql = "update "+ibstables.getTablename().toLowerCase()+" set sitenum='"+to_deptnum+"' where sitenum='"+department.getDeptnum()+"'";
				this.getBaseDao().executeSql(updatesql);
			}
		}
		
		//������Ա���е���Ա������Ϣ
		List laborList = this.findWithQuery(Labor.class, "deptnum='"+department.getDeptnum()+"'");
		for(int i=0;i<laborList.size();i++)
		{
			Labor labor = (Labor) laborList.get(i);
			labor.setDeptnum(to_deptnum);
			this.getBaseDao().updateObject(labor);
		}
		
		
		//�����Լ�
		department.setDeptnum(to_deptnum);
		this.update(department);
	}
	
	
	
	
}	
