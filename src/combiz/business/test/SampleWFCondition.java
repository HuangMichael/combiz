package combiz.business.test;

import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseSrv;
import combiz.system.workflow.common.WfCustomCondition;


public class SampleWFCondition implements WfCustomCondition 
{

	/**
	 * 执行一个条件判断，返回true or false
	 * 
	 * brianhong  2008-3-17
	 * @param ibobaseSrv
	 * @param wfinstance
	 * @param mainObject
	 * @return
	 * @throws Exception
	 */
	public boolean executeCondition(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Object mainObject)
	throws Exception
	{
		return true;
	}

}
