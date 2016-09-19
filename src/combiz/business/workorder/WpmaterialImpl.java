package combiz.business.workorder;

import combiz.domain.equipment.Eqsparepart;
import combiz.domain.inventory.Favorite;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemeqbom;
import combiz.domain.inventory.Matreq;
import combiz.domain.user.Ibsusers;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;

import java.util.Date;
import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class WpmaterialImpl extends IBOBaseImpl implements WpmaterialSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Wpmaterial))
			throw new Exception("要删除的对象传递不正确！");
		Wpmaterial wpmaterial = (Wpmaterial) obj;
		Long id = wpmaterial.getId();
		String wonum = wpmaterial.getWonum();
		super.delete(obj);

		if(this.getRecWnd() != null)
		{
			String app = this.getRecWnd().getOwnerWnd().getApp();
			
			if (!(app.equals("EQREQ") || app.equals("MATREQ") || app.equals("BORROWEQU"))) {
				Workorder wo = (Workorder) this.getRecWnd().getOwnerWnd()
						.getMainObject();
				Double linecost = wpmaterial.getLinecost();
				Double matcost = wo.getEstmatcost();
				wo.setEstmatcost(matcost - linecost);
				this.getBaseDao().updateObject(wo);
			}
			
		}
		

		// 删除工单物料计划行的时候，同时修改工单的计划总成本！
		/*
		 * if(this.getRecWnd()!=null) { if(this.getRecWnd().getOwnerWnd() !=
		 * null) { if(this.getRecWnd().getOwnerWnd().getMainObject() instanceof
		 * Workorder) { List wolist =
		 * this.getBaseDao().findWithQuery(Workorder.class, "wonum =
		 * '"+wonum+"'"); Workorder wo = (Workorder) wolist.get(0); Double
		 * estmatcost = wo.getEstmatcost(); if (estmatcost==null) estmatcost=0d;
		 * if(id==null) //是新建记录 { } else //更新记录 { Double newsetmatcost =
		 * estmatcost - wpmaterial.getLinecost();
		 * wo.setEstmatcost(newsetmatcost); }
		 * this.getBaseDao().updateObject(wo); } } }
		 */
		// 删除本身
		// 删除关联对象－父类方法
		// this.deleteAllChild(obj, "");
	}

	/**
	 * 陈明锐 2009-02-20 重写save方法,核算工单的备件成本
	 */
	@Override
	public void save(Object arg0) throws Exception {
		if (arg0 instanceof Wpmaterial) {
			Wpmaterial wpmaterial = (Wpmaterial) arg0;
			// 在工单中添加物料进行的操作
			if (this.getRecWnd() != null) {
				if (this.getRecWnd().getOwnerWnd() != null) {
					if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Workorder) {
						String wonum = wpmaterial.getWonum();
						Double line_db = 0d;
						Workorder wo = null;
						// 从界面上获取，如果界面上有wo对象，那就不要从数据库中取了，避免重复对象
						if (this.getRecWnd() != null) {
							if (this.getRecWnd().getOwnerWnd() != null) {
								wo = (Workorder) this.getRecWnd().getOwnerWnd()
										.getMainObject();
							}
						}
						// 如果不是界面上调用的保存方法，那么获取wo肯定为null，那就从数据库中直接取。
						if (wo == null) {
							List wolist = this.getBaseDao().findWithQuery(
									Workorder.class, "wonum = '" + wonum + "'");
							if (wolist.size() > 0)
								wo = (Workorder) wolist.get(0);
						}
						if (wo != null) {
							if (wpmaterial.getId() == null) // 是新建记录
							{
								line_db = (Double) this.getBaseDao()
										.selectSumByHql(
												"select sum(t.linecost) from Wpmaterial t where t.wonum = '"
														+ wonum + "'");
							} else {
								// 过滤掉改动的数据 t.id != wpmaterial.getId()
								line_db = (Double) this.getBaseDao()
										.selectSumByHql(
												"select sum(t.linecost) from Wpmaterial t where t.wonum = '"
														+ wonum
														+ "' and t.id != "
														+ wpmaterial.getId());
							}
							if (line_db == null)
								line_db = 0d;
							Double linecost = wpmaterial.getLinecost();
							if (linecost == null)
								linecost = 0d;

							wo.setEstmatcost(linecost + line_db);
							this.getBaseDao().updateObject(wo);
						}
					}
				}
				// 保存自己
				super.save(wpmaterial);
			}
		}
		// 在申请发放的订单中添加物料进行的操作
	}

	/**
	 * 通过我的收藏添加物资 陈明锐 2009-2-20
	 */
	public void addwpmaterial(List list, Object obj) throws Exception {
		Workorder wo = (Workorder) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Favorite favorite = (Favorite) list.get(i);
				Wpmaterial wpmaterial = new Wpmaterial();
				wpmaterial.setWonum(wo.getWonum());
				wpmaterial.setJpnum(wo.getJpnum());
				wpmaterial.setWarehouse(this.getUserInfo().getDefstoreroom());
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ favorite.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					wpmaterial.setDescription(item.getDescription());
					wpmaterial.setModelnum(item.getModelnum());
					wpmaterial.setClassid(item.getClassid());
				}
				wpmaterial.setItemqty(0.00);
				wpmaterial.setLinecost(0.00);
				wpmaterial.setUnitcost(0.00);
				//wpmaterial.setCorpnum("INBASIS");
				wpmaterial.setItemnum(favorite.getItemnum());
				wpmaterial.setRequestby(favorite.getFavoriteby());
				this.getBaseDao().saveObject(wpmaterial);
			}
		}
	}

	public void addwpmaterialbypart(List list, Object obj) throws Exception {
		Workorder wo = (Workorder) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Eqsparepart eqsparepart = (Eqsparepart) list.get(i);
				Wpmaterial wpmaterial = new Wpmaterial();
				wpmaterial.setWonum(wo.getWonum());
				wpmaterial.setJpnum(wo.getJpnum());
				wpmaterial.setWarehouse(this.getUserInfo().getDefstoreroom());
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ eqsparepart.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					wpmaterial.setDescription(item.getDescription());
					wpmaterial.setModelnum(item.getModelnum());
					wpmaterial.setClassid(item.getClassid());
				}
				wpmaterial.setItemqty(0.00);
				wpmaterial.setLinecost(0.00);
				wpmaterial.setUnitcost(0.00);
				//wpmaterial.setCorpnum("INBASIS");
				wpmaterial.setItemnum(eqsparepart.getItemnum());
				wpmaterial.setRequestby(this.getUserInfo().getLabornum());
				this.getBaseDao().saveObject(wpmaterial);
			}
		}

	}

	public void addwpmaterialbymatreq(List list, Object obj) throws Exception {
		Matreq matreq = (Matreq) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Favorite favorite = (Favorite) list.get(i);
				Wpmaterial wpmaterial = new Wpmaterial();
				wpmaterial.setWarehouse(this.getUserInfo().getDefstoreroom());
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ favorite.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					wpmaterial.setDescription(item.getDescription());
					wpmaterial.setModelnum(item.getModelnum());
					wpmaterial.setClassid(item.getClassid());
				}
				wpmaterial.setMatreqnum(matreq.getMatreqnum());
				wpmaterial.setItemqty(0.00);
				wpmaterial.setLinecost(0.00);
				wpmaterial.setUnitcost(0.00);
				//wpmaterial.setCorpnum("INBASIS");
				wpmaterial.setItemnum(favorite.getItemnum());
				wpmaterial.setRequestby(favorite.getFavoriteby());
				this.getBaseDao().saveObject(wpmaterial);
			}
		}
	}

	public void addwpmaterialbypartmatreq(List list, Object obj)
			throws Exception {
		Matreq matreq = (Matreq) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Eqsparepart eqsparepart = (Eqsparepart) list.get(i);
				Wpmaterial wpmaterial = new Wpmaterial();
				wpmaterial.setWarehouse(this.getUserInfo().getDefstoreroom());
				List itemlist = this.findWithQuery(Item.class, "itemnum='"
						+ eqsparepart.getItemnum() + "'");
				if (itemlist != null && itemlist.size() != 0) {
					Item item = (Item) itemlist.get(0);
					wpmaterial.setDescription(item.getDescription());
					wpmaterial.setModelnum(item.getModelnum());
					wpmaterial.setClassid(item.getClassid());
				}
				wpmaterial.setMatreqnum(matreq.getMatreqnum());
				wpmaterial.setItemqty(0.00);
				wpmaterial.setLinecost(0.00);
				wpmaterial.setUnitcost(0.00);
				//wpmaterial.setCorpnum("INBASIS");
				wpmaterial.setItemnum(eqsparepart.getItemnum());
				wpmaterial.setRequestby(this.getUserInfo().getLabornum());
				this.getBaseDao().saveObject(wpmaterial);
			}
		}

	}

	public void addwpmaterialbybom(List list, Object obj) throws Exception {
		Workorder wo = (Workorder) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Itemeqbom itemeqbom = (Itemeqbom) list.get(i);
				Wpmaterial wpmaterial = new Wpmaterial();
				wpmaterial.setWonum(wo.getWonum());
				wpmaterial.setJpnum(wo.getJpnum());
				wpmaterial.setWarehouse(this.getUserInfo().getDefstoreroom());
				wpmaterial.setDescription(itemeqbom.getRemark());
				wpmaterial.setItemqty(0.00);
				wpmaterial.setLinecost(0.00);
				wpmaterial.setUnitcost(0.00);
				//wpmaterial.setCorpnum("INBASIS");
				wpmaterial.setItemnum(itemeqbom.getItemnum());
				wpmaterial.setRequestby(this.getUserInfo().getLabornum());
				this.getBaseDao().saveObject(wpmaterial);
			}
		}

	}

	public void addwpmaterialbybommatreq(List list, Object obj)
			throws Exception {
		Matreq matreq = (Matreq) obj;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Itemeqbom itemeqbom = (Itemeqbom) list.get(i);
				Wpmaterial wpmaterial = new Wpmaterial();
				wpmaterial.setWarehouse(this.getUserInfo().getDefstoreroom());
				wpmaterial.setDescription(itemeqbom.getRemark());
				wpmaterial.setMatreqnum(matreq.getMatreqnum());
				wpmaterial.setItemqty(0.00);
				wpmaterial.setLinecost(0.00);
				wpmaterial.setUnitcost(0.00);
				//wpmaterial.setCorpnum("INBASIS");
				wpmaterial.setItemnum(itemeqbom.getItemnum());
				wpmaterial.setRequestby(this.getUserInfo().getLabornum());
				this.getBaseDao().saveObject(wpmaterial);
			}
		}
	}
}
