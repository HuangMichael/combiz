package combiz.business.rfq;

import combiz.domain.rfq.Rfqquoteline;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class RfqquotelineImpl extends IBOBaseImpl
implements RfqquotelineSrv {
	
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfqquoteline))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}


	@Override
	public void save(Object arg0) 
	throws Exception 
	{
		Rfqquoteline rfqquoteline = (Rfqquoteline)arg0;
		String isawarded = rfqquoteline.getIsawarded();
		if(Util.isNotNull(isawarded) && isawarded.equals("是"))
		{
			//已经授予了其他供应商
			int count = this.getRowCountByWhere(rfqquoteline, "rfqnum='"+rfqquoteline.getRfqnum()+
					"' and rfqlinenum="+rfqquoteline.getRfqlinenum()+" and itemnum='"+rfqquoteline.getItemnum()+"'"+
					" and vendor<>'"+rfqquoteline.getVendor()+"' and isawarded='是'");
			if(count>0)
			{
				throw new Exception("报价行["+rfqquoteline.getItemnum()+"]已经授予了超过两家供应商,请检查报价行!");
			}
		}
		super.save(arg0);
	}

	
/////////////////////业务方法//////////////////////////////////
}
