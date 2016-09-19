package combiz.business.failure;

import combiz.domain.failure.*;
import combiz.system.IBOBaseImpl;

public class FailureproblemImpl extends IBOBaseImpl implements FailureproblemSrv {

	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Failureproblem))
			throw new Exception("要删除的对象传递不正确！");

		this.deleteAllChild(obj, "failproblem");
		super.delete(obj);

	}
}
