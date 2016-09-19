package combiz.business.corp;


import combiz.domain.corp.Department;
import combiz.system.IBOBaseSrv;


public interface DepartmentSrv extends IBOBaseSrv
{
	/**
	 * 修改部门编码
	 * brianhong  2009-10-12
	 * @param department
	 * @param to_deptnum
	 * @throws Exception
	 */
	public void deptnumchg(Department department,String to_deptnum)
	throws Exception;
	
	/**
	 * 部门调整
	 * brianhong  2009-10-12
	 * @param department
	 * @param to_deptnum
	 * @throws Exception
	 */
	public void deptchg(Department department,String orgParent)
	throws Exception;
}
