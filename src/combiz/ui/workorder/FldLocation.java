package combiz.ui.workorder;

import java.util.List;

import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.pm.Premaint;
import combiz.domain.po.Poline;
import combiz.domain.workorder.Workorder;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDoublebox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 1:52:11 PM  Oct 23, 2008 
 * 功能：在输入位置后，如果该位置下只有一台设备，就把设备编号写入到eqnum字段
 * 捆绑表：workorder,premaint
 * 捆绑字段：location
 */
public class FldLocation extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根
		Object obj = this.getMainObject();
		if(obj instanceof Workorder)
		{
			Workorder wo  = (Workorder) obj;
		}
		if(obj instanceof Premaint)
		{
			Premaint pre = (Premaint) obj;
		}

		String location = (String) this.getValueByColname("location");
		if (Util.isNotNull(location)) 
		{
			List equiplist = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class, "location ='"+location+"' ");
			if(equiplist.size()==1)
			{
				Equipment equip = (Equipment) equiplist.get(0);
				String equipnum = equip.getEqnum();
				this.setValueByColname("eqnum", equipnum);
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
