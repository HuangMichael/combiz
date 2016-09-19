package combiz.ui.company;
 

import combiz.business.company.CompaniesSrv;
import combiz.domain.company.Companies;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class CompaniesWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CompaniesWindow()
	{
		super();
	}

	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Companies newobj = new Companies();	
		mainObject = newobj;
		return true;
	}

	
}
