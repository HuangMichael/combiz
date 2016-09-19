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
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class MatreqImpl extends IBOBaseImpl implements MatreqSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		// this.deleteAllChild(obj, "");
	}

	/*
	 * 功能：在进行发放申请时，点击操作菜单下的发放预留后，触发该事件，往invusetrans表里插数据； 作者：李阳 日期：Nov 4, 2008
	 * 12:52:39 PM
	 */
	public void geninvuse(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;
		for (int i = 0; i < list.size(); i++) {
			Invreserve inverserve = (Invreserve) list.get(i);
			Invusetrans invuse = new Invusetrans();
			invuse.setItemnum(inverserve.getItemnum());
			invuse.setWarehouse(inverserve.getWarehouse());
			invuse.setMatreqnum(matreq.getMatreqnum());
			invuse.setIssuetype("发放");
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
				// throw new Exception("库存余量小于您申请的数量，请先修改申请数量或提出采购申请！");
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
				invuse.setState("待确认");
				invuse.setReqqty(issueqty);
				// }
			} else
				throw new Exception("库存暂时没有您要申请的库存项目，请先提出采购申请！");
			super.save(invuse);

		}
	}

	/*
	 * 功能：在发放（设备）应用程序中，根据选中的周转件进行发放！ 作者：李阳 日期：Nov 4, 2008 12:53:04 PM
	 */
	public void genequse(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;
		String reqtype = matreq.getReqtype();
		String matreqnum = matreq.getMatreqnum();

		/** ************判断选择的要发放的数量是否大于预留数量*************** */
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
						+ "' and state ='待确认'");
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
					Double resqty = invres.getReservedqty();// 预留数量
					Double sumqty = 0d;// 选中的将要发放的记录
					for (int k = 0; k < list.size(); k++) {
						Invrectrans invrec = (Invrectrans) list.get(k);// 取出选中的接收记录；
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
							+ "'and t.issuetype = '发放'  and t.state = '待确认' ");
					if (hasqty == null) {
						hasqty = 0d;
					}
					if (hasqty + sumqty - invres.getReservedqty() > 0) {
						throw new Exception("您选择的要发放的资产数量大于预留数量，不能发放！");
					}

				}

			}

			for (int i = 0; i < list.size(); i++) {
				Invrectrans invrec = (Invrectrans) list.get(i);
				Invusetrans invuse = new Invusetrans();
				invuse.setItemnum(invrec.getItemnum());
				invuse.setWarehouse(invrec.getTowarehouse());
				invuse.setMatreqnum(matreq.getMatreqnum());

				if(reqtype.equals("设备借用申请"))
				{
					invuse.setIssuetype("借用");
				}
				else
				{
					invuse.setIssuetype("发放");
				}

				invuse.setIsprint("否");
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

					invuse.setState("待确认");
				} else
					throw new Exception("库存暂时没有您要申请的库存项目，请先提出采购申请！");
				super.save(invuse);

			}
		}
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：生成预留信息，如果库存中没有足够余量的话，生成采购申请记录。 日期：4:36:31 PM Dec 26, 2008
	 * 
	 */

	public void createinvr(Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
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
					throw new Exception("描述为:'" + wpmat.getDescription()
							+ "'规格为'" + wpmat.getModelnum() + "'的记录，采购编码不能为空");
				}
				if (Util.isNull(wpmat.getWarehouse())) {
					throw new Exception("描述为:'" + wpmat.getDescription()
							+ "'规格为'" + wpmat.getModelnum() + "'的记录，仓库不能为空");
				}
				if (wpmat.getItemqty()==null || wpmat.getItemqty()<=0) {
					throw new Exception("描述为:'" + wpmat.getDescription()
							+ "'规格为'" + wpmat.getModelnum() + "'的记录，领用数量不能为小于零！");
				}
				this.createserve(wpmat, matreq);

				/** **************是否生成采购申请********************** */
				String sql = "select sum(inv.curbal) from Invstock inv where inv.itemnum = '"
					+ wpmat.getItemnum()
					+ "' and warehouse ='"
					+ wpmat.getWarehouse() + "'";
				// 库存总余量
				Double sumcurbal = (Double) this.getBaseDao().selectSumByHql(
						sql);
				// 预留数量
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

				// 需要采购数量
				Double needorderqty = 0d;
				// Double needorderqty = sumcurbal - reserveqty
				// - wpmat.getItemqty();
				if (reserveqty - sumcurbal >= 0)// 当前的库存余量已经预留给已经批准的申请，只是暂时没有发出。
				{
					needorderqty = -wpmat.getItemqty();
				} else// 现有库存除去预留数量（包括已经预留，但未发出数量），还有结余。
				{
					needorderqty = sumcurbal - reserveqty - wpmat.getItemqty();
				}
				if (needorderqty < 0) {
					if (Util.isNotNull(isgov) && isgov.equals("是"))// 是否政府采购
					{

						if (po == null) {
							po = (Po) this.createpo(wpmat, matreq);
						}
						if (po instanceof Po) {
							this.createpoline(wpmat, po, -needorderqty);
						}

					} else// 一般采购
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

			matreq.setStatus("已批准");
			super.getBaseDao().updateObject(matreq);

			// 判断是否自动启动工作流
			// ************************自动启动工作流(开始)××××××××××××××××××××××××××××××××××××//
			if (pr != null) {
				PrSrv prsrv = (PrSrv) IBOSrvUtil.getSrv("pr");
				try {
					prsrv.workflow(pr, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ************************自动启动工作流(结束)××××××××××××××××××××××××××××××××××××//
			Messagebox.show("预留生成完毕！");
		} else {
			Messagebox.show("该单据下没有满足条件的记录或者已经全部生成预留，无法生成预留！");
			return;
		}
	}

	/**
	 * 方法 预留表里插入数据
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
			throw new Exception("请补充仓库信息！");
		}
		Invreserve invreserve = new Invreserve();
		invreserve.setDirectreq("否");
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

	// 生成采购申请
	public Pr createpr(Wpmaterial mater, Matreq mat) throws Exception {
		Pr pr = new Pr();
		PrSrv prsrv = (PrSrv) IBOSrvUtil.getSrv("pr");
		String desc = "由申请{" + mat.getMatreqnum() + ":" + mat.getDescription()
		+ "}生成";
		pr.setPrnum(prsrv.genInskey("prnum"));
		pr.setStatus("采购员确认");
		pr.setPrnumtype("领用申请生成");
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
		pr.setPrtype("采购申请");
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

	// 生成采购申请行
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
		pl.setProrateservice("否");
		pl.setIsservice("否");
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
				pl.setStocktype(inventory.getStocktype());// 库存类型
				unitcost = inventory.getAvgcost();
			}

		}
		pl.setManufacturer(wpmat.getManufacturer());
		pl.setOrderqty(genqty);// 订购数量
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

		// 回写到wpmaterial表里数据
		wpmat.setPrnum(pl.getPrnum());
		wpmat.setPrlinenum(pl.getPrlinenum());
		this.getBaseDao().updateObject(wpmat);
	}

	// --------------------------------------------------------------------

	// 生成采购申请
	public Po createpo(Wpmaterial mater, Matreq mat) throws Exception {
		Po po = new Po();
		PoSrv posrv = (PoSrv) IBOSrvUtil.getSrv("po");
		String desc = "由申请{" + mat.getMatreqnum() + ":" + mat.getDescription()
		+ "}生成";
		po.setPonum(posrv.genInskey("ponum"));
		po.setStatus("采购员确认");
		po.setStatusdate(new Date());
		String labornum = this.getLaborInfo().getLabornum();
		po.setChangeby(labornum);
		Labor labor = (Labor) this.getBaseDao().findUniqueBy(Labor.class,
				"labornum", labornum);
		po.setChangedate(new Date());
		po.setDescription(desc);
		po.setIsprotocol("否");
		po.setTotalcost(0d);
		po.setVendor("请指定供应商");
		po.setPotype("否");
		po.setReceipts("未接收");
		po.setIsgov("是");
		//po.setSitenum(mat.getSitenum());
		//po.setCorpnum(mat.getCorpnum());
		this.getBaseDao().saveObject(po);
		return po;
	}

	// 生成采购申请行
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
		pl.setProrated("否");
		pl.setInspection("否");
		pl.setService("否");
		pl.setReceivedqty(0d);
		pl.setReceivedtotalcost(0d);
		pl.setReceivedunitcost(0d);
		pl.setRejectedqty(0d);
		pl.setService("否");
		pl.setReceiptscomplete("否");
		pl.setRequestedby(wpmat.getRequestby());
		pl.setWarehouse("仓库一");
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
				pl.setStocktype(inventory.getStocktype());// 库存类型
				unitcost = inventory.getAvgcost();
			}

		}
		pl.setManufacturer(wpmat.getManufacturer());
		pl.setOrderqty(genqty);// 订购数量
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

		// 回写到wpmaterial表里数据
		wpmat.setPrnum(pl.getPonum());
		wpmat.setPrlinenum(pl.getPolinenum());
		this.getBaseDao().updateObject(wpmat);
	}

	// -------------------------------------------------------------------------

	/*
	 * 功能：对发放行进行校验。 作者：李阳 日期：Nov 4, 2008 12:53:31 PM
	 */
	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;
		String reqtype = matreq.getReqtype();

		if (reqtype.equals("物资领用申请")) {
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				// 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
				// super.save(inv);
				/***************************************************************
				 * ***************************判断是否是批次管理的****************************************************************
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
					if (lottype.equals("批次管理") && Util.isNull(inv.getLotnum())) {
						throw new Exception("库存项目'" + itemnum
								+ "'为批次管理的物资，请在发放行中选择箱柜确定发放的批次！");
					}
				}

				List invstocklist = null;
				Double issueqty = inv.getQuantity();
				if (Util.isNotNull(inv.getItemnum())) {
					if (lottype.equals("批次管理")) // 批次管理
					{
						if (Util.isNotNull(inv.getBinnum()))// 箱柜不为空
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
						} else// 箱柜为空
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
							inv.setState("已完成");
							super.save(inv);
						} else {
							if (curbal - issueqty == 0) {
								invstock.setCurbal(0d);
								super.save(invstock);
								super.delete(invstock);
								inv.setState("已完成");
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
								throw new Exception("发放行中库存项目编号为："
										+ inv.getItemnum()
										+ "库存余量小于发放数量,不能进行发放");
						}

					} else // 非批次管理
					{
						if (Util.isNotNull(inv.getBinnum()))// 箱柜不为空
						{
							invstocklist = this.getBaseDao().findWithQuery(
									Invstock.class,
									"itemnum ='" + inv.getItemnum()
									+ "' and warehouse ='"
									+ inv.getWarehouse()
									+ "' and binnum = '"
									+ inv.getBinnum()
									+ "'");
						} else// 箱柜为空
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
								inv.setState("已完成");
								super.save(inv);
							} else {
								if (curbal - issueqty == 0) {
									invstock.setCurbal(0d);
									super.save(invstock);
									super.delete(invstock);
									inv.setState("已完成");
									super.save(inv);
								} else
									throw new Exception("发放行中库存项目编号为："
											+ inv.getItemnum()
											+ "库存余量小于发放数量,不能进行发放");
							}
						}
					}

					/** ***************修改预留数量********* */
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

		} else// 设备的领用申请
		{
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				/***************************************************************
				 * ***************************判断是否是批次管理的****************************************************************
				 * ************************************************************************************************************
				 * ************************************************************************************************************
				 */
				Double issueqty = inv.getQuantity();
				String loc = inv.getLocation();
				if (Util.isNull(loc)) {
					throw new Exception("资产编号为'" + inv.getEqnum()
							+ "'的资产发放位置为空，请核实！");
				}

				/***************************************************************
				 * ******************箱柜号不为空情况***********************
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
							inv.setState("已完成");
							super.getBaseDao().updateObject(inv);
						} else {
							if (curbal - issueqty == 0) {
								// invstock.setCurbal(0d);
								// super.getBaseDao().updateObject(invstock);
								super.getBaseDao().deleteObject(invstock);
								inv.setState("已完成");
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
								throw new Exception("发放行中库存项目编号为："
										+ inv.getItemnum()
										+ "库存余量小于发放数量,不能进行发放");
						}

						// 写回equipment表数据
						List equiplist = this.getBaseDao().findWithQuery(
								Equipment.class,
								"eqnum ='" + inv.getEqnum() + "'");
						if (equiplist.size() > 0) {
							Equipment equipment = (Equipment) equiplist.get(0);
							equipment.setLocation(inv.getLocation());
							equipment.setDeptnum(inv.getIssuedeptnum());
							equipment.setLabornum(inv.getIssuetolabor());
							equipment.setRundate(new Date());
							equipment.setStatus("在用");
							this.getBaseDao().updateObject(equipment);

						}

					}

				} else
					throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");

				/** ***************修改预留数量********* */
				
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
	 * 功能：库存项目发放后，且已完成，可以进行退库操作 作者：李阳 日期：Nov 4, 2008 12:53:52 PM
	 */
	public void returnissue(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;
		String matreqnum = matreq.getMatreqnum();
		String reqtype = matreq.getReqtype();

		if (reqtype.equals("物资领用申请"))// 物资领用申请领出的库存退货
		{
			for (int i = 0; i < list.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) list.get(i);
				List invuselist = this.getBaseDao().findWithQuery(
						Invusetrans.class,
						"matreqnum = '" + matreqnum + "' and itemnum ='"
						+ invusetrans.getItemnum()
						+ "'and issuetype = '发放' and state ='已完成'");
				if (invuselist.size() > 0) {
					Invusetrans newinvuse = new Invusetrans();
					newinvuse.setItemnum(invusetrans.getItemnum());
					newinvuse.setWarehouse(invusetrans.getWarehouse());
					newinvuse.setMatreqnum(invusetrans.getMatreqnum());
					newinvuse.setLocation(invusetrans.getLocation());
					newinvuse.setIssuetype("退回");
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
								+ "'and state ='已完成'");
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
								+ "'and binnum is null and state ='已完成'");
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
						newinvuse.setState("待确认");
						//newinvuse.setSitenum(invusetrans.getSitenum());
						//newinvuse.setCorpnum(invusetrans.getCorpnum());
						super.save(newinvuse);
					} else
						throw new Exception("没有可以退库的发放行！");
				}
			}
		} else// 设备领用申请领出的库存退货
		{

			for (int i = 0; i < list.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) list.get(i);
				List invuselist = this.getBaseDao().findWithQuery(
						Invusetrans.class,
						"matreqnum = '" + matreqnum + "' and itemnum ='"
						+ invusetrans.getItemnum() + "' and eqnum = '"
						+ invusetrans.getEqnum() + "' and lotnum = '"
						+ invusetrans.getLotnum()
						+ "'and issuetype = '发放' and state ='已完成'");
				if (invuselist.size() > 0) {
					Double curbal = 0d;
					Double physcnt = 0d;
					Invusetrans newinvuse = new Invusetrans();
					newinvuse.setItemnum(invusetrans.getItemnum());
					newinvuse.setWarehouse(invusetrans.getWarehouse());
					newinvuse.setMatreqnum(invusetrans.getMatreqnum());
					newinvuse.setLocation("0109");
					newinvuse.setIssuetype("退回");
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
							+ "'and state ='已完成'");
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
					newinvuse.setState("待确认");
					//newinvuse.setSitenum(invusetrans.getSitenum());
					//newinvuse.setCorpnum(invusetrans.getCorpnum());
					this.getBaseDao().saveObject(newinvuse);

				}
				// else
				// throw new Exception("没有可以退库的发放行！");
			}
		}

	}

	/*
	 * 功能：在进行退库操作后，对退库的退库行记录进行校验。 作者：李阳 日期：Nov 4, 2008 12:54:02 PM
	 */
	public void verifyreturn(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");

		Matreq matreq = (Matreq) obj;
		String reqtype = matreq.getReqtype();

		if (reqtype.equals("物资领用申请"))// 物资领用申请领出的库存退货验证
		{
			for (int i = 0; i < list.size(); i++) {
				Invusetrans inv = (Invusetrans) list.get(i);
				/***************************************************************
				 * ******************************************************************
				 * 
				 **************************************************************/

				// 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
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
						// '"+ inv.getBinnum() + "'and issuetype ='发放' and state
						// ='已完成'");
						hasissueqty = (Double) this.getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
								+ inv.getMatreqnum()
								+ "' and itemnum ='"
								+ inv.getItemnum()
								+ "'and binnum = '"
								+ inv.getBinnum()
								+ "'and state ='已完成'");
						if (hasissueqty + returnqty >= 0) {
							invstock.setCurbal(curbal - returnqty);
							avgcost = (-returnqty * inv.getUnitcost() + curbal
									* inventory.getAvgcost())
									/ (curbal - returnqty);
							inventory.setAvgcost(avgcost);
							this.getBaseDao().updateObject(inventory);
							this.getBaseDao().updateObject(invstock);
							inv.setState("已完成");
							super.getBaseDao().updateObject(inv);

						} else {
							throw new Exception("库存项目编号为：" + inv.getItemnum()
									+ "且类型为退库的发放行中退库数量大于发放合计数量，不能退库");
						}
					} else
						throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");

				} else {
					List invstocklist = this.getBaseDao().findWithQuery(
							Invstock.class,
							"itemnum ='" + inv.getItemnum()
							+ "' and warehouse ='" + inv.getWarehouse()
							+ "' and binnum is null");
					if (invstocklist.size() > 0)// 正常情况下的退库。
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
								+ "'and issuetype ='发放' and state ='已完成'");
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
							inv.setState("已完成");
							this.getBaseDao().updateObject(inv);

						} else {
							throw new Exception("库存项目编号为：" + inv.getItemnum()
									+ "且类型为退库的发放行中退库数量大于发放合计数量，不能退库");

						}

					} else// 异常情况，invstock表中的数据丢失
					{
						Invstock invstock = new Invstock();
						invstock.setItemnum(inv.getItemnum());
						invstock.setWarehouse(inv.getWarehouse());
						invstock.setBinnum(inv.getBinnum());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						invstock.setPhyscnt(inv.getQuantity());
						invstock.setPhyscntdate(new Date());
						invstock.setCurbal(inv.getQuantity());
						invstock.setReconciled("否");
						//invstock.setSitenum(inv.getSitenum());
						//invstock.setCorpnum(inv.getCorpnum());
						super.save(invstock);
					}

				}
			}

		} else// 领出的周转件的退回验证
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
						+ "'and state ='已完成' and lotnum='"
						+ inv.getLotnum() + "'");

				if (hasissueqty + returnqty < 0) {

					throw new Exception("库存项目编号为：" + inv.getItemnum()
							+ "且类型为退库的发放行中退库数量大于发放合计数量，不能退库");
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
					invstock.setReconciled("是");
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

				inv.setState("已完成");
				this.getBaseDao().updateObject(inv);

				// 修改资产中设备的位置为0139仓库位置。
				List equiplist = this.getBaseDao().findWithQuery(
						Equipment.class,
						"eqnum='" + inv.getEqnum() + "' and lotnum='"
						+ inv.getLotnum() + "' and itemnum='"
						+ inv.getItemnum() + "'");
				if (equiplist.size() > 0) {
					Equipment equip = (Equipment) equiplist.get(0);
					equip.setLocation("0109");
					equip.setStatus("未启用");
					equip.setLabornum(this.getUserInfo().getLabornum());
					equip.setDeptnum("");
					this.getBaseDao().updateObject(equip);

				}

			}

		}

	}

	/*
	 * 功能：在资产的领用申请里，根据申请行中的描述、规格型号和分类生成ITEM编码。 作者：李阳 日期：Dec 26, 2008 3:49:17 PM
	 */
	public int createitem(List list) throws Exception {
		int createcount = 0;
		for (int i = 0; i < list.size(); i++) {

			Wpmaterial wpmat = (Wpmaterial) list.get(i);
			if (Util.isNull(wpmat.getClassid())) {
				throw new Exception("描述为:'" + wpmat.getDescription() + "'规格为'"
						+ wpmat.getModelnum() + "'的记录，分类不能为空");
			}
			if (Util.isNull(wpmat.getOrderunit())) {
				throw new Exception("描述为:'" + wpmat.getDescription() + "'规格为'"
						+ wpmat.getModelnum() + "'的记录，订购单位不能为空");
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

				// **********************************ITEMNUM编码（开始）*****************************************************************
				Double maxeq = 0d;
				long largeid = 0l;
				int totallength = 10;// 资产编号的总位数；
				int count = this.getBaseDao().selectCountByHql(
						"select count(*) from Item  t where t.classid = '"
						+ wpmat.getClassid() + "'");
				if (count > 0) {
					if (IBSServer.getIBSServer().getDatabaseProductName()
							.equals("SQLSERVER")) // 判断使用的数据库是sqlserver还是oracle数据库。
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
				// **********************************ITEMNUM编码(结束)*****************************************************************

				item.setDescription(wpmat.getDescription());
				item.setClassid(wpmat.getClassid());
				item.setModelnum(wpmat.getModelnum());
				item.setRotating("是");
				item.setLottype("批次管理");
				item.setOutside("否");
				item.setSpareautoadd("否");
				item.setInspectreq("否");
				item.setStatus("在用");
				item.setStatusdate(new Date());
				item.setOrderunit(wpmat.getOrderunit());
				item.setIssueunit(wpmat.getOrderunit());
				createcount++;// 对创建的库存项目进行计数。
				this.getBaseDao().saveObject(item);

				wpmat.setItemnum(newitemnum);
				this.getBaseDao().updateObject(wpmat);

			} else {
				Item item = (Item) itemlist.get(0);
				throw new Exception("已经存在编码为：'" + item.getItemnum() + "'描述为:'"
						+ wpmat.getDescription() + "' 规格型号为:'"
						+ wpmat.getModelnum() + "'的资产,不能进行编码操作。");
			}
		}
		/** *************判断是否不需要编码***************** */
		if (createcount == 0) {
			throw new Exception("您选中的记录都不需要编码,请确认!");

		} else {
			return createcount;
		}
	}

	/*
	 * 功能：将所有记录标识为已打印！ 作者：李阳 日期：Mar 4, 2009 3:05:18 PM
	 */
	public void hasprint(List list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			Invusetrans invu = (Invusetrans) list.get(i);
			invu.setIsprint("是");
			this.getBaseDao().updateObject(invu);

		}

	}
}
