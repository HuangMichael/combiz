package combiz.ui.location;

import combiz.domain.location.Locsystem;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldLocsystem_isdefault extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
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
		String isdef = (String) this.getValueByColname("isdefault");
		if(isdef!=null && isdef.equals("是"))
		{
			int count = this.mainSrv.getBaseDao().selectCountByWhere(Locsystem.class, "isdefault='是'");
			if(count>0)
			{
				Messagebox.show("默认系统已经存在！");
				this.setValueByColname("isdefault", "否");
			}
		}
	}

}
