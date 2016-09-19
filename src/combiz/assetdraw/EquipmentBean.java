package combiz.assetdraw;

import combiz.domain.equipment.Eqspec;
import combiz.system.IBSServer;
import combiz.system.mobile.MobileBase;
import combiz.system.util.Util;

import java.util.Date;
import java.util.HashMap;

public class EquipmentBean extends MobileBase
{
	@Override
	public boolean save(String tablename, HashMap paramMap, HashMap otherParam)
	throws Exception
	{
		Eqspec eqspec = new Eqspec();
		eqspec.setChangedate(new Date());
		eqspec.setChangeby((String)paramMap.get("changeby"));
		eqspec.setClassattr((String)paramMap.get("classattr"));
		String strClassid = (String)paramMap.get("classid");
		if(Util.isNotNull(strClassid))
			eqspec.setClassid(strClassid);
		String strId = (String)paramMap.get("id");
		if(Util.isNotNull(strId))
		{
			Long id = Long.parseLong((String)paramMap.get("id"));
			eqspec.setId(id);
		}
		String strIsmustbe = (String)paramMap.get("ismustbe");
		if(Util.isNull(strIsmustbe))
			strIsmustbe = "否";
		eqspec.setIsmustbe(strIsmustbe);
		eqspec.setEqnum((String)paramMap.get("eqnum"));
		String strNvalue = (String)paramMap.get("numvalue");
		if(Util.isNotNull(strNvalue))
		{
			Double nvalue = Double.parseDouble(strNvalue);
			eqspec.setNumvalue(nvalue);
		}
		eqspec.setStrvalue((String)paramMap.get("strvalue"));
		eqspec.setRemark((String)paramMap.get("remark"));
		eqspec.setUnitid((String)paramMap.get("unitid"));
		
		try
		{
			//必须调用业务类的save方法，自动判断是新建还是更新
			IBSServer.getIBSServer().getSrvByTable("equipment").save(eqspec);
		}
		catch(Exception e)
		{
			throw new Exception("执行数据库保存时出错:" + e.getMessage());
		}
		return true;
	}
	
}
