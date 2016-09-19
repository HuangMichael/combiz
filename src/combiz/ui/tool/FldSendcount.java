package combiz.ui.tool;

import combiz.business.tool.TooltransSrv;
import combiz.domain.tool.Toolreserve;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Longbox;
import com.inbasis.zul.Messagebox;

/**
 *  @author ljh 应用程序
 */

public class FldSendcount extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根
		Toolreserve toolreserve = (Toolreserve) this.mainObject;
		Longbox ilbox = null;
		ilbox = (Longbox) com;
		
		TooltransSrv worksrv = (TooltransSrv)IBOSrvUtil.getSrv("tooltrans");
		Long getcont =(Long) worksrv.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where sendnum = '"+toolreserve.getId()+"' and transtype = '借用'");
		
		if (getcont == null)
		{
			getcont = 0L;
		}
		
		
		if (ilbox.getValue()!=null) {
			Long count = ilbox.getValue();
			if (count <= 0)
			{
				Messagebox.show("工具数量必须为大于0的整数！");
				this.setValueByColname("sendcount", toolreserve.getReservedqty());
				return;
			}else if (count > toolreserve.getReservedqty())
			{
				Messagebox.show("发放的工具数量大于申请的工具数量！");
				this.setValueByColname("sendcount", toolreserve.getReservedqty());
				return;
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}

	@Override
	public void validate(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}
	
}
