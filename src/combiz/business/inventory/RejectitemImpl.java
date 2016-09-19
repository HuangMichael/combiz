package combiz.business.inventory;

import combiz.domain.inventory.*;
import combiz.system.IBOBaseImpl;

public class RejectitemImpl extends IBOBaseImpl
implements RejectitemSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}

	@Override
	public void save(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		Rejectitem rej = (Rejectitem) arg0;
		Double rejqty = rej.getRejectqty();
		if(rejqty == 0d)
		{
			throw new Exception("库存项目编号为:'"+rej.getItemnum()+"'的库存，报废数量不能等于零，请核实！");
		}
		super.save(arg0);
	}
	
	
}