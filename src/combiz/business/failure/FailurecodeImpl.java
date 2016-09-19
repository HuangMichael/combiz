package combiz.business.failure;

import combiz.domain.failure.Failurecode;
import combiz.system.IBOBaseImpl;

public class FailurecodeImpl extends IBOBaseImpl implements FailurecodeSrv {

	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Failurecode))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		this.deleteAllChild(obj, "failcode");
		super.delete(obj);

	}

}
