package combiz.ui.rfq;

import combiz.domain.rfq.Rfqquoteline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;
import combiz.system.ui.common.ICombobox;
import combiz.system.ui.common.IDoublebox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Checkbox;
import com.inbasis.zul.Combobox;

public class FldIsAwarded extends FieldAdapter
{

	@Override
	public void action(Component arg0)
	throws Exception 
	{
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception 
	{
		ICheckbox checkbox = (ICheckbox) arg0;
		String value = checkbox.getValue();
		Rfqquoteline rfqquoteline = (Rfqquoteline) this.mainObject;
		if(Util.isNotNull(value) && value.equals("是"))
		{
			//已经授予了其他供应商
			int count = this.getMainSrv().getRowCountByWhere(this.getMainObject(), "rfqnum='"+rfqquoteline.getRfqnum()+
					"' and rfqlinenum="+rfqquoteline.getRfqlinenum()+" and itemnum='"+rfqquoteline.getItemnum()+"'"+
					" and vendor<>'"+rfqquoteline.getVendor()+"' and isawarded='是'");
			if(count>0)
			{
				this.throwException(arg0, "该报价行已经授予了,请先取消之前的授予!");
			}
		}
		//在授予该报价行时，判断单价是否大于零。
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if (Util.isNotNull(value) && unitcost ==null)
			unitcost = 0d;
		//String isawarded = (String) this.getValueByColname("isawarded");
		if(value.equals("是") && (unitcost - 0) <=0)
		{
			ICombobox target =(ICombobox) arg0.getFellow("rfqquoteline.isawarded");
			this.setValueByColname("isawarded", "否");
			throw new Exception("如果授予该供应商，该供应商报价应大于零");
		
		}
		
		
	}
	
}
