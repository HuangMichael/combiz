package combiz.business.rfq;

import combiz.domain.rfq.Rfqline;
import combiz.domain.rfq.Rfqquoteline;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class RfqlineImpl extends IBOBaseImpl
implements RfqlineSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfqline))
			throw new Exception("要删除的对象传递不正确！");
		Rfqline rl = (Rfqline)obj;
		//删除关联记录---报价行
		List rfqquoteline = this.getBaseDao().findWithQuery(Rfqquoteline.class, "rfqnum='"+rl.getRfqnum()+"' and rfqlinenum='"+rl.getRfqlinenum()+"'");
		this.getBaseDao().deleteBatch(rfqquoteline);
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////业务方法//////////////////////////////////
}
