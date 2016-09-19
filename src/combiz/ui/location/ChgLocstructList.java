package combiz.ui.location;

import com.inbasis.zul.Messagebox;

import combiz.system.ui.CommonListWindow;

public class ChgLocstructList extends CommonListWindow
{

	@Override
	public boolean beforeRemove()
	throws Exception
	{
		if(Messagebox.show("确定要将该位置及其子位置从系统中移除吗？","确定？",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
		{
			return true;
		}
		else
			return false;
	}
	
}
