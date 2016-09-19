package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.business.inventory.ItemSrv;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.ICombobox;

public class ItemWindow extends MainWindow implements InfWindow {
	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ItemWindow() {
		super();
	}

	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Item item = (Item) this.getMainObject();
		ICombobox combox = (ICombobox) this.getFellow("item.lottype");
		String rota = item.getRotating();
		if (rota != null) {
			if (item.getRotating().equals("��")) {
				this.setReadonly(combox);
			} else {
				this.setNoReadonly(combox);
			}
		}

		super.initData();
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
		Item newobj = new Item();
		newobj.setItemnum(this.genAutokey("itemnum"));
		newobj.setRotating("��");
		newobj.setLottype("������");
		newobj.setOutside("��");
		newobj.setSpareautoadd("��");
		newobj.setInspectreq("��");

		mainObject = newobj;
		return true;
	}

	/**
	 * ����createtoinventory()
	 * 
	 * ���ߣ���� ���ܣ������ʱ���ֱ�����ɵ������� ���ڣ�Sep 23, 2008 2:15:41 PM
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void createtoinventory() throws Exception {
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���ɲ���ǰ�����ȱ��浱ǰ�ļ�¼��");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ������ɲ�����");
			return;
		}
		Item item = (Item) this.getMainObject();
		// ����ָ���Ĵ���
		Inventory inventory = new Inventory();
		inventory.setItemnum(item.getItemnum());
		inventory.setOrderunit(item.getOrderunit());
		inventory.setIssueunit(item.getIssueunit());
		inventory.setModelnum(item.getModelnum());
		inventory.setStocktype("�������");
		inventory.setConversion(1D);
		inventory.setIssueytd(0D);

		//inventory.setSitenum(this.getLaborInfo().getSitenum());
		//inventory.setCorpnum(this.getLaborInfo().getCorpnum());

		this.popupDialog((Object) inventory, "/inventory/inventorypopup.xul");

		this.refreshData();

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ�ά��������λ ���ڣ�2008-4-14 ����08:27:14
	 * 
	 */
	public void measure() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this
				.popupDialog(this.getMainObject(),
						"/inventory/measureunit.xul", ""); // "tablename='IBSAPPS'"

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ���������Ŀ��ת��ϵ�� ���ڣ�Oct 15, 2008 2:07:53 PM
	 * 
	 */
	public void conversion() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog(this.getMainObject(), "/inventory/conversion.xul", "");

	}

	/**
	 * ������createeqnum ���ߣ���� ���ܣ�����bom���� ���ڣ�Feb 24, 2009 3:31:46 PM
	 */
	public void createeqnum() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ������ɲ�����");
			return;
		}
		int rtn = Messagebox.show("�Ƿ�ȷ�����ɱ����嵥��", "ȷ�����ɣ�", Messagebox.YES
				| Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			Item item = (Item) this.getMainObject();
			if (!(item.getRotating().equals("��"))) {
				throw new Exception("����ת�����������ɲ�����");
			}
			// ��ѯ�Ƿ��б���
			/*
			 * List itemeqbomlist =
			 * this.getMainSrv().getBaseDao().findWithQuery( Itemeqbom.class,
			 * "parent='" + item.getItemnum() + "'"); if (itemeqbomlist.size() <=
			 * 0) { Messagebox.show("�������������嵥���������ɱ����嵥��"); return; }
			 */
			ItemSrv itemsrv = (ItemSrv) this.getMainSrv();
			itemsrv.createeqnum(item);
			Messagebox.show("����BOM������ɣ�");
		}
	}

	/**
	 * ����
	 * 
	 * ���ߣ���� ���ܣ����޸�����Ϊ��ת����ʱ����֤������Ϊ��
	 * 
	 */
	
	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��б��������");
			return;
		}
		Item item = (Item) this.getMainObject();//�õ������ϵĶ���
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+item.getItemnum()+"'");
		Item olditem =(Item) itemlist.get(0);
		if (item.getId() != null) {//˵�������½���¼
			if (!(item.getRotating().equals(olditem.getRotating()))) {
				if ("��".equals(item.getRotating())) {
					int rtn = Messagebox.show("�Ƿ�ȷ���޸�Ϊ��ת����", "ȷ���޸ģ�", Messagebox.YES
							| Messagebox.NO, Messagebox.QUESTION);
					if (rtn == Messagebox.NO)
						return;
					else if (rtn == Messagebox.YES) {
						//�õ�����Ƿ����������У������޸�
						List invlist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+item.getItemnum()+"'");
						/*Double culbar = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(i.culbar) from Invstock i where i.itemnum='"+item.getItemnum()+"'");
						if (culbar!=null && culbar>0) {
							Messagebox.show("���ʿ��������Ϊ�㣬�����޸ĳ���ת����");
							return;
						}*/
						if (invlist.size()>0) {
							Messagebox.show("���ʿ��������Ϊ�㣬�����޸ĳ���ת����");
							return;
						}
						}
				}
			}
			
		}
		
		super.save();
	}
	
	public void intowarehouse() throws Exception {
		this.popupDialog(this.getMainObject(), "/test/warehousedialog.xul", "");
	}
}
