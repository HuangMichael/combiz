package combiz.business.pr;

import combiz.domain.equipment.Eqsparepart;
import combiz.domain.inventory.Favorite;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemeqbom;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.system.IBOBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class PrlineImpl extends IBOBaseImpl implements PrlineSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Prline))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Prline prline = (Prline) obj;
		// this.deleteAllChild(obj, "");
		// ɾ���е�ʱ���޸Ĳɹ������гɱ�
		Double totalcost = 0d;
		if (this.getRecWnd() != null) {
			Pr pr = (Pr) this.getRecWnd().getOwnerWnd().getMainObject();
			totalcost = pr.getTotalcost();
			if (prline.getId() != null) {
				// Poline storepl = (Poline)
				// this.getBaseDao().findUniqueBy(Poline.class,"id",poline.getId());
				Double dblinecost = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.linecost) from Prline t where t.id = '"
								+ prline.getId() + "'");
				if (dblinecost == null) {
					dblinecost = 0d;
				}
				totalcost = totalcost - dblinecost;

			} else {
				totalcost = totalcost - prline.getLinecost();
			}
			pr.setTotalcost(totalcost);
			this.getBaseDao().updateObject(pr);

		}
		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "");
	}

	/**
	 * 
	 */
	public void addPrline(List list, Object obj) throws Exception {
		Pr pr = (Pr) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Favorite favorite = (Favorite) list.get(i);
				Prline prline = new Prline();

				prline.setPrnum(pr.getPrnum());
				int PrlInt = this.getRowCountByWhere(prline, "prnum='"
						+ pr.getPrnum() + "'");
				prline.setPrlinenum((long) PrlInt + 1);
				prline.setWarehouse(this.getUserInfo().getDefstoreroom());
				prline.setOrderqty(0.00);
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ favorite.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					prline.setOrderunit(item.getOrderunit());
					prline.setDescription(item.getDescription());
				} else {
					prline.setOrderunit("��");
				}
				prline.setLinecost(0.00);
				prline.setUnitcost(0.00);
				prline.setIsservice("��");
				prline.setLoadedcost(0.00);
				prline.setProrateservice("��");
				prline.setInspection("��");
				prline.setEnterby(this.getUserInfo().getLabornum());
				prline.setEnterdate(new Date());
				//prline.setCorpnum("INBASIS");
				prline.setItemnum(favorite.getItemnum());
				this.getBaseDao().saveObject(prline);
			}
		}
	}

	public void addPrlinebypart(List list, Object obj) throws Exception {
		Pr pr = (Pr) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Eqsparepart eqsparepart = (Eqsparepart) list.get(i);
				Prline prline = new Prline();
				prline.setPrnum(pr.getPrnum());
				int PrlInt = this.getRowCountByWhere(prline, "prnum='"
						+ pr.getPrnum() + "'");
				prline.setPrlinenum((long) PrlInt + 1);
				prline.setWarehouse(this.getUserInfo().getDefstoreroom());
				prline.setOrderqty(0.00);
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ eqsparepart.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					prline.setOrderunit(item.getOrderunit());
					prline.setDescription(item.getDescription());
				} else {
					prline.setOrderunit("��");
				}
				prline.setLinecost(0.00);
				prline.setUnitcost(0.00);
				prline.setIsservice("��");
				prline.setLoadedcost(0.00);
				prline.setProrateservice("��");
				prline.setInspection("��");
				prline.setEnterby(this.getUserInfo().getLabornum());
				prline.setEnterdate(new Date());
				//prline.setCorpnum("INBASIS");
				prline.setItemnum(eqsparepart.getItemnum());
				this.getBaseDao().saveObject(prline);
			}
		}
	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////

	public void addPrlinebybom(List list, Object obj) throws Exception {
		Pr pr = (Pr) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Itemeqbom itemeqbom = (Itemeqbom) list.get(i);
				Prline prline = new Prline();
				prline.setPrnum(pr.getPrnum());
				int PrlInt = this.getRowCountByWhere(prline, "prnum='"
						+ pr.getPrnum() + "'");
				prline.setPrlinenum((long) PrlInt + 1);
				prline.setWarehouse(this.getUserInfo().getDefstoreroom());
				prline.setOrderqty(0.00);
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ itemeqbom.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					prline.setOrderunit(item.getOrderunit());
					prline.setDescription(item.getDescription());
				} else {
					prline.setOrderunit("��");
				}
				prline.setLinecost(0.00);
				prline.setUnitcost(0.00);
				prline.setIsservice("��");
				prline.setLoadedcost(0.00);
				prline.setProrateservice("��");
				prline.setInspection("��");
				prline.setEnterby(this.getUserInfo().getLabornum());
				prline.setEnterdate(new Date());
				//prline.setCorpnum("INBASIS");
				prline.setItemnum(itemeqbom.getItemnum());
				this.getBaseDao().saveObject(prline);
			}
		}

	}

	/*
	 * save() ���
	 */
	@Override
	public void save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Prline prline = (Prline) obj;
		boolean hasrotating = false;
		boolean hasnorotating = false;
		String itemnum = prline.getItemnum();
		int i = this.getBaseDao().selectCountByHql(
				"select count(*) from Item t where t.itemnum = '" + itemnum
						+ "' and t.rotating ='��'");
		if (i > 0) {
			hasrotating = true;
		} else {
			hasnorotating = true;
		}
		List prlinelist = this.getBaseDao().findWithQuery(Prline.class,
				"ponum='" + prline.getPrnum() + "' and id <> null");
		if (prlinelist.size() > 0) {
			for (int m = 0; m < prlinelist.size(); m++) {
				Prline poline2 = (Prline) prlinelist.get(m);
				int haszzcount = this.getBaseDao().selectCountByHql(
						"select count(*) from Item t where t.itemnum = '"
								+ poline2.getItemnum()
								+ "' and t.rotating ='��'");
				if (haszzcount > 0) {
					hasrotating = true;
				} else {
					hasnorotating = true;
				}
			}

		}
		if (hasrotating && hasnorotating) {
			throw new Exception("�ɹ�����������ͬʱ�ɹ����ʺ��豸!");
		}

		if (this.getRecWnd() != null) {
			if (this.getRecWnd().getOwnerWnd() != null) {
				if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Pr) {
					Pr pr = (Pr) this.getRecWnd().getOwnerWnd().getMainObject();
					Double totalcost = pr.getTotalcost();
					if (totalcost == null)
						totalcost = 0d;
					if (prline.getId() == null) // ���½���¼
					{
						totalcost = totalcost + prline.getLinecost();

					} else // ���¼�¼
					{
						Double dblinecost = (Double) this.getBaseDao()
								.selectSumByHql(
										"select sum(p.linecost) from Poline p where p.id = '"
												+ prline.getId() + "'");
						if (dblinecost == null) {
							dblinecost = 0d;
						}
						totalcost = totalcost - dblinecost
								+ prline.getLinecost();
					}
					pr.setTotalcost(totalcost);
					this.getBaseDao().updateObject(pr);
				}
			}
		}

		super.save(obj);
	}
}
