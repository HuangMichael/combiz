package combiz.business.workorder;

import combiz.system.IBOBaseImpl;

/**
 * �ڸ�����дҵ������� ���ߣ�ljh ���ڣ�2008-02-29
 * 
 */
public class WofailcauseImpl extends IBOBaseImpl implements WofailcauseSrv {

	@Override
	public void delete(Object arg0) throws Exception {
		// TODO �Զ����ɷ������
		this.deleteAllChild(arg0, "wofailcause");
		super.delete(arg0);
	}
}
