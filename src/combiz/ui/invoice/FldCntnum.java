package combiz.ui.invoice;

import java.util.List;

import combiz.domain.contract.Contract;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.invoice.Invoice;
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
 * 功能：在发票应用程序下，输入合同字段后，鼠标离开后，自动填充供应商字段，同时将采购单字段清空。
 * 捆绑表：invoice
 * 捆绑字段：cntnum
 */
public class FldCntnum extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根
		Object obj = this.getMainObject();
		if(obj instanceof Invoice)
		{
			Invoice invoice  = (Invoice) obj;
		}

		String cntnum = (String) this.getValueByColname("cntnum");
		if (Util.isNotNull(cntnum)) 
		{
			List contractlist = this.getMainSrv().getBaseDao().findWithQuery(Contract.class, "cntnum ='"+cntnum+"' ");
			if(contractlist.size()>0)
			{
				Contract contract = (Contract) contractlist.get(0);
				String vendor = contract.getVendor();
				this.setValueByColname("vendor", vendor);
				this.setValueByColname("ponum", "");
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
