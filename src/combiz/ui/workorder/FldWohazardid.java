package combiz.ui.workorder;

import combiz.domain.stdplan.Hazard;
import combiz.domain.workorder.Wohazard;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldWohazardid extends FieldAdapter
{	
	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
		Textbox textbox = (Textbox)component;
		if(textbox.getValue()!=null && !textbox.getValue().equals(""))
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
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
	 * @throws Exception 
	 * 
	 */
	public void action(Component component)
	throws Exception 
	{
		Textbox textbox = (Textbox)component;
		Wohazard wohazard = (Wohazard)this.mainObject;
		List hazards = this.mainSrv.getBaseDao().findWithQuery(Hazard.class,"hazardid='" + textbox.getValue() + "'");
		if (hazards.size() == 1)
		{
			Hazard hazard = (Hazard)hazards.get(0);
			wohazard.setDescription(hazard.getDescription());
			wohazard.setHazmat(hazard.getHazmat());
			wohazard.setHazprec(hazard.getHazprec());
			wohazard.setHaztagout(hazard.getHaztagout());
			wohazard.setHealth(hazard.getHealth());

			RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
			recWnd.bandData();
		}
	}

}
