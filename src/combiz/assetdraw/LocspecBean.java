package combiz.assetdraw;

import combiz.domain.location.Locspec;
import combiz.system.IBSServer;
import combiz.system.mobile.MobileBase;
import combiz.system.util.Util;

import java.util.Date;
import java.util.HashMap;

public class LocspecBean extends MobileBase
{
	@Override
	public boolean save(String tablename, HashMap paramMap, HashMap otherParam)
	throws Exception
	{
		Locspec locspec = new Locspec();
		locspec.setChangedate(new Date());
		locspec.setChangeby((String)paramMap.get("changeby"));
		locspec.setClassattr((String)paramMap.get("classattr"));
		String strClassid = (String)paramMap.get("classid");
		if(Util.isNotNull(strClassid))
			locspec.setClassid(strClassid);
		String strId = (String)paramMap.get("id");
		if(Util.isNotNull(strId))
		{
			Long id = Long.parseLong((String)paramMap.get("id"));
			locspec.setId(id);
		}
		String strIsmustbe = (String)paramMap.get("ismustbe");
		if(Util.isNull(strIsmustbe))
			strIsmustbe = "��";
		locspec.setIsmustbe(strIsmustbe);
		locspec.setLocation((String)paramMap.get("location"));
		String strNvalue = (String)paramMap.get("numvalue");
		if(Util.isNotNull(strNvalue))
		{
			Double nvalue = Double.parseDouble(strNvalue);
			locspec.setNumvalue(nvalue);
		}
		locspec.setStrvalue((String)paramMap.get("strvalue"));
		locspec.setRemark((String)paramMap.get("remark"));
		locspec.setUnitid((String)paramMap.get("unitid"));
		
		try
		{
			//�������ҵ�����save�������Զ��ж����½����Ǹ���
			//����ֱ����saveObject
			IBSServer.getIBSServer().getBaseSrv().save(locspec);
		}
		catch(Exception e)
		{
			throw new Exception("ִ�����ݿⱣ��ʱ����:" + e.getMessage());
		}
		return true;
	}
	
}
