package combiz.ui.workorder;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.stdplan.Jobplan;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.MainWindow;

public class CreatejobplanDialog 
extends CommonDialog
{
	
	public CreatejobplanDialog()
	{
		super();
	}
	
	/**
	 * 产生重新分配工具借还数量的交易记录
	 * 
	 * ljh  2008-03-21
	 * @throws Exception
	 */
	
	public void reassend() 
	throws Exception
	{
		WorkorderSrv workordersrv = (WorkorderSrv) IBOSrvUtil.getSrv("workorder");
		Jobplan jobplan = (Jobplan) this.getMainObject();
		MainWindow wownd =(MainWindow) this.getOwnerWnd();
		Workorder workorder = (Workorder) wownd.getMainObject();
		
		workordersrv.createjbplan(workorder, jobplan);
		this.detach();
	}

	
}
