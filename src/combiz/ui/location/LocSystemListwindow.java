package combiz.ui.location;

import combiz.domain.location.Locstruct;
import combiz.domain.location.Locsystem;

import com.inbasis.zul.Messagebox;

public class LocSystemListwindow extends combiz.system.ui.CommonListWindow
{


	@Override
	public boolean canRemove() throws Exception
	{
		//�ж��Ƿ���ɾ��
		Locsystem locsys = (Locsystem) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByWhere(Locstruct.class, "systemid='"+locsys.getSystemid()+"'");
		if(count>0)
		{
			Messagebox.show("��ϵͳ�����ڹ�����λ�����ݣ�����ɾ����ϵͳ��");
			return false;
		}
		
		return super.canRemove();
	}
	
}
