package combiz.business.workorder;

import combiz.system.IBOBaseImpl;

/**
 * 在该类中写业务处理过程 作者：ljh 日期：2008-02-29
 * 
 */
public class WofailcauseImpl extends IBOBaseImpl implements WofailcauseSrv {

	@Override
	public void delete(Object arg0) throws Exception {
		// TODO 自动生成方法存根
		this.deleteAllChild(arg0, "wofailcause");
		super.delete(arg0);
	}
}
