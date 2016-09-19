package combiz.assetdraw;

import combiz.domain.ibs.Ibsassetdraw;
import combiz.system.IBSServer;
import combiz.system.mobile.MobileBase;
import combiz.system.util.Util;

import java.util.HashMap;
import java.util.List;

public class IbsAssetDrawBean extends MobileBase
{
	@Override
	public boolean save(String tablename, HashMap paramMap, HashMap otherParam)
	throws Exception
	{
		Ibsassetdraw ibsassetdraw;
		String strID = (String)paramMap.get("id");
		String adnum = (String)paramMap.get("adnum");
		String drawtype = (String)paramMap.get("drawtype");
		if(adnum==null || drawtype==null)
			throw new Exception("����ͼ���ļ���������ʱ�������ݴ���");

		List list = IBSServer.getIBSServer().getBaseDao().findWithQuery(Ibsassetdraw.class, "adnum='"+adnum+"' and drawtype='"+drawtype+"'");
		if(list.size()>0)
		{
			ibsassetdraw = (Ibsassetdraw) list.get(0);
			if(strID==null)
				ibsassetdraw.setDrawver(ibsassetdraw.getDrawver() + 1);
			else
			{
				ibsassetdraw.setAddesc((String)paramMap.get("addesc"));
				ibsassetdraw.setParent((String)paramMap.get("parent"));
				ibsassetdraw.setRemark((String)paramMap.get("remark"));
			}
		}
		else //�½���¼
		{
			ibsassetdraw = new Ibsassetdraw();
			ibsassetdraw.setDrawver(1L);
			ibsassetdraw.setAdnum(adnum);
			ibsassetdraw.setDrawtype(drawtype);
			if(drawtype.equals("�豸ͼ"))
				ibsassetdraw.setFilepath("/equipment/" + adnum + ".adx");
			else if(drawtype.equals("λ��ͼ"))
				ibsassetdraw.setFilepath("/location/" + adnum + ".adx");
			else
				ibsassetdraw.setFilepath("/custom/" + adnum + ".adx");
				
			ibsassetdraw.setAddesc((String)paramMap.get("addesc"));
			ibsassetdraw.setParent((String)paramMap.get("parent"));
			ibsassetdraw.setRemark((String)paramMap.get("remark"));
		}
		
		
		try
		{
			//�������ҵ�����save�������Զ��ж����½����Ǹ���
			//����ֱ����saveObject
			IBSServer.getIBSServer().getBaseSrv().save(ibsassetdraw);
		}
		catch(Exception e)
		{
			throw new Exception("ִ�����ݿⱣ��ʱ����:" + e.getMessage());
		}
		return true;
	}
	
}
