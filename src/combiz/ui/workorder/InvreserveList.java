package combiz.ui.workorder;

import combiz.domain.inventory.Invreserve;
import combiz.system.ui.ListWindow;

public class InvreserveList extends ListWindow
{

	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：ljh 日期：2008-01-28
	 */

	@Override
	public boolean addNew() throws Exception {
		// TODO 自动生成方法存根
		return super.addNew();
	}
	
	public void Deletinvr() throws Exception
	{
		Invreserve inv = (Invreserve) this.getMainObject();
		this.getMainSrv().getBaseDao().deleteObject(inv);
	}
}
