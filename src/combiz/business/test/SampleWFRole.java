package combiz.business.test;

import combiz.domain.workflow.Wfinstance;
import combiz.domain.workflow.Wfnode;
import combiz.system.IBOBaseSrv;
import combiz.system.workflow.common.WfCustomRole;

import java.util.ArrayList;
import java.util.List;

public class SampleWFRole implements  WfCustomRole
{

	/**
	 * 返回一个list，里面存储是labor对象集合
	 * 
	 * brianhong  2008-3-17
	 * @param ibobaseSrv
	 * @param wfinstance
	 * @param wfnode
	 * @param mainObject
	 * @return
	 * @throws Exception
	 */
	public List laborList(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Wfnode wfnode, Object mainObject)
	throws Exception 
	{
		return new ArrayList();
	}

}
