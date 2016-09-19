package combiz.ui.location;

import java.util.List;

import combiz.business.location.LocationsSrv;
import combiz.domain.location.Locations;
import combiz.system.FieldAdapter;
import combiz.system.ui.MainWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldClassid extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{
		List locspecList;
		Locations locations = (Locations)this.mainObject;
		try {
			locspecList = this.mainSrv.findByRelation(mainObject, "locspec");
			if(locations.getClassid()!=null && locspecList.size()>0)
			{
				this.setReadonly(component);
			}
			else
			{
				this.setNoReadonly(component);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
	}

}
