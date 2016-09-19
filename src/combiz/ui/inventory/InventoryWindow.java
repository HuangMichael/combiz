package combiz.ui.inventory;

import java.util.Date;
import java.util.List;

import combiz.business.inventory.InventorySrv;
import combiz.business.inventory.InvstockSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tab;

public class InventoryWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InventoryWindow() {
		super();
	}

	/**
	 * ������¼
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Inventory newobj = new Inventory();

		mainObject = newobj;
		newobj.setConversion(1.00);
		return true;
	}
	
	
	public void initData() throws Exception {
		// TODO �Զ����ɷ������
		/*Tab list = (Tab) this.getFellow("invreservetab");
		list.setVisible(false);*/
		super.initData();
		
		
	}




	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����������Ŀ���
	 * ���ڣ�2008-4-14 ����01:35:23
	 *
	 */
	public void adjustbinnum() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��е�����������");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		
		Inventory inv = (Inventory)this.getMainObject();
		
		if (inv.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		
		Invrectrans irt = new Invrectrans();
		irt.setItemnum(inv.getItemnum());
		irt.setDescription(inv.getItemdesc());
		irt.setFromwarehouse(inv.getWarehouse());
		irt.setTowarehouse(inv.getWarehouse());
		irt.setRecunit(inv.getIssueunit());
		irt.setUnitcost(inv.getAvgcost());
		irt.setActualcost(inv.getAvgcost());
		irt.setOldavgcost(inv.getAvgcost());
		irt.setLinecost(0d);
		irt.setLoadedcost(0d);
		irt.setReqby(this.getUserInfo().getLabornum());
		irt.setExchangerate(1.0);
		irt.setRejectqty(0d);
	    irt.setConversion(1d);
	    irt.setRectype("TRANSFER");
	    irt.setStatus("��ȷ��");
	    irt.setCurbal(0.0d);
	    irt.setChangeby(this.getUserInfo().getLabornum());
		irt.setTransdate(new Date());
		irt.setActualdate(new Date());
		//irt.setCorpnum(inv.getCorpnum());
		//irt.setSitenum(inv.getSitenum());
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog((Object)irt, "/inventory/invstockpopup.xul");
//		this.refreshChildData();
		this.refreshData();

	}
	public void issue() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��Ҫ���ŵĿ����Ŀ��Ȼ���ٽ��з��Ų�����");
			return;
		}
		
        Inventory inv = (Inventory)this.getMainObject();
		
        Invusetrans invuse = new Invusetrans();
		invuse.setItemnum(inv.getItemnum());
		invuse.setDescription(inv.getItemdesc());
		invuse.setWarehouse(inv.getWarehouse());
		invuse.setIssuetype("����");
		invuse.setTransdate(new Date());
		invuse.setActualdate(new Date());
		List invstock = this.getMainSrv().getBaseDao().findWithQuery(
				Invstock.class,
				"itemnum = '" + inv.getItemnum()
						+ "' and warehouse = '" + inv.getWarehouse()
						+ "'");
		Invstock curbal = (Invstock) invstock.get(0);
		invuse.setCurbal(curbal.getCurbal());
	
		invuse.setConversion(1.0);
		invuse.setPhyscnt(0d);
		invuse.setEnterby(this.getLaborInfo().getLabornum());
		//invuse.setCorpnum(inv.getCorpnum());
		//invuse.setSitenum(inv.getSitenum());
		
	
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog((Object)invuse, "/inventory/invissuepopup.xul");
//		this.refreshChildData();
		this.refreshData();

}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ��ض��������Ŀ
	 * ���ڣ�Oct 24, 2008 12:25:44 PM
	 *
	 */
	public void autogenpo() throws Exception {
		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ����ض��������Ŀ������");
			return;
		}
		Inventory inven = (Inventory) this.getMainObject();
		List list = this.getMainSrv().getBaseDao().findWithQuery(Inventory.class, "stocktype='�������' ");
		if (list.size()>0)
		{
			int count = ((InventorySrv)this.getMainSrv()).autogenerate(list);
			
			this.refreshData();
			Messagebox.show("�Ѿ��ɹ��ض�������'"+count+"'�вɹ�������");
		}
		else
		{
			Messagebox.show("û�����������ض����Ŀ���¼��");
		}
			
		
		
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�������������
	 * ���ڣ�Jun 17, 2008 2:55:33 PM
	 *
	 */
	public void editcurbal() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��е�������������");
			return;
		}
		//�ж����
		/*Boolean flage = this.check();
		if (flage != true){
			throw new Exception("��û��Ȩ�޲����òֿ⣡");
		}*/
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		
		Inventory inv = (Inventory)this.getMainObject();
		
		Invtrans invtrans = new Invtrans();
		
		//invtrans.setBinnum(inv.getBinnum());
		invtrans.setConversion(inv.getConversion());
		invtrans.setCurbal(0D);
		invtrans.setOldcost(inv.getAvgcost());
		invtrans.setNewcost(inv.getAvgcost());
		
		invtrans.setWarehouse(inv.getWarehouse());
		//invtrans.setCorpnum(inv.getCorpnum());
		invtrans.setEnterby(this.getLaborInfo().getLabornum());
		invtrans.setItemnum(inv.getItemnum());
		//invtrans.setSitenum(inv.getSitenum());
		//invtrans.setTransdate(new Date());
		invtrans.setTranstype("��������");
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog((Object)invtrans, "/inventory/invcurbalpopup.xul");
//		this.refreshChildData();
		this.refreshData();
	}
	
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ��������������̵�
	 * ���ڣ�Jun 17, 2008 2:55:33 PM
	 *
	 */
	public void editphyscnt() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��е��������̵������");
			return;
		}
		//�ж����
		/*Boolean flage = this.check();
		if (flage != true){
			throw new Exception("��û��Ȩ�޲����òֿ⣡");
		}*/
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		
		Inventory inv = (Inventory)this.getMainObject();
		
		Invtrans invtrans = new Invtrans();
		
		//invtrans.setBinnum(inv.getBinnum());
		invtrans.setConversion(inv.getConversion());
		invtrans.setCurbal(0D);
		invtrans.setPhyscnt(0D);
		invtrans.setOldcost(inv.getAvgcost());
		invtrans.setNewcost(inv.getAvgcost());
		
		
		invtrans.setWarehouse(inv.getWarehouse());
		//invtrans.setCorpnum(inv.getCorpnum());
		invtrans.setEnterby(this.getLaborInfo().getLabornum());
		invtrans.setItemnum(inv.getItemnum());
		//invtrans.setSitenum(inv.getSitenum());
		invtrans.setTransdate(new Date());
		invtrans.setTranstype("���������̵�");
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog((Object)invtrans, "/inventory/invphyscntpopup.xul");
//		this.refreshChildData();
		this.refreshData();
	}
	
	/**
	 * ����resetavgcost()
	 * 
	 * ���ߣ����
	 * ���ܣ���������ƽ���ɱ�
	 * ���ڣ�Jun 18, 2008 7:37:23 AM
	 *
	 */
	public void resetavgcost() throws Exception {

		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��е���ƽ���ɱ�������");
			return;
		}
		
		Inventory inv = (Inventory)this.getMainObject();
		
		Invtrans invtrans = new Invtrans();
		
		invtrans.setBinnum(inv.getBinnum());
		invtrans.setConversion(inv.getConversion());
		invtrans.setCurbal(0D);
		invtrans.setOldcost(inv.getAvgcost());
		invtrans.setWarehouse(inv.getWarehouse());
		//invtrans.setCorpnum(inv.getCorpnum());
		invtrans.setEnterby(this.getLaborInfo().getLabornum());
		invtrans.setItemnum(inv.getItemnum());
		//invtrans.setSitenum(inv.getSitenum());
		invtrans.setTransdate(new Date());
		invtrans.setTranstype("����ƽ���ɱ�");
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog((Object)invtrans, "/inventory/invavgcostpopup.xul");
//		this.refreshChildData();
		this.refreshData();
	}
}
