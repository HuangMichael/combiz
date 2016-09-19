package combiz.business.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemeqbom;
import combiz.system.IBOBaseImpl;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.domain.equipment.Eqsparepart;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.RecWindow;
import com.inbasis.zk.ui.Component;
import combiz.domain.inventory.Itemspec;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class ItemImpl extends IBOBaseImpl implements ItemSrv {
	ArrayList itemcount = new ArrayList();
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Item))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ��������itemspec
		this.deleteAllChild(obj, "itemspec");
	}

	/**
	 * ���淽��
	 * 
	 * brianhong 2007-11-8
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void save(Object obj) throws Exception {
		if (!(obj instanceof Item))
			throw new Exception("Ҫ����Ķ��󴫵ݲ���ȷ��");

		Item item = (Item) obj;
		if (item.getId() == null)// �½�
		{
			// ������������
			if (item.getClassid() != null)
				this.genItemspec(item);
		} else {
			RecWindow recWnd = this.getRecWnd();
			Component cmp = recWnd.getFellow("item.classid");
			if (cmp != null && cmp.isModified() && item.getClassid() != null) {
				// ���¼�������
				List itemspecList = this.findByRelation(item, "itemspec");
				if (itemspecList.size() <= 0) {
					this.genItemspec(item);
				}
			}
		}

		super.save(item);
	}

	/**
	 * �Ӽ�������ģ�忽������������ITEMSPEC brianhong 2007-11-8
	 * 
	 * @param item
	 * @throws Exception
	 */
	public void genItemspec(Item item) throws Exception {
		List classspecList = this.getBaseDao().findWithQuery(Classspec.class,
				"classid='" + item.getClassid() + "'");
		if (classspecList.size() > 0) {
			for (int i = 0; i < classspecList.size(); i++) {
				Classspec classpec = (Classspec) classspecList.get(i);
				Itemspec itemspec = new Itemspec();
				itemspec.setItemnum(item.getItemnum());
				itemspec.setClassid(classpec.getClassid());
				itemspec.setClassattr(classpec.getClassattr());
				itemspec.setUnitid(classpec.getUnitid());
				itemspec.setChangeby(this.getUserInfo().getLabornum());
				itemspec.setChangedate(new Date());
				itemspec.setIsmustbe(classpec.getIsmustbe());
				itemspec.setRemark(classpec.getRemark());
				super.save(itemspec);
			}
		}
	}

	/**
	 * ����
	 * 
	 * ���ߣ���� ���ܣ�classance���в������Ĺ�ϵ ���ڣ�Sep 1, 2008 11:53:21 AM
	 * 
	 */
	public void cretetree(List classlist) throws Exception {
		// List classlist =
		// this.getBaseDao().findWithQuery(Classification.class, "classtype =
		// '����' and parent is null");
		if (classlist.size() > 0) {
			for (int i = 0; i < classlist.size(); i++) {
				Classification classification = (Classification) classlist
						.get(i);
				Long classid = classification.getId();
				if (classification.getId() != null) // �½�
				{
					this.getAnce(classification);
				}
			}
		}

	}

	public void getclass(List classlist) throws Exception {
		Classification classification = null;
		Classance classance = new Classance();
		if (classlist != null && classlist.size() > 0) {
			for (int n = 0; n < classlist.size(); n++) {
				classification = (Classification) classlist.get(n);
				classance = new Classance();
				classance.setClassid(classification.getParent());
				classance.setAncestor(classification.getClassid());
				this.getBaseDao().saveObject(classance);

				this.getAnce(classification);
			}

		}
	}

	public ArrayList getAnce(Classification classification) throws Exception {
		List parentList = this.getBaseDao().findWithQuery(Classification.class,
				"parent = '" + classification.getClassid() + "'");
		if (parentList.size() > 0) {
			this.getclass(parentList);
			return (ArrayList) parentList;
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * ���ߣ���� ���ܣ������ʱ��봴��������� ���ڣ�Sep 24, 2008 3:59:30 PM
	 * 
	 */
	public void createinventory(Inventory inventory) throws Exception {
		if (!(inventory instanceof Inventory))
			throw new Exception("���󴫵ݲ���ȷ!");
		Inventory invent = inventory;
		this.getBaseDao().saveObject(inventory);

		// �ж��Ƿ�����������������������¼
		Double invcurbal = invent.getInvcurbal();
		Double invphycnt = invent.getInvphycnt();
		Date invphydate = invent.getInvphydate();

		if (invcurbal != null && invcurbal >= 0) {
			if (invphycnt == null || invphycnt <= 0) {
				throw new Exception("������Ϊ��ʱ�������̵���");
			}
			Invstock invstock = new Invstock();
			invstock.setItemnum(invent.getItemnum());
			invstock.setCurbal(inventory.getInvcurbal());
			invstock.setPhyscnt(invent.getInvphycnt());
			invstock.setWarehouse(invent.getWarehouse());
			invstock.setBinnum(invent.getBinnum());
			//invstock.setSitenum(invent.getSitenum());
			//invstock.setCorpnum(invent.getCorpnum());

			if (invent.getInvphydate() != null) {
				invstock.setPhyscntdate(invent.getInvphydate());
			}
			if ((invcurbal - invphycnt) != 0) {
				invstock.setReconciled("��");
			} else {
				invstock.setReconciled("��");
			}
			this.getBaseDao().saveObject(invstock);
			// ͬʱ����һ�����������ļ�¼
			Invtrans invtrans = new Invtrans();
			invtrans.setConversion(invent.getConversion());
			invtrans.setCurbal(invcurbal);
			invtrans.setPhyscnt(invphycnt);
			invtrans.setOldcost(invent.getAvgcost());
			invtrans.setNewcost(invent.getAvgcost());

			invtrans.setWarehouse(invent.getWarehouse());
			//invtrans.setCorpnum(invent.getCorpnum());
			invtrans.setEnterby(this.getLaborInfo().getLabornum());
			invtrans.setItemnum(invent.getItemnum());
			//invtrans.setSitenum(invent.getSitenum());
			invtrans.setTransdate(new Date());
			invtrans.setTranstype("��������");
			invtrans.setQuantity(invent.getInvcurbal());
			invtrans.setLinecost(invent.getAvgcost() * invcurbal);

			this.getBaseDao().saveObject(invtrans);
		}
	}

	/**
	 * ������createeqnum ���ߣ���� ���ܣ�����bom���� ���ڣ�Feb 24, 2009 3:31:46 PM
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see combiz.business.inventory.ItemSrv#createeqnum(java.lang.Object,
	 *      java.util.List)
	 */
	public void createeqnum(Object obj) throws Exception {
		if (!(obj instanceof Item))
			throw new Exception("���󴫵ݲ���ȷ!");
		Item item = (Item) obj;

		String itemnum = item.getItemnum();
		itemcount.add(itemnum);

		List equiplist = this.getBaseDao().findWithQuery(Equipment.class,
				"itemnum='" + itemnum + "'");

		if (equiplist.size() > 0) {
			for (int i = 0; i < equiplist.size(); i++) {
				Equipment equipment = (Equipment) equiplist.get(i);
				String eqnum = equipment.getEqnum();

				// ��ѯ���豸���Ƿ��иñ������У��������ٴ����
				List list = this.getBaseDao().findWithQuery(Itemeqbom.class, "parent='"+itemnum+"'");
				for (int n = 0; n < list.size(); n++) {
					Itemeqbom itemeqbom = (Itemeqbom) list.get(n);
					// �ж������������ת���������ɱ���
					int ifro = this.getBaseDao().selectCountByHql(
							"select count(*) from Item i where i.itemnum='"
									+ itemeqbom.getItemnum()
									+ "' and i.rotating='��'");
					if (ifro <= 0) {
						int count = this.getBaseDao().selectCountByHql(
								"select count(*) from Eqsparepart e where e.eqnum='"
										+ eqnum + "' and e.itemnum='"
										+ itemeqbom.getItemnum() + "'");
						if (count <= 0) {
							Eqsparepart newpart = new Eqsparepart();
							newpart.setEqnum(eqnum);
							newpart.setItemnum(itemeqbom.getItemnum());
							newpart.setDescription(itemeqbom.getRemark());
							newpart.setQuantity(itemeqbom.getQuanity());
							this.getBaseDao().saveObject(newpart);
						} else {
							List eqsplist = this.getBaseDao().findWithQuery(
									Eqsparepart.class,
									" eqnum='" + eqnum + "' and itemnum='"
											+ itemeqbom.getItemnum() + "'");
							Eqsparepart eqsparepart = (Eqsparepart) eqsplist
									.get(0);
							eqsparepart.setItemnum(itemeqbom.getItemnum());
							eqsparepart.setDescription(itemeqbom.getRemark());
							eqsparepart.setQuantity(itemeqbom.getQuanity());
							this.getBaseDao().updateObject(eqsparepart);
						}
					} else {
						// �������ת��������Ȼ��BOM�嵥���򣬼������ɣ��������
						
							if (!(itemcount.contains(itemeqbom.getItemnum()))){
								List theitemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemeqbom.getItemnum()+"'");
								Item theitem = (Item) theitemlist.get(0);
								this.createeqnum(theitem);
							}
							
					}
				}
			}
		}
	}
	// ///////////////////ҵ�񷽷�//////////////////////////////////
}
