package combiz.ui.location;

import com.inbasis.zul.Messagebox;

import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.ui.ListWindow;

public class LocstructList extends ListWindow
{
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		Locations parent = (Locations)ownerWnd.getMainObject();
		
		Locstruct newobj = new Locstruct();
		newobj.setLocation(parent.getLocation());
		newobj.setOrderby(0L);
		newobj.setHaschild("否");
		
		this.mainObject = newobj;
		return true;
	}
	
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
