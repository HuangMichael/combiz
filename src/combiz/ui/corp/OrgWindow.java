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
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public OrgWindow()
	{
		super();
	}
	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Department newobj = new Department();
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Department department = (Department) ti.getValue();
			newobj.setParent(department.getDeptnum());
		}
		newobj.setHaschild("��");
		newobj.setEnabled("��");
		//�ǹ�˾
		newobj.setIscrewid("��");
		mainObject = newobj;
		
		return true;
	}
	

	/**
	 * 
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null || ti.getValue()==null)
		{
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
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
				if(ibstables.getAuthlevel().equals("��֯��"))
				{
					int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from " + Util.toFirstUpcase(ibstables.getTablename()) + " where sitenum='"+department.getDeptnum()+"'");
					if(count>0)
					{
						Messagebox.show("���ݱ�["+ibstables.getTablename()+"]�д��ڸò��ŵ����ݣ�����ɾ���ò��ţ�");
						return;
					}
				}
			}
			//����ҵ������ɾ������
			super.delete();
			// ���¹�����
			mainTree.afterDeleteItem();
		}*/
		
		//����ҵ������ɾ������
		super.delete();
		// ���¹�����
		mainTree.afterDeleteItem();
	}
	
	
	/**
	 * ���ŵ����������������ڵ����ڵ�
	 * brianhong  2009-10-12
	 * @throws Exception
	 */
	public void deptchg()
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null || ti.getValue()==null)
		{
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
			return;
		}

		Department department = (Department) ti.getValue();
		CommonDialog cp = (CommonDialog) this.popupDialog(department, "/corp/deptchg.xul"); //
		if(cp.isConfirm())
		{
			this.clear();
			Messagebox.show("���ŵ����Ѿ���ɣ�");
		}
	}
	
	
	/**
	 * ���ŵ������룬��Ҫ�������й���������
	 * brianhong  2009-10-12
	 * @throws Exception
	 */
	public void deptnumchg()
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null || ti.getValue()==null)
		{
			Messagebox.show("��ѡ��Ҫ�޸ĵĲ��ţ�");
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
				Messagebox.show("û���޸Ĳ��ű��룡");
				return;
			}
			else
			{
				int count = this.getMainSrv().getRowCountByWhere(department,"deptnum='"+to_deptnum+"'");
				if(count>0)
				{
					Messagebox.show("���ű���["+to_deptnum+"]�Ѿ����ڣ������޸�Ϊ��ǰֵ��");
					return;
				}
				if(Messagebox.show("ȷ��Ҫ�����ű���["+orgNum+"]����Ϊ["+to_deptnum+"]��","ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					((DepartmentSrv)this.getMainSrv()).deptnumchg(department, to_deptnum);
					this.clear();
					Messagebox.show("��ص���֯���������Ѿ����£�");
				}
			}
		}
		else
			this.refreshData();
	}
	
}
