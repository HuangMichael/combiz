package combiz.assetdraw;

import combiz.system.IBSServer;
import combiz.system.assetdraw.TransDataBase;

import java.util.HashMap;

public class IbsAdnumAutokey implements TransDataBase
{
	public String execute(HashMap paramMap)
	{
		String adnum;
		try
		{
			adnum = IBSServer.getIBSServer().getBaseSrv().genInskey("DRAWNUM");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		return adnum;
	}
	
}
