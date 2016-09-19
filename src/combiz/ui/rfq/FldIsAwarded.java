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
		if(Util.isNotNull(value) && value.equals("��"))
		{
			//�Ѿ�������������Ӧ��
			int count = this.getMainSrv().getRowCountByWhere(this.getMainObject(), "rfqnum='"+rfqquoteline.getRfqnum()+
					"' and rfqlinenum="+rfqquoteline.getRfqlinenum()+" and itemnum='"+rfqquoteline.getItemnum()+"'"+
					" and vendor<>'"+rfqquoteline.getVendor()+"' and isawarded='��'");
			if(count>0)
			{
				this.throwException(arg0, "�ñ������Ѿ�������,����ȡ��֮ǰ������!");
			}
		}
		//������ñ�����ʱ���жϵ����Ƿ�����㡣
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if (Util.isNotNull(value) && unitcost ==null)
			unitcost = 0d;
		//String isawarded = (String) this.getValueByColname("isawarded");
		if(value.equals("��") && (unitcost - 0) <=0)
		{
			ICombobox target =(ICombobox) arg0.getFellow("rfqquoteline.isawarded");
			this.setValueByColname("isawarded", "��");
			throw new Exception("�������ù�Ӧ�̣��ù�Ӧ�̱���Ӧ������");
		
		}
		
		
	}
	
}
