package combiz.ui.company;

import combiz.business.company.CompcontactSrv;
import combiz.domain.company.Companies;
import combiz.domain.company.Compcontact;
import combiz.system.ui.ListWindow;

public class CompcontactList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CompcontactList()
	{
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		CompaniesWindow parentWnd = (CompaniesWindow)ownerWnd;
		Companies parent = (Companies) parentWnd.getMainObject();
		
		Compcontact newobj = new Compcontact();
		newobj.setCompany(parent.getCompany());
		
		this.mainObject = newobj;
		return true;
	}

}
