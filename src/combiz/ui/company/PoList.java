package combiz.ui.company;

import java.util.Date;

import combiz.domain.company.Companies;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.ui.ListWindow;

public class PoList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	
	public PoList()
	{
		super();
	}

	/** 
	 * 功能：点击新建记录按钮的触发事件，添加默认字段值
	 * 作者：李建红
	 * 日期：Oct 14, 20082:53:52 PM
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		CompaniesWindow parentWnd = (CompaniesWindow)ownerWnd;
		Companies companies = (Companies)parentWnd.getMainObject();
		
		Po po = new Po();
		
		//int PolInt = this.mainSrv.getRowCountByWhere(po, "ponum='"+po.getPonum()+"'");	
		po.setPonum(this.genAutokey("ponum"));
		po.setVendor(companies.getDescription());
		po.setStatus("流程未启动");
		po.setStatusdate(new Date());
		po.setTotalcost(0d);
		
		this.mainObject = po;
		return true;
	}
	
	
	
}
