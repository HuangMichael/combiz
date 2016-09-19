package combiz.ui.doclib;

import combiz.domain.ibs.Ibsapps;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;

import com.inbasis.zk.ui.Component;

public class FldDocapplibApp extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
	}

	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		if(this.getRecWnd()!=null)
		{
			if(((EditWindow)this.getRecWnd()).getListWnd() instanceof DocapplibList)
			{
				String app = (String) this.getValueByColname("app");
				Ibsapps ibsapp = (Ibsapps) this.getMainSrv().getBaseDao().findUniqueBy(Ibsapps.class, "app", app);
				if(ibsapp!=null)
					this.setValueByColname("ownertable", ibsapp.getMaintbname());
			}
		}
	}
}
