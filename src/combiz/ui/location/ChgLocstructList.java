package combiz.ui.location;

import com.inbasis.zul.Messagebox;

import combiz.system.ui.CommonListWindow;

public class ChgLocstructList extends CommonListWindow
{

	@Override
	public boolean beforeRemove()
	throws Exception
	{
		if(Messagebox.show("ȷ��Ҫ����λ�ü�����λ�ô�ϵͳ���Ƴ���","ȷ����",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
		{
			return true;
		}
		else
			return false;
	}
	
}
