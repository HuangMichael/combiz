package combiz.business.inventory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.pm.Pmgenhistory;
import combiz.domain.pm.Premaint;
import combiz.domain.po.Po;
import combiz.domain.pr.Pr;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseDao;
import combiz.system.IBOSrvUtil;
import combiz.system.schedultask.Task;

public class PrGenerator  extends Task
{
	/**
	 * @TODO 必须实现的任务方法
	 * @param map
	 * yuanjq 2008-10-24 上午11:25:19
	 * @see combiz.system.schedultask.Task#execute(java.util.Map)
	 */
	IBOBaseDao baseDao = null;
	InventorySrv invsrv = (InventorySrv)IBOSrvUtil.getSrv("inventory");
	
	public void execute(Map map) throws Exception
	{
		baseDao = (IBOBaseDao)map.get("baseDao");
		//任务执行的代码...
		/***
		 * 检验预防性维护中是否有数据，并得到信息
		 */
		List list = baseDao.findWithQuery(Inventory.class, "stocktype='常备库存'");
		if (list.size()>0)
		{
			invsrv.autogenerate(list);
		}
		
	}
}
