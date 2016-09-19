package combiz.ui.inventory;


import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InventorySrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invusetrans;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class IssueInventoryDialog 
extends CommonDialog
{
	
	public IssueInventoryDialog()
	{
		super();
	}
	public void onCreate()
	throws Exception
	{
		super.onCreate();
	}
	

	public void issue() 
	throws Exception
	{
		
		//ToolSrv toolsrv = (ToolSrv) this.getMainSrv();
		InventorySrv invsrv = (InventorySrv) IBOSrvUtil.getSrv("inventory");
		Invusetrans  invusetrans = (Invusetrans) this.getMainObject();
		MainWindow inventorywnd =(MainWindow) this.getOwnerWnd();
		Inventory inventory = (Inventory) inventorywnd.getMainObject();
		String location = invusetrans.getLocation();
		String issueto = invusetrans.getIssuetolabor();
//		String tobin = invrectrans.getTobin();
//		String frombin = invrectrans.getFrombin();
//		Double curbal = invrectrans.getCurbal();
		Double quantity = invusetrans.getQuantity();
		
		if(Util.isNull(location)&&Util.isNull(issueto))
		{
			throw new Exception("����λ�úͷ�����Ա����ͬʱΪ��,��ȷ��!");
		}
//		if((quantity == null)||quantity<0)
//		{
//			invrectrans.setQuantity(null);
//			this.refreshData();
//			throw new Exception("ת������Ϊ�ջ���Ϊ��ֵ,��ȷ��!");
//		}
//		if(quantity - curbal >0)
//		{
//			throw new Exception("ת����������ԭ����ϴ�����������ܵ���,��ȷ��!");
//		}
//
//		if (!(invrectrans instanceof Invrectrans))
//			throw new Exception("��ѡ��һ����¼!");
		invsrv.issue(invusetrans);
		this.detach();
	}
	
	
	
}
