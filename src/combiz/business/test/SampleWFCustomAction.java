package combiz.business.test;

import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseSrv;
import combiz.system.workflow.common.WFCustomAction;

public class SampleWFCustomAction implements WFCustomAction
{

	/**
	 * ִ��һ������
	 * 
	 * brianhong  2008-3-17
	 * @param ibobaseSrv
	 * @param wfinstance
	 * @param wfaction
	 * @param mainObject
	 * @throws Exception
	 */
	public void executeAction(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Wfaction wfaction, Object mainObject)
	throws Exception 
	{
		//System.out.println("ִ���˲�����" + wfaction.getAction());
		
		throw new Exception("�׳����쳣��");
	}

}
