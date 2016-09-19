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
			throw new Exception("�����Ŀ���Ϊ:'"+rej.getItemnum()+"'�Ŀ�棬�����������ܵ����㣬���ʵ��");
		}
		super.save(arg0);
	}
	
	
}