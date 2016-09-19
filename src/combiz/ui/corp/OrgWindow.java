package combiz.ui.corp;

import combiz.business.corp.DepartmentSrv;
import combiz.domain.corp.Department;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;
import com.inbasis.zul.Treeitem;

public class OrgWindow extends TMainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public OrgWindow()
	{
		super();
	}
	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Department newobj = new Department();
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Department department = (Department) ti.getValue();
			newobj.setParent(department.getDeptnum());
		}
		newobj.setHaschild("否");
		newobj.setEnabled("是");
		//是公司
		newobj.setIscrewid("是");
		mainObject = newobj;
		
		return true;
	}
	

	/**
	 * 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null || ti.getValue()==null)
		{
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

/*		Department department = (Department) ti.getValue();
		if (department != null) 
		{
			HashMap ibstablesinfo = IBSServer.getIBSServer().getIbstablesinfo();
			Iterator itor = ibstablesinfo.values().iterator();
			while(itor.hasNext())
			{
				TableInfo tableinfo = (TableInfo) itor.next();
				Ibstables ibstables = tableinfo.getIbstables();
				if(ibstables.getAuthlevel().equals("组织级"))
				{
					int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from " + Util.toFirstUpcase(ibstables.getTablename()) + " where sitenum='"+department.getDeptnum()+"'");
					if(count>0)
					{
						Messagebox.show("数据表["+ibstables.getTablename()+"]中存在该部门的数据，不能删除该部门！");
						return;
					}
				}
			}
			//调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}*/
		
		//调用业务对象的删除方法
		super.delete();
		// 重新构建树
		mainTree.afterDeleteItem();
	}
	
	
	/**
	 * 部门调整，调整部门所在的树节点
	 * brianhong  2009-10-12
	 * @throws Exception
	 */
	public void deptchg()
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null || ti.getValue()==null)
		{
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Department department = (Department) ti.getValue();
		CommonDialog cp = (CommonDialog) this.popupDialog(department, "/corp/deptchg.xul"); //
		if(cp.isConfirm())
		{
			this.clear();
			Messagebox.show("部门调整已经完成！");
		}
	}
	
	
	/**
	 * 部门调整编码，需要更新所有关联的数据
	 * brianhong  2009-10-12
	 * @throws Exception
	 */
	public void deptnumchg()
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null || ti.getValue()==null)
		{
			Messagebox.show("请选中要修改的部门！");
			return;
		}

		Department department = (Department) ti.getValue();
		String orgNum = department.getDeptnum();
		CommonDialog cp = (CommonDialog) this.popupDialog(department, "/corp/deptnumchg.xul");
		if(cp!=null)
		{
			String to_deptnum = ((Textbox)cp.getFellow("to.deptnum")).getValue();
			if(Util.isNull(to_deptnum) || orgNum.equals(to_deptnum))
			{
				Messagebox.show("没有修改部门编码！");
				return;
			}
			else
			{
				int count = this.getMainSrv().getRowCountByWhere(department,"deptnum='"+to_deptnum+"'");
				if(count>0)
				{
					Messagebox.show("部门编码["+to_deptnum+"]已经存在，不能修改为当前值！");
					return;
				}
				if(Messagebox.show("确定要将部门编码["+orgNum+"]更新为["+to_deptnum+"]？","确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					((DepartmentSrv)this.getMainSrv()).deptnumchg(department, to_deptnum);
					this.clear();
					Messagebox.show("相关的组织机构数据已经更新！");
				}
			}
		}
		else
			this.refreshData();
	}
	
}
