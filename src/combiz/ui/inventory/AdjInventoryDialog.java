package combiz.ui.inventory;


import java.util.Date;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InventorySrv;
import combiz.business.inventory.ItemSrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Item;
import combiz.domain.tool.Tool;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class AdjInventoryDialog 
extends CommonDialog
{
	
	public AdjInventoryDialog()
	{
		super();
	}
	public void onCreate()
	throws Exception
	{
		super.onCreate();
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ���������
	 * ���ڣ�Jun 18, 2008 8:02:29 AM
	 *
	 */
	public void curbalnum() 
	throws Exception
	{
		
		//ToolSrv toolsrv = (ToolSrv) this.getMainSrv();
		InventorySrv invsrv = (InventorySrv) IBOSrvUtil.getSrv("inventory");
		Invtrans  invtrans = (Invtrans) this.getMainObject();
		MainWindow inventorywnd =(MainWindow) this.getOwnerWnd();
		Inventory inventory = (Inventory) inventorywnd.getMainObject();
		String binnum = invtrans.getBinnum();
		//String frombin = invrectrans.getFrombin();
		Double curbal = invtrans.getCurbal();
		Double quantity = invtrans.getQuantity();
		
//		if(!Util.isNotNull(binnum))
//		{
//			throw new Exception("���Ų���Ϊ��,��ȷ��!");
//		}
		if((quantity == null)||quantity<0)
		{
			invtrans.setQuantity(null);
			this.refreshData();
			throw new Exception("��������Ϊ�ջ���Ϊ��ֵ,��ȷ��!");
		}
		

		if (!(invtrans instanceof Invtrans))
			throw new Exception("��ѡ��һ����¼!");
		invsrv.editcurbal(invtrans);
		this.detach();
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ����������̵�
	 * ���ڣ�Jun 18, 2008 8:02:29 AM
	 *
	 */
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�����ƽ���ɱ�
	 * ���ڣ�Jun 18, 2008 8:04:00 AM
	 *
	 */
	public void createinventory() 
	throws Exception
	{
		ItemSrv itemsrv = (ItemSrv) IBOSrvUtil.getSrv("item");
		Inventory  inventory = (Inventory) this.getMainObject();
		MainWindow itemwnd =(MainWindow) this.getOwnerWnd();
		Item item = (Item) itemwnd.getMainObject();
		//String binnum = invtrans.getBinnum();
		//String frombin = invrectrans.getFrombin();
		Double avgcost = inventory.getAvgcost();
		Double stdcost = inventory.getStdcost();
		Double lastcost = inventory.getLastcost();
		
		Double minlevel = inventory.getMinlevel();
		Double maxlevel = inventory.getMaxlevel();
		Double orderqty = inventory.getOrderqty();
		
		Double invcurbal = inventory.getInvcurbal();
		Double invphycnt = inventory.getInvphycnt();
		Date invphydate = inventory.getInvphydate();
		
		if (avgcost == null) {
			avgcost = 0D;
		}
		if (stdcost == null) {
			stdcost = 0D;
		}
		if (lastcost == null) {
			lastcost = 0D;
		}
		if (avgcost < 0) {
			inventory.setAvgcost(0D);
			this.refreshData();
			throw new Exception("����ƽ���۸���С��0,���ʵ!");
		}
		if (stdcost < 0) {
			inventory.setStdcost(0D);
			this.refreshData();
			throw new Exception("���ʱ�׼�ɱ�����С��0,���ʵ!");
		}
		if (lastcost < 0) {
			inventory.setLastcost(0D);
			this.refreshData();
			throw new Exception("�����ϴγɱ�����С��0,���ʵ!");
		}
		
		if (minlevel == null) {
			minlevel = 0D;
		}
		if (maxlevel == null) {
			maxlevel = 0D;
		}
		if (orderqty == null) {
			orderqty = 0D;
		}
		if (minlevel < 0) {
			inventory.setMinlevel(0D);
			this.refreshData();
			throw new Exception("��Ϳ�治��С��0,���ʵ!");
		}
		if (maxlevel < 0) {
			inventory.setMaxlevel(0D);
			this.refreshData();
			throw new Exception("��ȫ��治��С��0,���ʵ!");
		}
		if (orderqty < 0) {
			inventory.setOrderqty(0D);
			this.refreshData();
			throw new Exception("���ö�������С��0,���ʵ!");
		}
		
		if(invcurbal == null) {
			invcurbal = 0D;
		}
		
		if(invcurbal < 0) {
			inventory.setInvcurbal(0D);
			this.refreshData();
			throw new Exception("��������С��0,���ʵ!");
		}
		
		if (invphycnt == null) {
			invphycnt = 0D;
		}
		
		if (invphycnt < 0) {
			inventory.setInvphycnt(0D);
			this.refreshData();
			throw new Exception("�����̵���������С��0,���ʵ!");
		}
		
		if (inventory.getWarehouse() == null || inventory.getWarehouse().equals("")) {
			inventory.setWarehouse(null);
			this.refreshData();
			throw new Exception("��ѡ��ֿ� ��");
		}
		
		if (!(item instanceof Item))
			throw new Exception("��ѡ��һ����¼!");
		itemsrv.createinventory(inventory);
		this.detach();
	}
}
