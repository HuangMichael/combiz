package combiz.business.workorder;

import combiz.domain.workorder.Wofailcode;
import combiz.system.IBOBaseImpl;

/**
 * �ڸ�����дҵ������� ���ߣ�ljh ���ڣ�2008-02-29
 * 
 */
public class WofailcodeImpl extends IBOBaseImpl implements WofailcodeSrv {

	@Override
	public void delete(Object obj) throws Exception {
		// TODO �Զ����ɷ������
		if(!(obj instanceof Wofailcode))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		this.deleteAllChild(obj, "wofailcode");
		super.delete(obj);
	}
}
