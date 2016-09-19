package combiz.ui.assetscard;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;
import combiz.domain.budget.Budgetitem;
import combiz.domain.budget.Budgetline;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class Copynet extends FieldAdapter {
	public void action(Component component) 
	throws Exception 
	{
		
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public Object initValue() throws Exception
	{
		Assetscard assetscard = (Assetscard) this.getMainObject();
		String assetcode = assetscard.getAssetcode();
		Double net_xn = 0D;
		List list = this.getMainSrv().getBaseDao().findWithQuery(Assetscardline.class, "assetcode='"+assetcode+"'");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Assetscardline assetscardline = (Assetscardline)list.get(i);
				String status = assetscardline.getStatus();
				if(status.equals("Î´½á×ª")){
					net_xn=assetscardline.getNet();
				}
			}
		}
		return net_xn;
	}
	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
