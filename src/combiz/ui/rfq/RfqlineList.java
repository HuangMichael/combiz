package combiz.ui.rfq;

import java.util.Date;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.system.ui.ListWindow;

public class RfqlineList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	public RfqlineList()
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
		
		Rfqline rl = new Rfqline();
		
		Long Prl = this.mainSrv.getBaseDao().
			selectLongMaxByHql("select max(t.rfqlinenum) from Rfqline t where t.rfqnum='"+rfq.getRfqnum()+"'");

		rl.setRfqnum(rfq.getRfqnum());
		rl.setRfqlinenum(Prl.longValue()+1);
		rl.setUnitcost(0.0);
		rl.setLinecost(0.0);
		rl.setOrderqty(0D);
		rl.setInspection("否");
		rl.setIsservice("否");
		rl.setEnterby(this.getLaborInfo().getLaborname());
		rl.setEnterdate(new Date());
		this.mainObject = rl;
		return true;
	}
	
	
	
}
