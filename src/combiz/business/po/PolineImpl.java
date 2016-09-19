package combiz.business.po;

import combiz.domain.equipment.Eqsparepart;
import combiz.domain.inventory.Favorite;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemeqbom;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.IBOBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class PolineImpl extends IBOBaseImpl implements PolineSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		Poline poline = (Poline) obj;
		if (!(obj instanceof Poline))
			throw new Exception("要删除的对象传递不正确！");

		// this.deleteAllChild(obj, "");
		// 删除行的时候修改采购单的行成本
		Double totalcost = 0d;
		if (this.getRecWnd() != null) {
			Po po = (Po) this.getRecWnd().getOwnerWnd().getMainObject();
			totalcost = po.getTotalcost();
			if (poline.getId() != null) {
				// Poline storepl = (Poline)
				// this.getBaseDao().findUniqueBy(Poline.class,"id",poline.getId());
				Double dblinecost = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.linecost) from Poline t where t.id = '"
								+ poline.getId() + "'");
				if (dblinecost == null) {
					dblinecost = 0d;
				}
				totalcost = totalcost - dblinecost;

			} else {
				totalcost = totalcost - poline.getLinecost();
			}
			po.setTotalcost(totalcost);
			this.getBaseDao().updateObject(po);
			
			

		}
		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
	}

	@Override
	public void save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Poline poline = (Poline) obj;
		boolean hasrotating = false;
		boolean hasnorotating = false;
		String itemnum = poline.getItemnum();
		int i = this.getBaseDao().selectCountByHql(
				"select count(*) from Item t where t.itemnum = '" + itemnum
						+ "' and t.rotating ='是'");
		if (i > 0) {
			hasrotating = true;
		} else {
			hasnorotating = true;
		}
		List polinelist = this.getBaseDao().findWithQuery(Poline.class,
				"ponum='" + poline.getPonum() + "' and id <> null");
		if (polinelist.size() > 0) {
			for (int m = 0; m < polinelist.size(); m++) {
				Poline poline2 = (Poline) polinelist.get(m);
				int haszzcount = this.getBaseDao().selectCountByHql(
						"select count(*) from Item t where t.itemnum = '"
								+ poline2.getItemnum()
								+ "' and t.rotating ='是'");
				if (haszzcount > 0) {
					hasrotating = true;
				} else {
					hasnorotating = true;
				}
			}

		}
		if (hasrotating && hasnorotating) {
			throw new Exception("采购订单不允许同时采购物资和设备!");
		}

		if (this.getRecWnd() != null) {
			if (this.getRecWnd().getOwnerWnd() != null) {
				if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Po) {
					Po po = (Po) this.getRecWnd().getOwnerWnd().getMainObject();
					Double totalcost = po.getTotalcost();
					if (totalcost == null)
						totalcost = 0d;
					if (poline.getId() == null) // 是新建记录
					{
						totalcost = totalcost + poline.getLinecost();

					} else // 更新记录
					{
						Double dblinecost = (Double) this.getBaseDao()
								.selectSumByHql(
										"select sum(p.linecost) from Poline p where p.id = '"
												+ poline.getId() + "'");
						if (dblinecost == null) {
							dblinecost = 0d;
						}
						totalcost = totalcost - dblinecost
								+ poline.getLinecost();
					}
					po.setTotalcost(totalcost);
					this.getBaseDao().updateObject(po);
				}
			}
		}

		super.save(obj);

	}

	public void addPoline(List list, Object obj) throws Exception {
		Po po = (Po) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Favorite favorite = (Favorite) list.get(i);
				Poline poline = new Poline();
				poline.setPonum(po.getPonum());
				int PolInt = this.getRowCountByWhere(poline, "ponum='"
						+ po.getPonum() + "'");
				poline.setPolinenum((long) PolInt + 1);
				poline.setWarehouse(this.getUserInfo().getDefstoreroom());
				poline.setOrderqty(0.00);
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ favorite.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					poline.setOrderunit(item.getOrderunit());
					poline.setDescription(item.getDescription());
				} else {
					poline.setOrderunit("件");
				}
				poline.setProrated("否");
				poline.setReceiptscomplete("否");
				poline.setService("否");
				poline.setRejectedqty(0.00);
				poline.setReceivedtotalcost(0.00);
				poline.setReceivedunitcost(0.00);
				poline.setConversion(1.00);
				poline.setLinecost(0.00);
				poline.setUnitcost(0.00);
				poline.setLoadedcost(0.00);
				poline.setInspection("否");
				poline.setEnterby(this.getUserInfo().getLabornum());
				poline.setEnterdate(new Date());
				//poline.setCorpnum("INBASIS");
				poline.setItemnum(favorite.getItemnum());
				this.getBaseDao().saveObject(poline);
			}
		}

	}

	public void addPolinebypart(List list, Object obj) throws Exception {
		Po po = (Po) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Eqsparepart eqsparepart = (Eqsparepart) list.get(i);
				Poline poline = new Poline();
				poline.setPonum(po.getPonum());
				int PolInt = this.getRowCountByWhere(poline, "ponum='"
						+ po.getPonum() + "'");
				poline.setPolinenum((long) PolInt + 1);
				poline.setWarehouse(this.getUserInfo().getDefstoreroom());
				poline.setOrderqty(0.00);
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ eqsparepart.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					poline.setOrderunit(item.getOrderunit());
					poline.setDescription(item.getDescription());
				} else {
					poline.setOrderunit("件");
				}
				poline.setProrated("否");
				poline.setReceiptscomplete("否");
				poline.setService("否");
				poline.setRejectedqty(0.00);
				poline.setReceivedtotalcost(0.00);
				poline.setReceivedunitcost(0.00);
				poline.setConversion(1.00);
				poline.setLinecost(0.00);
				poline.setUnitcost(0.00);
				poline.setLoadedcost(0.00);
				poline.setInspection("否");
				poline.setEnterby(this.getUserInfo().getLabornum());
				poline.setEnterdate(new Date());
				//poline.setCorpnum("INBASIS");
				poline.setItemnum(eqsparepart.getItemnum());
				this.getBaseDao().saveObject(poline);
			}
		}

	}

	// ///////////////////业务方法//////////////////////////////////

	public void addPolinebybom(List list, Object obj) throws Exception {
		Po po = (Po) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Itemeqbom itemeqbom = (Itemeqbom) list.get(i);
				Poline poline = new Poline();
				poline.setPonum(po.getPonum());
				int PolInt = this.getRowCountByWhere(poline, "ponum='"
						+ po.getPonum() + "'");
				poline.setPolinenum((long) PolInt + 1);
				poline.setWarehouse(this.getUserInfo().getDefstoreroom());
				poline.setOrderqty(0.00);
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ itemeqbom.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					poline.setOrderunit(item.getOrderunit());
					poline.setDescription(item.getDescription());
				} else {
					poline.setOrderunit("件");
				}
				poline.setProrated("否");
				poline.setReceiptscomplete("否");
				poline.setService("否");
				poline.setRejectedqty(0.00);
				poline.setReceivedtotalcost(0.00);
				poline.setReceivedunitcost(0.00);
				poline.setConversion(1.00);
				poline.setLinecost(0.00);
				poline.setUnitcost(0.00);
				poline.setLoadedcost(0.00);
				poline.setInspection("否");
				poline.setEnterby(this.getUserInfo().getLabornum());
				poline.setEnterdate(new Date());
				//poline.setCorpnum("INBASIS");
				poline.setItemnum(itemeqbom.getItemnum());
				this.getBaseDao().saveObject(poline);
			}
		}

	}
}
