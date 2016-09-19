package combiz.business.inventory;

import combiz.business.po.PoSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.domain.location.Locations;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;
import combiz.system.IBSServer;
import combiz.system.util.Util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zul.Messagebox;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class MatreqImpl extends IBOBaseImpl implements MatreqSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "");
	}

	/*
	 * ���ܣ��ڽ��з�������ʱ����������˵��µķ���Ԥ���󣬴������¼�����invusetrans��������ݣ� ���ߣ����� ���ڣ�Nov 4, 2008
	 * 12:52:39 PM
	 */
	public void geninvuse(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		for (int i = 0; i < list.size(); i++) {
			Invreserve inverserve = (Invreserve) list.get(i);
			Invusetrans invuse = new Invusetrans();
			invuse.setItemnum(inverserve.getItemnum());
			invuse.setWarehouse(inverserve.getWarehouse());
			invuse.setMatreqnum(matreq.getMatreqnum());
			invuse.setIssuetype("����");
			invuse.setTransdate(new Date());
			invuse.setActualdate(new Date());
			String buditem = matreq.getBuditem();
			String budnum = matreq.getBudnum();
			if(Util.isNotNull(buditem)){
				invuse.setBuditem(buditem);
			}
			else{
				invuse.setBuditem("");
			}
			if(Util.isNotNull(budnum)){
				invuse.setBudnum(budnum);
			}else{
				invuse.setBudnum("");
			}
			Double inuqty = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
					+ inverserve.getReqnum() + "'");
			if (inuqty == null)
				inuqty = 0d;
			Double issueqty = inverserve.getReservedqty() - inuqty;
			invuse.setQuantity(inverserve.getActualqty());
			// List invstockList
			// =this.getBaseDao().findWithQuery(Invstock.class, "itemnum = '" +
			// inverserve.getItemnum() +"' and warehouse = '" +
			// inverserve.getWarehouse() +"'");
			Double curbalsum = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.curbal) from Invstock t where t.itemnum = '"
					+ inverserve.getItemnum() + "' and t.warehouse = '"
					+ inverserve.getWarehouse() + "'");
			if (curbalsum != null) {
				// Invstock invstock = (Invstock) invstockList.get(0);
				// if((issueqty - curbalsum) > 0)
				// {
				// throw new Exception("�������С��������������������޸���������������ɹ����룡");
				// }
				// else
				// {
				invuse.setCurbal(curbalsum);
				invuse.setCurbal(0d);
				List avgcostlist = this.getBaseDao().findWithQuery(
						Inventory.class,
						"itemnum = '" + inverserve.getItemnum()
						+ "' and warehouse = '"
						+ inverserve.getWarehouse() + "'");
				Double linecost = 0d;
				Double unitcost = 0d;
				Double actualcost = 0d;
				if (avgcostlist.size() > 0) {
					Inventory avgcost = (Inventory) avgcostlist.get(0);
					invuse.setUnitcost(avgcost.getAvgcost());
					linecost = inverserve.getReservedqty()
					* avgcost.getAvgcost();
					actualcost = avgcost.getAvgcost();

				}
				//invuse.setSitenum(inverserve.getSitenum());
				//invuse.setCorpnum(inverserve.getCorpnum());
				invuse.setActualcost(actualcost);
				invuse.setLinecost(linecost);
				invuse.setUnitcost(unitcost);
				invuse.setPhyscnt(0d);
				invuse.setConversion(1.0);
				invuse.setEnterby(this.getLaborInfo().getLabornum());
				invuse.setState("��ȷ��");
				invuse.setReqqty(issueqty);
				// }
			} else
				throw new Exception("�����ʱû����Ҫ����Ŀ����Ŀ����������ɹ����룡");
			super.save(invuse);

		}
	}

	/*
	 * ���ܣ��ڷ��ţ��豸��Ӧ�ó����У�����ѡ�е���ת�����з��ţ� ���ߣ����� ���ڣ�Nov 4, 2008 12:53:04 PM
	 */
	public void genequse(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		String reqtype = matreq.getReqtype();
		String matreqnum = matreq.getMatreqnum();

		/** ************�ж�ѡ���Ҫ���ŵ������Ƿ����Ԥ������*************** */
		String idstr2 = null;
		List InvrList = this.getBaseDao().findWithQuery(Invreserve.class,
				"reqnum = '" + matreqnum + "'");
		if (InvrList.size() > 0) {
			for (int i = 0; i < InvrList.size(); i++) {
				Invreserve invreserve = (Invreserve) InvrList.get(i);
				Double waitqty = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
						+ matreqnum + "' and t.itemnum = '"
						+ invreserve.getItemnum()
						+ "' and state ='��ȷ��'");
				if (waitqty == null) {
					waitqty = 0D;
				}
				if (invreserve.getReservedqty() - waitqty > 0) {
					if (idstr2 == null)
						idstr2 = invreserve.getId() + "";
					else
						idstr2 = invreserve.getId() + "," + idstr2;
				}
			}

			if (idstr2 != null)
				idstr2 = "id in(" + idstr2 + ")";
			else
				idstr2 = "1=2";

			List invreservelist = this.getBaseDao().findWithQuery(
					Invreserve.class,
					idstr2 + " and reqnum='" + matreq.getMatreqnum()
					+ "'");
			if (invreservelist.size() > 0) {
				for (int j = 0; j < invreservelist.size(); j++) {
					Invreserve invres = (Invreserve) invreservelist.get(j);
					Double resqty = invres.getReservedqty();// Ԥ������
					Double sumqty = 0d;// ѡ�еĽ�Ҫ���ŵļ�¼
					for (int k = 0; k < list.size(); k++) {
						Invrectrans invrec = (Invrectrans) list.get(k);// ȡ��ѡ�еĽ��ռ�¼��
						if (invrec.getItemnum().equals(invres.getItemnum())
								&& invrec.getTowarehouse().equals(
										invres.getWarehouse())) {
							sumqty = sumqty + invrec.getQuantity();
						}

					}
					Double hasqty = (Double) this.getBaseDao().selectSumByHql(
							"select  sum(t.quantity) from Invusetrans t where t.itemnum ='"
							+ invres.getItemnum()
							+ "' and t.matreqnum ='"
							+ matreq.getMatreqnum()
							+ "'and t.issuetype = '����'  and t.state = '��ȷ��' ");
					if (hasqty == null) {
						hasqty = 0d;
					}
					if (hasqty + sumqty - invres.getReservedqty() > 0) {
						throw new Exception("��ѡ���Ҫ���ŵ��ʲ���������Ԥ�����������ܷ��ţ�");
					}

				}

			}

			for (int i = 0; i < list.size(); i++) {
				Invrectrans invrec = (Invrectrans) list.get(i);
				Invusetrans invuse = new Invusetrans();
				invuse.setItemnum(invrec.getItemnum());
				invuse.setWarehouse(invrec.getTowarehouse());
				invuse.setMatreqnum(matreq.getMatreqnum());

				if(reqtype.equals("�豸��������"))
				{
					invuse.setIssuetype("����");
				}
				else
				{
					invuse.setIssuetype("����");
				}

				invuse.setIsprint("��");
				invuse.setTransdate(new Date());
				invuse.setActualdate(new Date());
				invuse.setRequestdate(matreq.getRequestdate());
				invuse.setUsedate(matreq.getUsedate());
				invuse.setQuantity(1d);
				Double curbalsum = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.curbal) from Invstock t where t.itemnum = '"
						+ invrec.getItemnum() + "' and t.warehouse = '"
						+ invrec.getTowarehouse() + "'");
				if (curbalsum != null) {
					invuse.setCurbal(curbalsum);
					invuse.setCurbal(0d);
					List invlotlist = this.getBaseDao().findWithQuery(
							Invlot.class,
							"itemnum = '" + invrec.getItemnum()
							+ "' and warehouse = '"
							+ invrec.getTowarehouse()
							+ "' and lotnum='" + invrec.getTolot()
							+ "'");
					Double linecost = 0d;
					Double unitcost = 0d;
					Double actualcost = 0d;
					if (invlotlist.size() > 0) {
						Invlot invlot = (Invlot) invlotlist.get(0);
						unitcost = invlot.getLotcost();
						linecost = invlot.getLotcost();
						actualcost = invlot.getLotcost();

					}
					//invuse.setSitenum(invrec.getSitenum());
					//invuse.setCorpnum(invrec.getCorpnum());
					invuse.setActualcost(actualcost);
					invuse.setLinecost(linecost);
					invuse.setUnitcost(unitcost);
					invuse.setPhyscnt(0d);
					invuse.setConversion(1.0);
					invuse.setEnterby(this.getLaborInfo().getLabornum());
					invuse.setLotnum(invrec.getTolot());
					invuse.setDescription(invrec.getDescription());
					invuse.setEqnum(invrec.getEqnum());
					invuse.setInvrectransid(invrec.getId());

					List invreslist = this.getBaseDao().findWithQuery(
							Invreserve.class,
							"reqnum='" + matreq.getMatreqnum()
							+ "' and itemnum ='" + invrec.getItemnum()
							+ "' and warehouse='"
							+ invrec.getTowarehouse()
							+ "'");
					if (invreslist.size() > 0) {
						Invreserve inverserve = (Invreserve) invreslist.get(0);
						invuse.setIssuetolabor(inverserve.getIssuetolabor());
						invuse.setLocation(inverserve.getLocation());
						invuse.setIssuedeptnum(inverserve.getIssuedeptnum());
					}

					invuse.setState("��ȷ��");
				} else
					throw new Exception("�����ʱû����Ҫ����Ŀ����Ŀ����������ɹ����룡");
				super.save(invuse);

			}
		}
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ�����Ԥ����Ϣ����������û���㹻�����Ļ������ɲɹ������¼�� ���ڣ�4:36:31 PM Dec 26, 2008
	 * 
	 */

	public void createinvr(Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		String matreqnum = matreq.getMatreqnum();
		String isgov = matreq.getIsgovprocurement();
		List wplist = this.getBaseDao().findWithQuery(
				Wpmaterial.class,
				"matreqnum = '" + matreqnum
				+ "' and prnum is null and prlinenum is null");
		Pr pr = null;
		Po po = null;
		if (wplist.size() > 0) {
			for (int i = 0; i < wplist.size(); i++) {
				Wpmaterial wpmat = (Wpmaterial) wplist.get(i);

				if (Util.isNull(wpmat.getItemnum())) {
					throw new Exception("����Ϊ:'" + wpmat.getDescription()
							+ "'���Ϊ'" + wpmat.getModelnum() + "'�ļ�¼���ɹ����벻��Ϊ��");
				}
				if (Util.isNull(wpmat.getWarehouse())) {
					throw new Exception("����Ϊ:'" + wpmat.getDescription()
							+ "'���Ϊ'" + wpmat.getModelnum() + "'�ļ�¼���ֿⲻ��Ϊ��");
				}
				if (wpmat.getItemqty()==null || wpmat.getItemqty()<=0) {
					throw new Exception("����Ϊ:'" + wpmat.getDescription()
							+ "'���Ϊ'" + wpmat.getModelnum() + "'�ļ�¼��������������ΪС���㣡");
				}
				this.createserve(wpmat, matreq);

				/** **************�Ƿ����ɲɹ�����********************** */
				String sql = "select sum(inv.curbal) from Invstock inv where inv.itemnum = '"
					+ wpmat.getItemnum()
					+ "' and warehouse ='"
					+ wpmat.getWarehouse() + "'";
				// ���������
				Double sumcurbal = (Double) this.getBaseDao().selectSumByHql(
						sql);
				// Ԥ������
				Double reserveqty = (Double) this
				.getBaseDao()
				.selectSumByHql(
						"select sum(t.reservedqty) from Invreserve t where t.reqnum <> '"
						+ wpmat.getMatreqnum()
						+ "' and t.itemnum = '"
						+ wpmat.getItemnum()
						+ "' and t.warehouse = '"
						+ wpmat.getWarehouse()
						+ "' and t.ponum is null and t.polinenum is null");

				if (sumcurbal == null) {
					sumcurbal = 0d;
				}
				if (reserveqty == null) {
					reserveqty = 0d;
				}

				// ��Ҫ�ɹ�����
				Double needorderqty = 0d;
				// Double needorderqty = sumcurbal - reserveqty
				// - wpmat.getItemqty();
				if (reserveqty - sumcurbal >= 0)// ��ǰ�Ŀ�������Ѿ�Ԥ�����Ѿ���׼�����룬ֻ����ʱû�з�����
				{
					needorderqty = -wpmat.getItemqty();
				} else// ���п���ȥԤ�������������Ѿ�Ԥ������δ���������������н��ࡣ
				{
					needorderqty = sumcurbal - reserveqty - wpmat.getItemqty();
				}
				if (needorderqty < 0) {
					if (Util.isNotNull(isgov) && isgov.equals("��"))// �Ƿ������ɹ�
					{

						if (po == null) {
							po = (Po) this.createpo(wpmat, matreq);
						}
						if (po instanceof Po) {
							this.createpoline(wpmat, po, -needorderqty);
						}

					} else// һ��ɹ�
					{
						if (pr == null) {
							pr = (Pr) this.createpr(wpmat, matreq);
						}
						if (pr instanceof Pr) {
							this.createprline(wpmat, pr, -needorderqty);
						}
					}
				}

			}

			matreq.setStatus("����׼");
			super.getBaseDao().updateObject(matreq);

			// �ж��Ƿ��Զ�����������
			// ************************�Զ�����������(��ʼ)������������������������������������������������������������������������//
			if (pr != null) {
				PrSrv prsrv = (PrSrv) IBOSrvUtil.getSrv("pr");
				try {
					prsrv.workflow(pr, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ************************�Զ�����������(����)������������������������������������������������������������������������//
			Messagebox.show("Ԥ��������ϣ�");
		} else {
			Messagebox.show("�õ�����û�����������ļ�¼�����Ѿ�ȫ������Ԥ�����޷�����Ԥ����");
			return;
		}
	}

	/**
	 * ���� Ԥ�������������
	 * 
	 */
	public void createserve(Object obj, Object obj2) throws Exception {
		Wpmaterial mater = (Wpmaterial) obj;
		Matreq matreq = (Matreq) obj2;
		Warehouse warehouse = null;
		String loc = null;
		if (mater.getWarehouse() != null) {
			List warelist = this.findWithQuery(Warehouse.class, "warehouse='"
					+ mater.getWarehouse() + "'");
			warehouse = (Warehouse) warelist.get(0);
		} else {
			throw new Exception("�벹��ֿ���Ϣ��");
		}
		Invreserve invreserve = new Invreserve();
		invreserve.setDirectreq("��");
		invreserve.setIssuetolabor(matreq.getRequestby());

		String labornum = matreq.getRequestby();
		List laborlist = this.getBaseDao().findWithQuery(
				Labor.class,
				"labornum ='" + labornum + "'");
		if (laborlist.size() > 0) {
			Labor labor = (Labor) laborlist.get(0);
			loc = labor.getDefaultloc();
		} else {
			List loclist = this.getBaseDao().findWithQuery(Locations.class,
			"parent is null");
			Locations location = (Locations) loclist.get(0);
			loc = location.getLocation();
		}
		String buditem = mater.getBuditem();
		String budnum = mater.getBudnum();
		if(Util.isNotNull(buditem)){
			invreserve.setBuditem(buditem);
		}
		else{
			invreserve.setBuditem("");
		}
		if(Util.isNotNull(budnum)){
			invreserve.setBudnum(budnum);
		}else{
			invreserve.setBudnum("");
		}	
		invreserve.setLocation(loc);
		invreserve.setIssuedeptnum(matreq.getReqdept());
		invreserve.setItemnum(mater.getItemnum());
		invreserve.setReqby(mater.getRequestby());
		invreserve.setReqdate(new java.util.Date());
		invreserve.setReqnum(mater.getMatreqnum());
		invreserve.setRequireddate(mater.getRequiredate());
		invreserve.setReservedqty(mater.getItemqty());
		invreserve.setWarehouse(mater.getWarehouse());
		//invreserve.setSitenum(matreq.getSitenum());
		//invreserve.setCorpnum(matreq.getCorpnum());
		this.getBaseDao().saveObject(invreserve);
	}

	// ���ɲɹ�����
	public Pr createpr(Wpmaterial mater, Matreq mat) throws Exception {
		Pr pr = new Pr();
		PrSrv prsrv = (PrSrv) IBOSrvUtil.getSrv("pr");
		String desc = "������{" + mat.getMatreqnum() + ":" + mat.getDescription()
		+ "}����";
		pr.setPrnum(prsrv.genInskey("prnum"));
		pr.setStatus("�ɹ�Աȷ��");
		pr.setPrnumtype("������������");
		pr.setStatusdate(new Date());
		String labornum = this.getLaborInfo().getLabornum();
		pr.setChangeby(labornum);
		Labor labor = (Labor) this.getBaseDao().findUniqueBy(Labor.class,
				"labornum", labornum);
		pr.setRequestdept(labor.getDeptnum());
		pr.setChangedate(new Date());
		pr.setDescription(desc);
		pr.setRequestdate(new Date());
		pr.setRequestedby(mat.getRequestby());
		pr.setPrtype("�ɹ�����");
		String buditem = mat.getBuditem();
		String budnum = mat.getBudnum();
		if(Util.isNotNull(buditem)){
			pr.setBuditem(buditem);
		}
		else{
			pr.setBuditem("");
		}
		if(Util.isNotNull(budnum)){
			pr.setBudnum(budnum);
		}else{
			pr.setBudnum("");
		}
		//pr.setSitenum(mat.getSitenum());
		//pr.setCorpnum(mat.getCorpnum());
		this.getBaseDao().saveObject(pr);
		return pr;
	}

	// ���ɲɹ�������
	public void createprline(Wpmaterial wpmat, Pr pr, double genqty)
	throws Exception {
		Prline pl = new Prline();

		int PrlInt = this.getRowCountByWhere(pl, "prnum='" + pr.getPrnum()
				+ "'");
		pl.setPrnum(pr.getPrnum());
		pl.setPrlinenum((long) PrlInt + 1);
		pl.setConversion(1.00);
		String buditem = wpmat.getBuditem();
		String budnum = wpmat.getBudnum();
		if(Util.isNotNull(buditem)){
			pl.setBuditem(buditem);
		}
		else{
			pl.setBuditem("");
		}
		if(Util.isNotNull(budnum)){
			pl.setBudnum(budnum);
		}else{
			pl.setBudnum("");
		}
		pl.setEnterdate(new Date());
		pl.setUnitcost(wpmat.getUnitcost());
		pl.setEnterby(this.getLaborInfo().getLaborname());
		pl.setPrlinenum((long) PrlInt + 1);
		pl.setProrateservice("��");
		pl.setIsservice("��");
		pl.setOrderunit(wpmat.getOrderunit());
		pl.setLoadedcost(0d);
		pl.setItemnum(wpmat.getItemnum());
		pl.setDescription(wpmat.getDescription());
		pl.setModelnum(wpmat.getModelnum());
		pl.setWarehouse(wpmat.getWarehouse());
		//pl.setSitenum(pr.getSitenum());
		//pl.setCorpnum(pr.getCorpnum());

		Double unitcost = 0d;

		List itemlist = this.getBaseDao().findWithQuery(Item.class,
				"itemnum = '" + pl.getItemnum() + "'");
		List inventorylist = this.getBaseDao().findWithQuery(
				Inventory.class,
				"itemnum='" + wpmat.getItemnum() + "' and warehouse = '"
				+ wpmat.getWarehouse() + "'");
		if (itemlist.size() > 0) {
			Item item = (Item) itemlist.get(0);
			pl.setInspection(item.getInspectreq());
			String unit = item.getOrderunit();
			if (inventorylist.size() > 0) {
				Inventory inventory = (Inventory) inventorylist.get(0);
				pl.setStocktype(inventory.getStocktype());// �������
				unitcost = inventory.getAvgcost();
			}

		}
		pl.setManufacturer(wpmat.getManufacturer());
		pl.setOrderqty(genqty);// ��������
		pl.setUnitcost(unitcost);
		pl.setLinecost(unitcost * genqty);
		this.getBaseDao().saveObject(pl);

		List invreslist = this.findWithQuery(Invreserve.class, "reqnum='"
				+ wpmat.getMatreqnum() + "' and itemnum='" + wpmat.getItemnum()
				+ "' and warehouse ='" + wpmat.getWarehouse() + "'");
		if (invreslist.size() > 0) {
			Invreserve invr = (Invreserve) invreslist.get(0);
			invr.setPonum(pl.getPrnum());
			invr.setPolinenum(pl.getPrlinenum());
			this.getBaseDao().updateObject(invr);
		}

		// ��д��wpmaterial��������
		wpmat.setPrnum(pl.getPrnum());
		wpmat.setPrlinenum(pl.getPrlinenum());
		this.getBaseDao().updateObject(wpmat);
	}

	// --------------------------------------------------------------------

	// ���ɲɹ�����
	public Po createpo(Wpmaterial mater, Matreq mat) throws Exception {
		Po po = new Po();
		PoSrv posrv = (PoSrv) IBOSrvUtil.getSrv("po");
		String desc = "������{" + mat.getMatreqnum() + ":" + mat.getDescription()
		+ "}����";
		po.setPonum(posrv.genInskey("ponum"));
		po.setStatus("�ɹ�Աȷ��");
		po.setStatusdate(new Date());
		String labornum = this.getLaborInfo().getLabornum();
		po.setChangeby(labornum);
		Labor labor = (Labor) this.getBaseDao().findUniqueBy(Labor.class,
				"labornum", labornum);
		po.setChangedate(new Date());
		po.setDescription(desc);
		po.setIsprotocol("��");
		po.setTotalcost(0d);
		po.setVendor("��ָ����Ӧ��");
		po.setPotype("��");
		po.setReceipts("δ����");
		po.setIsgov("��");
		//po.setSitenum(mat.getSitenum());
		//po.setCorpnum(mat.getCorpnum());
		this.getBaseDao().saveObject(po);
		return po;
	}

	// ���ɲɹ�������
	public void createpoline(Wpmaterial wpmat, Po po, double genqty)
	throws Exception {
		Poline pl = new Poline();

		int PolInt = this.getRowCountByWhere(pl, "ponum='" + po.getPonum()
				+ "'");
		pl.setPonum(po.getPonum());
		pl.setPolinenum((long) PolInt + 1);
		pl.setConversion(1d);
		pl.setEnterdate(new Date());
		pl.setEnterby(this.getLaborInfo().getLaborname());
		pl.setPolinenum((long) PolInt + 1);
		pl.setOrderunit(wpmat.getOrderunit());
		pl.setLoadedcost(0d);
		pl.setItemnum(wpmat.getItemnum());
		pl.setDescription(wpmat.getDescription());
		pl.setModelnum(wpmat.getModelnum());
		pl.setWarehouse(wpmat.getWarehouse());
		pl.setProrated("��");
		pl.setInspection("��");
		pl.setService("��");
		pl.setReceivedqty(0d);
		pl.setReceivedtotalcost(0d);
		pl.setReceivedunitcost(0d);
		pl.setRejectedqty(0d);
		pl.setService("��");
		pl.setReceiptscomplete("��");
		pl.setRequestedby(wpmat.getRequestby());
		pl.setWarehouse("�ֿ�һ");
		String buditem = wpmat.getBuditem();
		String budnum = wpmat.getBudnum();
		if(Util.isNotNull(buditem)){
			pl.setBuditem(buditem);
		}
		else{
			pl.setBuditem("");
		}
		if(Util.isNotNull(budnum)){
			pl.setBudnum(budnum);
		}else{
			pl.setBudnum("");
		}
		//pl.setSitenum(po.getSitenum());
		//pl.setCorpnum(po.getCorpnum());

		Double unitcost = 0d;

		List itemlist = this.getBaseDao().findWithQuery(Item.class,
				"itemnum = '" + pl.getItemnum() + "'");
		List inventorylist = this.getBaseDao().findWithQuery(
				Inventory.class,
				"itemnum='" + wpmat.getItemnum() + "' and warehouse = '"
				+ wpmat.getWarehouse() + "'");
		if (itemlist.size() > 0) {
			Item item = (Item) itemlist.get(0);
			pl.setInspection(item.getInspectreq());
			String unit = item.getOrderunit();
			if (inventorylist.size() > 0) {
				Inventory inventory = (Inventory) inventorylist.get(0);
				pl.setStocktype(inventory.getStocktype());// �������
				unitcost = inventory.getAvgcost();
			}

		}
		pl.setManufacturer(wpmat.getManufacturer());
		pl.setOrderqty(genqty);// ��������
		pl.setUnitcost(unitcost);
		pl.setLinecost(unitcost * genqty);
		this.getBaseDao().saveObject(pl);

		List invreslist = this.findWithQuery(Invreserve.class, "reqnum='"
				+ wpmat.getMatreqnum() + "' and itemnum='" + wpmat.getItemnum()
				+ "' and warehouse ='" + wpmat.getWarehouse() + "'");
		if (invreslist.size() > 0) {
			Invreserve invr = (Invreserve) invreslist.get(0);
			invr.setPonum(pl.getPonum());
			invr.setPolinenum(pl.getPolinenum());
			this.getBaseDao().updateObject(invr);
		}

		// ��д��wpmaterial��������
		wpmat.setPrnum(pl.getPonum());
		wpmat.setPrlinenum(pl.getPolinenum());
		this.getBaseDao().updateObject(wpmat);
	}

	// -------------------------------------------------------------------------

	/*
	 * ���ܣ��Է����н���У�顣 ���ߣ����� ���ڣ�Nov 4, 2008 12:53:31 PM
	 */
	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		String reqtype = matreq.getReqtype();

		if (reqtype.equals("������������")) {
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				// �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
				// super.save(inv);
				/***************************************************************
				 * ***************************�ж��Ƿ������ι����****************************************************************
				 * ************************************************************************************************************
				 * ************************************************************************************************************
				 */
				String lottype = null;
				String itemnum = inv.getItemnum();
				String warehouse = inv.getWarehouse();
				List itemlist = this.getBaseDao().findWithQuery(Item.class,
						"itemnum='" + itemnum + "'");
				if (itemlist.size() > 0) {
					Item item = (Item) itemlist.get(0);
					lottype = item.getLottype();
					if (lottype.equals("���ι���") && Util.isNull(inv.getLotnum())) {
						throw new Exception("�����Ŀ'" + itemnum
								+ "'Ϊ���ι�������ʣ����ڷ�������ѡ�����ȷ�����ŵ����Σ�");
					}
				}

				List invstocklist = null;
				Double issueqty = inv.getQuantity();
				if (Util.isNotNull(inv.getItemnum())) {
					if (lottype.equals("���ι���")) // ���ι���
					{
						if (Util.isNotNull(inv.getBinnum()))// ���Ϊ��
						{
							invstocklist = this.getBaseDao().findWithQuery(
									Invstock.class,
									"itemnum ='" + inv.getItemnum()
									+ "' and warehouse ='"
									+ inv.getWarehouse()
									+ "' and binnum = '"
									+ inv.getBinnum()
									+ "' and lotnum = '"
									+ inv.getLotnum() + "'");
						} else// ���Ϊ��
						{
							invstocklist = this.getBaseDao().findWithQuery(
									Invstock.class,
									"itemnum ='" + inv.getItemnum()
									+ "' and warehouse ='"
									+ inv.getWarehouse()
									+ "' and lotnum = '"
									+ inv.getLotnum()
									+ "' and binnum is null ");
						}

						Invstock invstock = (Invstock) invstocklist.get(0);
						Double curbal = invstock.getCurbal();
						if (curbal - issueqty > 0) {
							invstock.setCurbal(curbal - issueqty);
							super.save(invstock);
							inv.setState("�����");
							super.save(inv);
						} else {
							if (curbal - issueqty == 0) {
								invstock.setCurbal(0d);
								super.save(invstock);
								super.delete(invstock);
								inv.setState("�����");
								// List invlotlist =
								// this.getBaseDao().findWithQuery(Invlot.class,
								// "itemnum ='" + inv.getItemnum() + "' and
								// warehouse ='" + inv.getWarehouse() + "' and
								// lotnum = '"+inv.getLotnum()+"'");
								// if(invlotlist.size()>0)
								// {
								// Invlot invlot = (Invlot) invlotlist.get(0);
								// this.getBaseDao().deleteObject(invlot);
								// }

								super.save(inv);
							} else
								throw new Exception("�������п����Ŀ���Ϊ��"
										+ inv.getItemnum()
										+ "�������С�ڷ�������,���ܽ��з���");
						}

					} else // �����ι���
					{
						if (Util.isNotNull(inv.getBinnum()))// ���Ϊ��
						{
							invstocklist = this.getBaseDao().findWithQuery(
									Invstock.class,
									"itemnum ='" + inv.getItemnum()
									+ "' and warehouse ='"
									+ inv.getWarehouse()
									+ "' and binnum = '"
									+ inv.getBinnum()
									+ "'");
						} else// ���Ϊ��
						{
							invstocklist = this
							.getBaseDao()
							.findWithQuery(
									Invstock.class,
									"itemnum ='"
									+ inv.getItemnum()
									+ "' and warehouse ='"
									+ inv.getWarehouse()
									+ "' and binnum is null ");
						}
						if(invstocklist!=null && invstocklist.size()>0)
						{
							Invstock invstock = (Invstock) invstocklist.get(0);
							Double curbal = invstock.getCurbal();
							if (curbal - issueqty > 0) {
								invstock.setCurbal(curbal - issueqty);
								super.save(invstock);
								inv.setState("�����");
								super.save(inv);
							} else {
								if (curbal - issueqty == 0) {
									invstock.setCurbal(0d);
									super.save(invstock);
									super.delete(invstock);
									inv.setState("�����");
									super.save(inv);
								} else
									throw new Exception("�������п����Ŀ���Ϊ��"
											+ inv.getItemnum()
											+ "�������С�ڷ�������,���ܽ��з���");
							}
						}
					}

					/** ***************�޸�Ԥ������********* */
					List invreslist = this.getBaseDao().findWithQuery(
							Invreserve.class,
							"reqnum='" + inv.getMatreqnum() + "' and itemnum='"
							+ itemnum + "' and warehouse='"+warehouse+"'");
					if (invreslist.size() > 0) {
						Invreserve invres = (Invreserve) invreslist.get(0);
						Double resqty = invres.getReservedqty();
						if (resqty - inv.getQuantity() > 0) 
						{
							invres.setReservedqty(resqty - inv.getQuantity());
							this.getBaseDao().updateObject(invres);
						} 
						else 
						{
							this.getBaseDao().deleteObject(invres);

						}

					}

				}
			}

		} else// �豸����������
		{
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				/***************************************************************
				 * ***************************�ж��Ƿ������ι����****************************************************************
				 * ************************************************************************************************************
				 * ************************************************************************************************************
				 */
				Double issueqty = inv.getQuantity();
				String loc = inv.getLocation();
				if (Util.isNull(loc)) {
					throw new Exception("�ʲ����Ϊ'" + inv.getEqnum()
							+ "'���ʲ�����λ��Ϊ�գ����ʵ��");
				}

				/***************************************************************
				 * ******************���Ų�Ϊ�����***********************
				 **************************************************************/
				if (Util.isNotNull(inv.getItemnum())) {
					List invstocklist = this.getBaseDao().findWithQuery(
							Invstock.class,
							"itemnum ='" + inv.getItemnum()
							+ "' and warehouse ='" + inv.getWarehouse()
							+ "' and lotnum = '" + inv.getLotnum()
							+ "'");
					if (invstocklist.size() > 0) {
						Invstock invstock = (Invstock) invstocklist.get(0);
						Double curbal = invstock.getCurbal();
						if (curbal - issueqty > 0) {
							invstock.setCurbal(curbal - issueqty);
							super.getBaseDao().updateObject(invstock);
							inv.setState("�����");
							super.getBaseDao().updateObject(inv);
						} else {
							if (curbal - issueqty == 0) {
								// invstock.setCurbal(0d);
								// super.getBaseDao().updateObject(invstock);
								super.getBaseDao().deleteObject(invstock);
								inv.setState("�����");
								List invlotlist = this.getBaseDao()
								.findWithQuery(
										Invlot.class,
										"itemnum ='" + inv.getItemnum()
										+ "' and warehouse ='"
										+ inv.getWarehouse()
										+ "' and lotnum = '"
										+ inv.getLotnum()
										+ "'");
								if (invlotlist.size() > 0) {
									Invlot invlot = (Invlot) invlotlist.get(0);
									this.getBaseDao().deleteObject(invlot);
								}

								super.getBaseDao().updateObject(inv);
							} else
								throw new Exception("�������п����Ŀ���Ϊ��"
										+ inv.getItemnum()
										+ "�������С�ڷ�������,���ܽ��з���");
						}

						// д��equipment������
						List equiplist = this.getBaseDao().findWithQuery(
								Equipment.class,
								"eqnum ='" + inv.getEqnum() + "'");
						if (equiplist.size() > 0) {
							Equipment equipment = (Equipment) equiplist.get(0);
							equipment.setLocation(inv.getLocation());
							equipment.setDeptnum(inv.getIssuedeptnum());
							equipment.setLabornum(inv.getIssuetolabor());
							equipment.setRundate(new Date());
							equipment.setStatus("����");
							this.getBaseDao().updateObject(equipment);

						}

					}

				} else
					throw new Exception("��������Ч�����ţ�������ȫ��Ϊ�ո�");

				/** ***************�޸�Ԥ������********* */
				
				List invreslist = this.getBaseDao().findWithQuery(
						Invreserve.class,
						"reqnum='" + inv.getMatreqnum() + "' and itemnum='"
						+ inv.getItemnum() + "' and warehouse='"+inv.getWarehouse()+"'");
				if (invreslist.size() > 0) {
					Invreserve invres = (Invreserve) invreslist.get(0);
					Double resqty = invres.getReservedqty();
					if (resqty - inv.getQuantity() > 0) 
					{
						invres.setReservedqty(resqty - inv.getQuantity());
						this.getBaseDao().updateObject(invres);
					} 
					else 
					{
						this.getBaseDao().deleteObject(invres);

					}
				}
			}

		}

	}

	/*
	 * ���ܣ������Ŀ���ź�������ɣ����Խ����˿���� ���ߣ����� ���ڣ�Nov 4, 2008 12:53:52 PM
	 */
	public void returnissue(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		String matreqnum = matreq.getMatreqnum();
		String reqtype = matreq.getReqtype();

		if (reqtype.equals("������������"))// ����������������Ŀ���˻�
		{
			for (int i = 0; i < list.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) list.get(i);
				List invuselist = this.getBaseDao().findWithQuery(
						Invusetrans.class,
						"matreqnum = '" + matreqnum + "' and itemnum ='"
						+ invusetrans.getItemnum()
						+ "'and issuetype = '����' and state ='�����'");
				if (invuselist.size() > 0) {
					Invusetrans newinvuse = new Invusetrans();
					newinvuse.setItemnum(invusetrans.getItemnum());
					newinvuse.setWarehouse(invusetrans.getWarehouse());
					newinvuse.setMatreqnum(invusetrans.getMatreqnum());
					newinvuse.setLocation(invusetrans.getLocation());
					newinvuse.setIssuetype("�˻�");
					newinvuse.setTransdate(new Date());
					newinvuse.setActualdate(new Date());
					String binnum = invusetrans.getBinnum();
					newinvuse.setDescription(invusetrans.getDescription());
					newinvuse.setBinnum(binnum);
					Double issueqty = 0d;
					if (Util.isNotNull(binnum)) {
						issueqty = (Double) this.getBaseDao().selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
								+ invusetrans.getMatreqnum()
								+ "' and itemnum ='"
								+ invusetrans.getItemnum()
								+ "'and binnum = '" + binnum
								+ "'and state ='�����'");
						if (issueqty == null)
							issueqty = 0d;

					} else {
						issueqty = (Double) this
						.getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
								+ invusetrans.getMatreqnum()
								+ "' and itemnum ='"
								+ invusetrans.getItemnum()
								+ "'and binnum is null and state ='�����'");
						if (issueqty == null)
							issueqty = 0d;
					}
					newinvuse.setQuantity(-issueqty);
					List invstock = this.getBaseDao().findWithQuery(
							Invstock.class,
							"itemnum = '" + invusetrans.getItemnum()
							+ "' and warehouse = '"
							+ invusetrans.getWarehouse() + "'");
					if (invstock.size() > 0) {
						Invstock curbal = (Invstock) invstock.get(0);
						newinvuse.setCurbal(curbal.getCurbal());
						newinvuse.setPhyscnt(curbal.getPhyscnt());
						List avgcostlist = this.getBaseDao().findWithQuery(
								Inventory.class,
								"itemnum = '" + invusetrans.getItemnum()
								+ "' and warehouse = '"
								+ invusetrans.getWarehouse() + "'");
						Inventory avgcost = (Inventory) avgcostlist.get(0);
						newinvuse.setUnitcost(avgcost.getAvgcost());
						Double linecost = (-issueqty) * avgcost.getAvgcost();
						newinvuse.setLinecost(linecost);
						newinvuse.setActualcost(avgcost.getAvgcost());
						newinvuse.setConversion(1.0);
						newinvuse.setEnterby(this.getLaborInfo().getLabornum());
						newinvuse.setState("��ȷ��");
						//newinvuse.setSitenum(invusetrans.getSitenum());
						//newinvuse.setCorpnum(invusetrans.getCorpnum());
						super.save(newinvuse);
					} else
						throw new Exception("û�п����˿�ķ����У�");
				}
			}
		} else// �豸������������Ŀ���˻�
		{

			for (int i = 0; i < list.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) list.get(i);
				List invuselist = this.getBaseDao().findWithQuery(
						Invusetrans.class,
						"matreqnum = '" + matreqnum + "' and itemnum ='"
						+ invusetrans.getItemnum() + "' and eqnum = '"
						+ invusetrans.getEqnum() + "' and lotnum = '"
						+ invusetrans.getLotnum()
						+ "'and issuetype = '����' and state ='�����'");
				if (invuselist.size() > 0) {
					Double curbal = 0d;
					Double physcnt = 0d;
					Invusetrans newinvuse = new Invusetrans();
					newinvuse.setItemnum(invusetrans.getItemnum());
					newinvuse.setWarehouse(invusetrans.getWarehouse());
					newinvuse.setMatreqnum(invusetrans.getMatreqnum());
					newinvuse.setLocation("0109");
					newinvuse.setIssuetype("�˻�");
					newinvuse.setTransdate(new Date());
					newinvuse.setActualdate(new Date());
					newinvuse.setLotnum(invusetrans.getLotnum());
					String binnum = invusetrans.getBinnum();
					newinvuse.setBinnum(binnum);
					newinvuse.setDescription(invusetrans.getDescription());
					Double issueqty = 0d;
					issueqty = (Double) this.getBaseDao().selectSumByHql(
							"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
							+ invusetrans.getMatreqnum()
							+ "' and itemnum ='"
							+ invusetrans.getItemnum()
							+ "'and eqnum = '" + invusetrans.getEqnum()
							+ "' and lotnum ='"
							+ invusetrans.getLotnum()
							+ "'and state ='�����'");
					if (issueqty == null)
						issueqty = 0d;

					newinvuse.setQuantity(-issueqty);
					List invstock = this.getBaseDao().findWithQuery(
							Invstock.class,
							"itemnum = '" + invusetrans.getItemnum()
							+ "' and warehouse = '"
							+ invusetrans.getWarehouse() + "'");
					if (invstock.size() > 0) {
						Invstock stock = (Invstock) invstock.get(0);
						physcnt = stock.getPhyscnt();
						curbal = stock.getCurbal();
					}
					newinvuse.setCurbal(curbal);
					newinvuse.setPhyscnt(physcnt);
					newinvuse.setUnitcost(invusetrans.getUnitcost());
					Double linecost = (-issueqty) * invusetrans.getUnitcost();
					newinvuse.setLinecost(linecost);
					newinvuse.setActualcost(invusetrans.getUnitcost());
					newinvuse.setConversion(1.0);
					newinvuse.setEnterby(this.getLaborInfo().getLabornum());
					newinvuse.setEqnum(invusetrans.getEqnum());
					newinvuse.setState("��ȷ��");
					//newinvuse.setSitenum(invusetrans.getSitenum());
					//newinvuse.setCorpnum(invusetrans.getCorpnum());
					this.getBaseDao().saveObject(newinvuse);

				}
				// else
				// throw new Exception("û�п����˿�ķ����У�");
			}
		}

	}

	/*
	 * ���ܣ��ڽ����˿�����󣬶��˿���˿��м�¼����У�顣 ���ߣ����� ���ڣ�Nov 4, 2008 12:54:02 PM
	 */
	public void verifyreturn(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");

		Matreq matreq = (Matreq) obj;
		String reqtype = matreq.getReqtype();

		if (reqtype.equals("������������"))// ����������������Ŀ���˻���֤
		{
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				/***************************************************************
				 * ******************************************************************
				 * 
				 **************************************************************/

				// �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
				super.save(inv);
				Double returnqty = inv.getQuantity();
				// if(inv.getBinnum().trim().length()>0 && inv.getBinnum() !=
				// null)
				Double hasissueqty = 0d;
				List inventorylist = this.getBaseDao().findWithQuery(
						Inventory.class,
						"itemnum ='" + inv.getItemnum() + "' and warehouse ='"
						+ inv.getWarehouse() + "'");
				Inventory inventory = (Inventory) inventorylist.get(0);
				Double avgcost = 0d;
				if (Util.isNotNull(inv.getBinnum())) {
					Integer length = inv.getItemnum().trim().length();
					if (length > 0) {
						List invstocklist = this.getBaseDao().findWithQuery(
								Invstock.class,
								"itemnum ='" + inv.getItemnum()
								+ "' and warehouse ='"
								+ inv.getWarehouse()
								+ "' and binnum = '" + inv.getBinnum()
								+ "'");
						Invstock invstock = (Invstock) invstocklist.get(0);
						Double curbal = invstock.getCurbal();
						// hasissueqty = (Double)
						// this.getBaseDao().selectSumByHql("select
						// sum(t.quantity)
						// from Invusetrans t where t.wonum ='" + inv.getWonum()
						// +
						// "' and itemnum ='" + inv.getItemnum() + "'and binnum
						// =
						// '"+ inv.getBinnum() + "'and issuetype ='����' and state
						// ='�����'");
						hasissueqty = (Double) this.getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
								+ inv.getMatreqnum()
								+ "' and itemnum ='"
								+ inv.getItemnum()
								+ "'and binnum = '"
								+ inv.getBinnum()
								+ "'and state ='�����'");
						if (hasissueqty + returnqty >= 0) {
							invstock.setCurbal(curbal - returnqty);
							avgcost = (-returnqty * inv.getUnitcost() + curbal
									* inventory.getAvgcost())
									/ (curbal - returnqty);
							inventory.setAvgcost(avgcost);
							this.getBaseDao().updateObject(inventory);
							this.getBaseDao().updateObject(invstock);
							inv.setState("�����");
							super.getBaseDao().updateObject(inv);

						} else {
							throw new Exception("�����Ŀ���Ϊ��" + inv.getItemnum()
									+ "������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");
						}
					} else
						throw new Exception("��������Ч�����ţ�������ȫ��Ϊ�ո�");

				} else {
					List invstocklist = this.getBaseDao().findWithQuery(
							Invstock.class,
							"itemnum ='" + inv.getItemnum()
							+ "' and warehouse ='" + inv.getWarehouse()
							+ "' and binnum is null");
					if (invstocklist.size() > 0)// ��������µ��˿⡣
					{
						Invstock invstock = (Invstock) invstocklist.get(0);
						Double curbal1 = invstock.getCurbal();
						hasissueqty = (Double) this
						.getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
								+ inv.getMatreqnum()
								+ "' and itemnum ='"
								+ inv.getItemnum()
								+ "'and binnum = '"
								+ inv.getBinnum()
								+ "'and issuetype ='����' and state ='�����'");
						if (hasissueqty == null) {
							hasissueqty = 0d;
						}
						if (hasissueqty + returnqty >= 0) {
							invstock.setCurbal(curbal1 - returnqty);
							avgcost = (-returnqty * inv.getUnitcost() + curbal1
									* inventory.getAvgcost())
									/ (curbal1 - returnqty);
							inventory.setAvgcost(avgcost);
							super.save(inventory);
							super.save(invstock);
							inv.setState("�����");
							this.getBaseDao().updateObject(inv);

						} else {
							throw new Exception("�����Ŀ���Ϊ��" + inv.getItemnum()
									+ "������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");

						}

					} else// �쳣�����invstock���е����ݶ�ʧ
					{
						Invstock invstock = new Invstock();
						invstock.setItemnum(inv.getItemnum());
						invstock.setWarehouse(inv.getWarehouse());
						invstock.setBinnum(inv.getBinnum());
						// ��Ҫ�����������Ŀ����һ�����Ľ��մ���
						invstock.setPhyscnt(inv.getQuantity());
						invstock.setPhyscntdate(new Date());
						invstock.setCurbal(inv.getQuantity());
						invstock.setReconciled("��");
						//invstock.setSitenum(inv.getSitenum());
						//invstock.setCorpnum(inv.getCorpnum());
						super.save(invstock);
					}

				}
			}

		} else// �������ת�����˻���֤
		{
			Double curbal = 0d;
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				Double returnqty = inv.getQuantity();
				Double hasissueqty = 0d;
				List inventorylist = this.getBaseDao().findWithQuery(
						Inventory.class,
						"itemnum ='" + inv.getItemnum() + "' and warehouse ='"
						+ inv.getWarehouse() + "'");
				Inventory inventory = (Inventory) inventorylist.get(0);
				Double avgcost = 0d;

				hasissueqty = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
						+ inv.getMatreqnum() + "' and itemnum ='"
						+ inv.getItemnum() + "'and eqnum = '"
						+ inv.getEqnum()
						+ "'and state ='�����' and lotnum='"
						+ inv.getLotnum() + "'");

				if (hasissueqty + returnqty < 0) {

					throw new Exception("�����Ŀ���Ϊ��" + inv.getItemnum()
							+ "������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");
				}

				List invstocklist = this.getBaseDao().findWithQuery(
						Invstock.class,
						"itemnum ='" + inv.getItemnum() + "' and warehouse ='"
						+ inv.getWarehouse() + "' and lotnum = '"
						+ inv.getLotnum() + "'");
				if (invstocklist.size() > 0) {
					Invstock invstock = (Invstock) invstocklist.get(0);
					curbal = invstock.getCurbal();

					invstock.setCurbal(curbal - returnqty);
					avgcost = (-returnqty * inv.getUnitcost() + curbal
							* inventory.getAvgcost())
							/ (curbal - returnqty);
					inventory.setAvgcost(avgcost);
					this.getBaseDao().updateObject(inventory);
					this.getBaseDao().updateObject(invstock);

				} else {
					Invstock invstock = new Invstock();
					invstock.setCurbal(-inv.getQuantity());
					invstock.setItemnum(inv.getItemnum());
					invstock.setBinnum(inv.getBinnum());
					invstock.setWarehouse(inv.getWarehouse());
					invstock.setLotnum(inv.getLotnum());
					invstock.setPhyscnt(-inv.getQuantity());
					//invstock.setSitenum(inv.getSitenum());
					//invstock.setCorpnum(inv.getCorpnum());
					invstock.setReconciled("��");
					invstock.setPhyscntdate(new Date());
					this.getBaseDao().saveObject(invstock);

					Invlot invlot = new Invlot();
					invlot.setItemnum(inv.getItemnum());
					//invlot.setCorpnum(inv.getCorpnum());
					//invlot.setSitenum(inv.getSitenum());
					invlot.setWarehouse(inv.getWarehouse());
					invlot.setLotnum(inv.getLotnum());
					invlot.setLotcost(inv.getUnitcost());
					invlot.setLotlinecost(-inv.getUnitcost()
							* inv.getQuantity());
					this.getBaseDao().saveObject(invlot);

				}

				inv.setState("�����");
				this.getBaseDao().updateObject(inv);

				// �޸��ʲ����豸��λ��Ϊ0139�ֿ�λ�á�
				List equiplist = this.getBaseDao().findWithQuery(
						Equipment.class,
						"eqnum='" + inv.getEqnum() + "' and lotnum='"
						+ inv.getLotnum() + "' and itemnum='"
						+ inv.getItemnum() + "'");
				if (equiplist.size() > 0) {
					Equipment equip = (Equipment) equiplist.get(0);
					equip.setLocation("0109");
					equip.setStatus("δ����");
					equip.setLabornum(this.getUserInfo().getLabornum());
					equip.setDeptnum("");
					this.getBaseDao().updateObject(equip);

				}

			}

		}

	}

	/*
	 * ���ܣ����ʲ�����������������������е�����������ͺźͷ�������ITEM���롣 ���ߣ����� ���ڣ�Dec 26, 2008 3:49:17 PM
	 */
	public int createitem(List list) throws Exception {
		int createcount = 0;
		for (int i = 0; i < list.size(); i++) {

			Wpmaterial wpmat = (Wpmaterial) list.get(i);
			if (Util.isNull(wpmat.getClassid())) {
				throw new Exception("����Ϊ:'" + wpmat.getDescription() + "'���Ϊ'"
						+ wpmat.getModelnum() + "'�ļ�¼�����಻��Ϊ��");
			}
			if (Util.isNull(wpmat.getOrderunit())) {
				throw new Exception("����Ϊ:'" + wpmat.getDescription() + "'���Ϊ'"
						+ wpmat.getModelnum() + "'�ļ�¼��������λ����Ϊ��");
			}
			if (Util.isNotNull(wpmat.getItemnum())) {
				continue;
			}
			List itemlist = this.getBaseDao().findWithQuery(
					Item.class,
					"description = '" + wpmat.getDescription()
					+ "' and modelnum ='" + wpmat.getModelnum() + "'");
			int existcount = 0;
			if (itemlist.size() >= 0) {
				existcount = itemlist.size();
			}

			// int existcount = this.getBaseDao().selectCountByHql("select
			// count(*) from Item t where t.description =
			// '"+wpmat.getDescription()+"' and t.modelnum
			// ='"+wpmat.getModelnum()+"'");
			if (existcount == 0) {
				Item item = new Item();

				// **********************************ITEMNUM���루��ʼ��*****************************************************************
				Double maxeq = 0d;
				long largeid = 0l;
				int totallength = 10;// �ʲ���ŵ���λ����
				int count = this.getBaseDao().selectCountByHql(
						"select count(*) from Item  t where t.classid = '"
						+ wpmat.getClassid() + "'");
				if (count > 0) {
					if (IBSServer.getIBSServer().getDatabaseProductName()
							.equals("SQLSERVER")) // �ж�ʹ�õ����ݿ���sqlserver����oracle���ݿ⡣
					{
						List maxidlist = this
						.getBaseDao()
						.selectListBySql(
								"select max(abs(substring(t.itemnum,len(t.classid)+1,len(t.itemnum)-len(t.classid)))) as largeid from Item  t where t.classid = '"
								+ wpmat.getClassid() + "'");
						if (maxidlist.size() > 0) {
							Map map = (Map) maxidlist.get(0);
							if (map.size() > 0) {
								maxeq = (Double) map.get("LARGEID") + 1;
								largeid = maxeq.longValue();
							}
						}

					} else// oracle
					{
						List maxidlist = this
						.getBaseDao()
						.selectListBySql(
								"select max(abs(substr(t.itemnum,length(t.classid)+1,length(t.itemnum)-length(t.classid)))) as largeid from Item  t where t.classid = '"
								+ wpmat.getClassid() + "'");

						if (maxidlist.size() > 0) {
							Map map = (Map) maxidlist.get(0);
							if (map.size() > 0) {
								BigDecimal maxvalue = (BigDecimal) map
								.get("LARGEID");
								maxeq = maxvalue.doubleValue() + 1;
								largeid = maxeq.longValue();
							}
						}
					}

				}

				String s = String.valueOf(largeid);
				String newitemnum = wpmat.getClassid();
				int len1 = s.length();
				int len2 = wpmat.getClassid().length();
				for (int j = 0; j < totallength - len1 - len2; j++) {
					newitemnum = newitemnum + "0";
				}
				newitemnum = newitemnum + s;

				item.setItemnum(newitemnum);
				// **********************************ITEMNUM����(����)*****************************************************************

				item.setDescription(wpmat.getDescription());
				item.setClassid(wpmat.getClassid());
				item.setModelnum(wpmat.getModelnum());
				item.setRotating("��");
				item.setLottype("���ι���");
				item.setOutside("��");
				item.setSpareautoadd("��");
				item.setInspectreq("��");
				item.setStatus("����");
				item.setStatusdate(new Date());
				item.setOrderunit(wpmat.getOrderunit());
				item.setIssueunit(wpmat.getOrderunit());
				createcount++;// �Դ����Ŀ����Ŀ���м�����
				this.getBaseDao().saveObject(item);

				wpmat.setItemnum(newitemnum);
				this.getBaseDao().updateObject(wpmat);

			} else {
				Item item = (Item) itemlist.get(0);
				throw new Exception("�Ѿ����ڱ���Ϊ��'" + item.getItemnum() + "'����Ϊ:'"
						+ wpmat.getDescription() + "' ����ͺ�Ϊ:'"
						+ wpmat.getModelnum() + "'���ʲ�,���ܽ��б��������");
			}
		}
		/** *************�ж��Ƿ���Ҫ����***************** */
		if (createcount == 0) {
			throw new Exception("��ѡ�еļ�¼������Ҫ����,��ȷ��!");

		} else {
			return createcount;
		}
	}

	/*
	 * ���ܣ������м�¼��ʶΪ�Ѵ�ӡ�� ���ߣ����� ���ڣ�Mar 4, 2009 3:05:18 PM
	 */
	public void hasprint(List list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			Invusetrans invu = (Invusetrans) list.get(i);
			invu.setIsprint("��");
			this.getBaseDao().updateObject(invu);

		}

	}
}
