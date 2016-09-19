package combiz.business.failure;

import combiz.domain.failure.*;
import combiz.system.IBOBaseImpl;

public class FailurecauseImpl extends IBOBaseImpl implements FailurecauseSrv {

	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Failurecause))
			throw new Exception("要删除的对象传递不正确！");

		this.deleteAllChild(obj, "failcause");
		super.delete(obj);

	}
}
