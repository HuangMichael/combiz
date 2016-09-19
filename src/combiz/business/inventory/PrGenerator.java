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
	 * @TODO ����ʵ�ֵ����񷽷�
	 * @param map
	 * yuanjq 2008-10-24 ����11:25:19
	 * @see combiz.system.schedultask.Task#execute(java.util.Map)
	 */
	IBOBaseDao baseDao = null;
	InventorySrv invsrv = (InventorySrv)IBOSrvUtil.getSrv("inventory");
	
	public void execute(Map map) throws Exception
	{
		baseDao = (IBOBaseDao)map.get("baseDao");
		//����ִ�еĴ���...
		/***
		 * ����Ԥ����ά�����Ƿ������ݣ����õ���Ϣ
		 */
		List list = baseDao.findWithQuery(Inventory.class, "stocktype='�������'");
		if (list.size()>0)
		{
			invsrv.autogenerate(list);
		}
		
	}
}
