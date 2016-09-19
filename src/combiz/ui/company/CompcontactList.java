package combiz.ui.company;

import combiz.business.company.CompcontactSrv;
import combiz.domain.company.Companies;
import combiz.domain.company.Compcontact;
import combiz.system.ui.ListWindow;

public class CompcontactList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CompcontactList()
	{
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		CompaniesWindow parentWnd = (CompaniesWindow)ownerWnd;
		Companies parent = (Companies) parentWnd.getMainObject();
		
		Compcontact newobj = new Compcontact();
		newobj.setCompany(parent.getCompany());
		
		this.mainObject = newobj;
		return true;
	}

}
