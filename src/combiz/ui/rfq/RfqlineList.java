package combiz.ui.rfq;

import java.util.Date;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.system.ui.ListWindow;

public class RfqlineList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	public RfqlineList()
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
		
		Rfqline rl = new Rfqline();
		
		Long Prl = this.mainSrv.getBaseDao().
			selectLongMaxByHql("select max(t.rfqlinenum) from Rfqline t where t.rfqnum='"+rfq.getRfqnum()+"'");

		rl.setRfqnum(rfq.getRfqnum());
		rl.setRfqlinenum(Prl.longValue()+1);
		rl.setUnitcost(0.0);
		rl.setLinecost(0.0);
		rl.setOrderqty(0D);
		rl.setInspection("��");
		rl.setIsservice("��");
		rl.setEnterby(this.getLaborInfo().getLaborname());
		rl.setEnterdate(new Date());
		this.mainObject = rl;
		return true;
	}
	
	
	
}
