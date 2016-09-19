package combiz.assetdraw;

import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseSrv;
import combiz.system.IBSServer;
import combiz.system.assetdraw.TransDataBase;
import combiz.system.util.Util;

import java.util.HashMap;

public class AutokeyEqnumBean implements TransDataBase
{
	public String execute(HashMap paramMap)
	{
		String eqnum;
		try
		{
			IBOBaseSrv baseSrv = IBSServer.getIBSServer().getBaseSrv();
			eqnum = baseSrv.genInskey("EQNUM");
			if(Util.isNotNull(eqnum))
			{
				int count = baseSrv.getBaseDao().selectCountByWhere(Equipment.class, "eqnum='"+eqnum+"'");
				while(count>0)
				{
					eqnum = baseSrv.genInskey("EQNUM");
					count = baseSrv.getBaseDao().selectCountByWhere(Equipment.class, "eqnum='"+eqnum+"'");
				}
				eqnum = "OK:" + eqnum;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		return eqnum;
	}
	
}