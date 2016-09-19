package combiz.ui.company;

import java.util.Date;

import combiz.domain.company.Companies;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.ui.ListWindow;

public class PoList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	public PoList()
	{
		super();
	}

	/** 
	 * ���ܣ�����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * ���ߣ����
	 * ���ڣ�Oct 14, 20082:53:52 PM
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		CompaniesWindow parentWnd = (CompaniesWindow)ownerWnd;
		Companies companies = (Companies)parentWnd.getMainObject();
		
		Po po = new Po();
		
		//int PolInt = this.mainSrv.getRowCountByWhere(po, "ponum='"+po.getPonum()+"'");	
		po.setPonum(this.genAutokey("ponum"));
		po.setVendor(companies.getDescription());
		po.setStatus("����δ����");
		po.setStatusdate(new Date());
		po.setTotalcost(0d);
		
		this.mainObject = po;
		return true;
	}
	
	
	
}
