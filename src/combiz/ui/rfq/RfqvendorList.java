package combiz.ui.rfq;

import java.util.Date;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.ui.ListWindow;

public class RfqvendorList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	public RfqvendorList()
	{
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * @蒋祖兵 日期：2007-8-7
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		RfqWindow parentWnd = (RfqWindow)ownerWnd;
		Rfq rfq = (Rfq)parentWnd.getMainObject();
		
		Rfqvendor rl = new Rfqvendor();
		rl.setExchangedate(new Date());
		int PrlInt = this.mainSrv.getRowCountByWhere(rl, "rfqnum='"+rfq.getRfqnum()+"'");	
		rl.setRfqnum(rfq.getRfqnum());
	
		this.mainObject = rl;
		return true;
	}
	
	
	
}
