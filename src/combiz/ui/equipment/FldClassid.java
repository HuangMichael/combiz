package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;

import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldClassid extends FieldAdapter
{	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{
		List eqspecList;
		Equipment equipment = (Equipment)mainObject;
		try {
			eqspecList = this.mainSrv.findByRelation(equipment, "eqspec");
			if(equipment.getClassid()!=null && eqspecList.size()>0)
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
