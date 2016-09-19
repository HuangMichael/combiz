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


import combiz.business.classattr.ClassificationSrv;
import combiz.domain.inventory.Matreq;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseSrv;
import combiz.system.IBOSrvUtil;
import combiz.system.workflow.common.WFCustomAction;

public class MatreqApprove implements WFCustomAction
{

	public void executeAction(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Wfaction wfaction, Object obj)
	throws Exception 
	{
		Matreq matreq=(Matreq)obj;
		MatreqSrv matreqsrv = (MatreqSrv)IBOSrvUtil.getSrv("matreq");
		matreqsrv.createinvr(matreq);
	}

}