package combiz.ui.workorder;

import combiz.domain.inventory.Invreserve;
import combiz.system.ui.ListWindow;

public class InvreserveList extends ListWindow
{

	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ�ljh ���ڣ�2008-01-28
	 */

	@Override
	public boolean addNew() throws Exception {
		// TODO �Զ����ɷ������
		return super.addNew();
	}
	
	public void Deletinvr() throws Exception
	{
		Invreserve inv = (Invreserve) this.getMainObject();
		this.getMainSrv().getBaseDao().deleteObject(inv);
	}
}
