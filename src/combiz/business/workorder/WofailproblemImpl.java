package combiz.business.workorder;

import combiz.system.IBOBaseImpl;

/**
 * �ڸ�����дҵ������� ���ߣ�ljh ���ڣ�2008-02-29
 * 
 */
public class WofailproblemImpl extends IBOBaseImpl implements WofailproblemSrv {

	@Override
	public void delete(Object arg0) throws Exception {
		// TODO �Զ����ɷ������
		this.deleteAllChild(arg0, "wofailproblem");
		super.delete(arg0);
	}

}
