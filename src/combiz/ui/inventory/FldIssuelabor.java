package combiz.ui.inventory;

import java.util.List;

import combiz.domain.corp.Labor;
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
 * 11:49:28 AM  Nov 5, 2008 
 * 功能：在发放设备的时候，如果选中发放给某一个人员的话，将该人员所在的部门信息写回。
 * 捆绑表：INVUSETRANS
 * 捆绑字段：ISSUETOLABOR
 */
public class FldIssuelabor extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根
		
		String issuelabor = (String) this.getValueByColname("issuetolabor");
		if(Util.isNotNull(issuelabor))
		{
			List laborlist = this.getMainSrv().getBaseDao().findWithQuery(Labor.class, "labornum='"+issuelabor+"'");
			if(laborlist.size()>0)
			{
				Labor labor = (Labor) laborlist.get(0);
				String dept = labor.getDeptnum();
				this.setValueByColname("issuedeptnum", dept);
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
