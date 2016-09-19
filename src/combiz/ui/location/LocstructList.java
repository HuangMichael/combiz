package combiz.ui.location;

import com.inbasis.zul.Messagebox;

import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.ui.ListWindow;

public class LocstructList extends ListWindow
{
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		Locations parent = (Locations)ownerWnd.getMainObject();
		
		Locstruct newobj = new Locstruct();
		newobj.setLocation(parent.getLocation());
		newobj.setOrderby(0L);
		newobj.setHaschild("��");
		
		this.mainObject = newobj;
		return true;
	}
	
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
