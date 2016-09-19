package combiz.ui.corp;

import com.inbasis.zul.Messagebox;

import combiz.business.corp.DepartmentSrv;
import combiz.domain.corp.Department;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;



public class DeptChanged extends CommonDialog
{
	
	private String orgParent;

	@Override
	public void onCreate() throws Exception
	{
		super.onCreate();
		orgParent = ((Department)this.getMainObject()).getParent();
	}

	@Override
	public void confirm() throws Exception
	{
		if(!this.canSave())
			return;
		
		Department department = (Department)this.getMainObject();
		if(Util.isNull(department.getParent()) || department.getParent().equals(orgParent))
		{
			Messagebox.show("û�е������Ÿ�����");
			return;
		}
		else
		{
			int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Deptance where deptance='"+department.getDeptnum()+"' and deptnum='"+department.getParent()+"'");
			if(count>0)
			{
				Messagebox.show("�ò��ŵĸ��������Ǹò����Լ������Ӽ�["+department.getParent()+"]��ȡ��������");
				return;
			}
			((DepartmentSrv)this.getMainSrv()).deptchg(department, orgParent);
			this.setConfirm(true);
			this.onClose();
		}
	}
	
}
