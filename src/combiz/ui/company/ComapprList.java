package combiz.ui.company;

import combiz.domain.company.Comappr;
import combiz.domain.company.Companies;
import combiz.system.ui.ListWindow;

public class ComapprList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	public ComapprList()
	{
		super();
	}

	/**
	 * 功能：点击新建记录按钮的触发事件，添加默认字段值
	 * 作者：李建红
	 * 日期：Oct 17, 200811:31:54 AM
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Companies companies = (Companies)this.getOwnerWnd().getMainObject();
		Comappr comappr = new Comappr();
		
		comappr.setCompany(companies.getCompany());
		
		this.mainObject = comappr;
		return true;
		
	}
	
	
	
}
