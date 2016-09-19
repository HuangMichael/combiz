package combiz.ui.rfq;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class RfqquotelineList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	public RfqquotelineList()
	{
		super();
	}

	/**
	 * 
	 * @TODO 报价单行，点击添加按钮时确发此方法
	 * @return true
	 * @throws Exception
	 * @蒋祖兵 2007-8-14 上午09:48:02
	 */
	public boolean addNew()
	throws Exception
	{
		RecWindow parentWnd = (RecWindow)ownerWnd;
		
		Rfq rfq = (Rfq)ownerWnd.getOwnerWnd().getMainObject();
		Rfqvendor rv = (Rfqvendor)parentWnd.getMainObject();
		if(rv == null ||rv.getRfqnum() == null || rv.getRfqnum().equals(""))
		{
			Messagebox.show("请在供应商窗口选择一供应商后添加！");
			return false;
		}
	
		
		Rfqquoteline rl = new Rfqquoteline();
		
		int PrlInt = this.mainSrv.getRowCountByWhere(rl, "rfqnum='"+rfq.getRfqnum()+"'");	
		rl.setRfqnum(rfq.getRfqnum());
		rl.setRfqlinenum((long)PrlInt+1);
		this.mainObject = rl;
		return true;
	}
	
	
	
}
