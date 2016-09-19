package combiz.ui.rfq;

import java.util.Date;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.ui.ListWindow;

public class RfqvendorList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	public RfqvendorList()
	{
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * @����� ���ڣ�2007-8-7
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
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
