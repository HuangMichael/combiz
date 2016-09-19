package combiz.business.inventory;



//
//public class WorkorderApprove implements WFCustomAction {
//		public void executeAction(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Wfaction wfaction, Object obj)
//		throws Exception 
//		{
//				WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
//				Workorder wo=(Workorder)obj;
//				worksrv.createinvr(wo);
//		}
//
//		
//		
//}


import combiz.business.workorder.WorkorderSrv;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseSrv;
import combiz.system.IBOSrvUtil;
import combiz.system.workflow.common.WFCustomAction;

public class WorkorderApprove implements WFCustomAction
{

	public void executeAction(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Wfaction wfaction, Object obj)
	throws Exception 
	{
		Workorder workorder=(Workorder)obj;
		WorkorderSrv workordersrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
		workordersrv.createinvr(workorder);
	}

}