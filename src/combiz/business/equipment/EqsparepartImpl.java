package combiz.business.equipment;

import combiz.domain.equipment.Eqsparepart;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class EqsparepartImpl extends IBOBaseImpl
implements EqsparepartSrv {
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Eqsparepart))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////业务方法//////////////////////////////////
}
