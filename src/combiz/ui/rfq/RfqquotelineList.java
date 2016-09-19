package combiz.ui.rfq;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class RfqquotelineList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	public RfqquotelineList()
	{
		super();
	}

	/**
	 * 
	 * @TODO ���۵��У������Ӱ�ťʱȷ���˷���
	 * @return true
	 * @throws Exception
	 * @����� 2007-8-14 ����09:48:02
	 */
	public boolean addNew()
	throws Exception
	{
		RecWindow parentWnd = (RecWindow)ownerWnd;
		
		Rfq rfq = (Rfq)ownerWnd.getOwnerWnd().getMainObject();
		Rfqvendor rv = (Rfqvendor)parentWnd.getMainObject();
		if(rv == null ||rv.getRfqnum() == null || rv.getRfqnum().equals(""))
		{
			Messagebox.show("���ڹ�Ӧ�̴���ѡ��һ��Ӧ�̺���ӣ�");
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
