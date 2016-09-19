package combiz.business.workorder;

import java.util.List;

import combiz.business.po.PoSrv;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.pr.Prline;
import combiz.domain.workflow.Wfinstance;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseSrv;
import combiz.system.IBOSrvUtil;
import combiz.system.util.Util;
import combiz.system.workflow.common.WfCustomCondition;


public class Ifreceived implements WfCustomCondition 
{

	public boolean executeCondition(IBOBaseSrv ibobaseSrv, Wfinstance wfinstance, Object mainObject)
	throws Exception
	{
		boolean result=false;
		Workorder wo = (Workorder) mainObject;
		String wonum = wo.getWonum();
		WorkorderSrv wosrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
		int verifytimes = 0;
		if(Util.isNotNull(wonum))
		{
			List wplist = wosrv.getBaseDao().findWithQuery(Wpmaterial.class, "wonum='"+wonum+"'");
			if(wplist.size()>0)
			{
				for(int i=0;i<wplist.size();i++)
				{
					String ponum = null;
					Long polinenum = 0l;
					Wpmaterial wpmat = (Wpmaterial) wplist.get(i);
					Double itemqty = wpmat.getItemqty();
					String itemnum = wpmat.getItemnum();
					List invlist = wosrv.getBaseDao().findWithQuery(Invreserve.class, "wonum='"+wonum+"' and itemnum='"+itemnum+"' and warehouse = '"+wpmat.getWarehouse()+"'");
					if(invlist.size()>0)
					{
						Invreserve invreserve = (Invreserve) invlist.get(0);
						if(Util.isNotNull(invreserve.getPonum()))
						{
							List  pllist = wosrv.getBaseDao().findWithQuery(Prline.class, "prnum ='"+invreserve.getPonum()+"' and prlinenum='"+invreserve.getPolinenum()+"'");
							if(pllist.size()>0)
							{
								Prline pl = (Prline) pllist.get(0);
								ponum = pl.getPonum();
								polinenum = pl.getPolinenum();
								Double qty = (Double) wosrv.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '"+ponum+"' and t.polinenum = "+polinenum+"  and status = 'ÒÑ¼ìÑé'");
								if(qty ==null)
								{
									qty = 0d;
								}
								else
								{
									if(qty - itemqty >=0 )
									{
										verifytimes++;
									}
									else
									{
										break;
									}
								}
								
							}
							else
							{
								verifytimes ++;
							}
							
							
						}
						
					}
					else
					{
						break;
					}

				}
				if(verifytimes - wplist.size() == 0)
				{
					result = true;
				}
				
			}
		}
		return result;
	}

}
