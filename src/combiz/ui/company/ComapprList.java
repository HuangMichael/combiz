package combiz.ui.company;

import combiz.domain.company.Comappr;
import combiz.domain.company.Companies;
import combiz.system.ui.ListWindow;

public class ComapprList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	
	public ComapprList()
	{
		super();
	}

	/**
	 * ���ܣ�����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * ���ߣ����
	 * ���ڣ�Oct 17, 200811:31:54 AM
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Companies companies = (Companies)this.getOwnerWnd().getMainObject();
		Comappr comappr = new Comappr();
		
		comappr.setCompany(companies.getCompany());
		
		this.mainObject = comappr;
		return true;
		
	}
	
	
	
}
