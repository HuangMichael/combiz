package combiz.business.workorder;

import combiz.domain.workorder.Wofailcode;
import combiz.system.IBOBaseImpl;

/**
 * 在该类中写业务处理过程 作者：ljh 日期：2008-02-29
 * 
 */
public class WofailcodeImpl extends IBOBaseImpl implements WofailcodeSrv {

	@Override
	public void delete(Object obj) throws Exception {
		// TODO 自动生成方法存根
		if(!(obj instanceof Wofailcode))
			throw new Exception("要删除的对象传递不正确！");
		this.deleteAllChild(obj, "wofailcode");
		super.delete(obj);
	}
}
