package combiz.business.test;

import combiz.domain.equipment.Eqstatus;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseSrv;
import combiz.system.IBOSrvUtil;
import combiz.system.util.GenralSQLTemplate;
import combiz.system.workflow.common.WFCustomAction;

import java.util.List;

public class SampleWFCustomAction2 implements WFCustomAction
{

	/**
	 * 执行一个操作
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
		/*List test = ibobaseSrv.getBaseDao().findWithQuery(Eqstatus.class, "");
		ibobaseSrv.getBaseDao().deleteBatch(test);*/
		
		/*GenralSQLTemplate genralSQLTemplete = new GenralSQLTemplate(IBOSrvUtil.getBaseDao());
		genralSQLTemplete.execute("delete from eqstatus");
		genralSQLTemplete.execute("commit");*/
		
		ibobaseSrv.getBaseDao().executeSql("delete from eqstatus");
		ibobaseSrv.getBaseDao().executeSql("commit");
		
		List test = ibobaseSrv.getBaseDao().findWithQuery(Eqstatus.class, "");
		for(int i=0;i<test.size();i++)
		{
			System.out.println(test.get(i));
		}
		throw new Exception("dddddddddddddddddddddd");
	}

}
