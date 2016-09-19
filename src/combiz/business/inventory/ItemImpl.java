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
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class ItemImpl extends IBOBaseImpl implements ItemSrv {
	ArrayList itemcount = new ArrayList();
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Item))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联表itemspec
		this.deleteAllChild(obj, "itemspec");
	}

	/**
	 * 保存方法
	 * 
	 * brianhong 2007-11-8
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void save(Object obj) throws Exception {
		if (!(obj instanceof Item))
			throw new Exception("要保存的对象传递不正确！");

		Item item = (Item) obj;
		if (item.getId() == null)// 新建
		{
			// 拷贝技术参数
			if (item.getClassid() != null)
				this.genItemspec(item);
		} else {
			RecWindow recWnd = this.getRecWnd();
			Component cmp = recWnd.getFellow("item.classid");
			if (cmp != null && cmp.isModified() && item.getClassid() != null) {
				// 更新技术参数
				List itemspecList = this.findByRelation(item, "itemspec");
				if (itemspecList.size() <= 0) {
					this.genItemspec(item);
				}
			}
		}

		super.save(item);
	}

	/**
	 * 从技术参数模板拷贝技术参数到ITEMSPEC brianhong 2007-11-8
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
	 * 方法
	 * 
	 * 作者：李建红 功能：classance表中插入数的关系 日期：Sep 1, 2008 11:53:21 AM
	 * 
	 */
	public void cretetree(List classlist) throws Exception {
		// List classlist =
		// this.getBaseDao().findWithQuery(Classification.class, "classtype =
		// '物资' and parent is null");
		if (classlist.size() > 0) {
			for (int i = 0; i < classlist.size(); i++) {
				Classification classification = (Classification) classlist
						.get(i);
				Long classid = classification.getId();
				if (classification.getId() != null) // 新建
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
	 * 方法
	 * 
	 * 作者：李建红 功能：从物资编码创建到库存中 日期：Sep 24, 2008 3:59:30 PM
	 * 
	 */
	public void createinventory(Inventory inventory) throws Exception {
		if (!(inventory instanceof Inventory))
			throw new Exception("对象传递不正确!");
		Inventory invent = inventory;
		this.getBaseDao().saveObject(inventory);

		// 判断是否有余量，否则不生成余量记录
		Double invcurbal = invent.getInvcurbal();
		Double invphycnt = invent.getInvphycnt();
		Date invphydate = invent.getInvphydate();

		if (invcurbal != null && invcurbal >= 0) {
			if (invphycnt == null || invphycnt <= 0) {
				throw new Exception("余量不为空时，物理盘点必填！");
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
				invstock.setReconciled("否");
			} else {
				invstock.setReconciled("是");
			}
			this.getBaseDao().saveObject(invstock);
			// 同时生成一条调整余量的记录
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
			invtrans.setTranstype("调整余量");
			invtrans.setQuantity(invent.getInvcurbal());
			invtrans.setLinecost(invent.getAvgcost() * invcurbal);

			this.getBaseDao().saveObject(invtrans);
		}
	}

	/**
	 * 方法：createeqnum 作者：李建红 功能：部件bom生成 日期：Feb 24, 2009 3:31:46 PM
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see combiz.business.inventory.ItemSrv#createeqnum(java.lang.Object,
	 *      java.util.List)
	 */
	public void createeqnum(Object obj) throws Exception {
		if (!(obj instanceof Item))
			throw new Exception("对象传递不正确!");
		Item item = (Item) obj;

		String itemnum = item.getItemnum();
		itemcount.add(itemnum);

		List equiplist = this.getBaseDao().findWithQuery(Equipment.class,
				"itemnum='" + itemnum + "'");

		if (equiplist.size() > 0) {
			for (int i = 0; i < equiplist.size(); i++) {
				Equipment equipment = (Equipment) equiplist.get(i);
				String eqnum = equipment.getEqnum();

				// 查询该设备下是否有该备件，有，则无须再次添加
				List list = this.getBaseDao().findWithQuery(Itemeqbom.class, "parent='"+itemnum+"'");
				for (int n = 0; n < list.size(); n++) {
					Itemeqbom itemeqbom = (Itemeqbom) list.get(n);
					// 判断如果备件费周转件，则不生成备件
					int ifro = this.getBaseDao().selectCountByHql(
							"select count(*) from Item i where i.itemnum='"
									+ itemeqbom.getItemnum()
									+ "' and i.rotating='是'");
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
						// 如果该周转件下面仍然有BOM清单，则，继续生成，否则结束
						
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
	// ///////////////////业务方法//////////////////////////////////
}
