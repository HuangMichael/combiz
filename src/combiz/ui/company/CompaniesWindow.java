package combiz.ui.company;
 

import combiz.business.company.CompaniesSrv;
import combiz.domain.company.Companies;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class CompaniesWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CompaniesWindow()
	{
		super();
	}

	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Companies newobj = new Companies();	
		mainObject = newobj;
		return true;
	}

	
}
