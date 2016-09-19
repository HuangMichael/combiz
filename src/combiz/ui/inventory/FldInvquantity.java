package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Item;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDoublebox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
/**
 *  @author lijh 应用程序
 */
public class FldInvquantity extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根

		Invrectrans invrectrans = (Invrectrans) this.mainObject;
		Long id = invrectrans.getId();
		Double reqty = (Double) this.getValueByColname("quantity");
		Double unitcost = (Double) this.getValueByColname("unitcost");
		if (invrectrans.getPonum()!=null && invrectrans.getPolinenum() != null) 
		{
//			Double num = 0d;
/*			List list = this.mainSrv.getBaseDao().findWithQuery(Invrectrans.class, "polinenum = '"+invrectrans.getPolinenum()+"' and ponum = '"+invrectrans.getPonum()+"' and id <> "+id+"");
			if (list.size()>0) 
			{
				*//**
				 * 得到该项目产品已经接收的数量
				 * *//*
				for (int i=0;i<list.size();i++) 
				{
					Invrectrans inv = (Invrectrans) list.get(i);
					num = num + inv.getQuantity();
				}
			}*/	
			   Double num = (Double) this.mainSrv.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where polinenum = '"+invrectrans.getPolinenum()+"' and ponum = '"+invrectrans.getPonum()+"' and id <> "+id+"");
			   if(num == null)
				   num = 0d;
			
//				PolineImpl polmp = new PolineImpl();
//				List polist = polmp.findWithQuery("polinenum = '"+invrectrans.getPolinenum()+"' and ponum = '"+invrectrans.getPonum()+"'");
				List polist = this.mainSrv.getBaseDao().findWithQuery(Poline.class,"polinenum = '"+invrectrans.getPolinenum()+"' and ponum = '"+invrectrans.getPonum()+"'");
				if (polist.size()>0)
				{
					Poline po = (Poline) polist.get(0);
					Double orderqty = po.getOrderqty();
					if(reqty<=0 || (reqty+num)> orderqty) 
					{
						IDoublebox target =(IDoublebox) com.getFellow("invrectrans.quantity");
						Messagebox.show("接收数量应不为0，且不大于订购数量，请核实！");
						//this.setValueByColname("quantity", 0.0D);
						this.setValueByColname("quantity", "0.0");
//						this.throwException(target, "接收数量应不为0，且不大于订购数量，请核实！");
						//this.setValueByComponent(target, "0");
					}
					else{
						double linecost = reqty * unitcost;
						Component target = com.getFellow("invrectrans.linecost");
						this.setValueByColname("linecost", linecost);
					}
				}
				
		}
			return;
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		Invrectrans invrectrans = (Invrectrans) this.mainObject;

		if (invrectrans.getStatus().equals("待编号")) {
			this.setReadonly(arg0);
		}else{
			this.setNoReadonly(arg0);
		}
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
