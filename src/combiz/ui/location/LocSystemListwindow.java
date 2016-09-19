package combiz.ui.location;

import combiz.domain.location.Locstruct;
import combiz.domain.location.Locsystem;

import com.inbasis.zul.Messagebox;

public class LocSystemListwindow extends combiz.system.ui.CommonListWindow
{


	@Override
	public boolean canRemove() throws Exception
	{
		//判断是否能删除
		Locsystem locsys = (Locsystem) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByWhere(Locstruct.class, "systemid='"+locsys.getSystemid()+"'");
		if(count>0)
		{
			Messagebox.show("该系统还存在关联的位置数据，不能删除该系统！");
			return false;
		}
		
		return super.canRemove();
	}
	
}
