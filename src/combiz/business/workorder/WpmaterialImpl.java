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
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class WpmaterialImpl extends IBOBaseImpl implements WpmaterialSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Wpmaterial))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
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
		

		// ɾ���������ϼƻ��е�ʱ��ͬʱ�޸Ĺ����ļƻ��ܳɱ���
		/*
		 * if(this.getRecWnd()!=null) { if(this.getRecWnd().getOwnerWnd() !=
		 * null) { if(this.getRecWnd().getOwnerWnd().getMainObject() instanceof
		 * Workorder) { List wolist =
		 * this.getBaseDao().findWithQuery(Workorder.class, "wonum =
		 * '"+wonum+"'"); Workorder wo = (Workorder) wolist.get(0); Double
		 * estmatcost = wo.getEstmatcost(); if (estmatcost==null) estmatcost=0d;
		 * if(id==null) //���½���¼ { } else //���¼�¼ { Double newsetmatcost =
		 * estmatcost - wpmaterial.getLinecost();
		 * wo.setEstmatcost(newsetmatcost); }
		 * this.getBaseDao().updateObject(wo); } } }
		 */
		// ɾ������
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "");
	}

	/**
	 * ������ 2009-02-20 ��дsave����,���㹤���ı����ɱ�
	 */
	@Override
	public void save(Object arg0) throws Exception {
		if (arg0 instanceof Wpmaterial) {
			Wpmaterial wpmaterial = (Wpmaterial) arg0;
			// �ڹ�����������Ͻ��еĲ���
			if (this.getRecWnd() != null) {
				if (this.getRecWnd().getOwnerWnd() != null) {
					if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Workorder) {
						String wonum = wpmaterial.getWonum();
						Double line_db = 0d;
						Workorder wo = null;
						// �ӽ����ϻ�ȡ�������������wo�����ǾͲ�Ҫ�����ݿ���ȡ�ˣ������ظ�����
						if (this.getRecWnd() != null) {
							if (this.getRecWnd().getOwnerWnd() != null) {
								wo = (Workorder) this.getRecWnd().getOwnerWnd()
										.getMainObject();
							}
						}
						// ������ǽ����ϵ��õı��淽������ô��ȡwo�϶�Ϊnull���Ǿʹ����ݿ���ֱ��ȡ��
						if (wo == null) {
							List wolist = this.getBaseDao().findWithQuery(
									Workorder.class, "wonum = '" + wonum + "'");
							if (wolist.size() > 0)
								wo = (Workorder) wolist.get(0);
						}
						if (wo != null) {
							if (wpmaterial.getId() == null) // ���½���¼
							{
								line_db = (Double) this.getBaseDao()
										.selectSumByHql(
												"select sum(t.linecost) from Wpmaterial t where t.wonum = '"
														+ wonum + "'");
							} else {
								// ���˵��Ķ������� t.id != wpmaterial.getId()
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
				// �����Լ�
				super.save(wpmaterial);
			}
		}
		// �����뷢�ŵĶ�����������Ͻ��еĲ���
	}

	/**
	 * ͨ���ҵ��ղ�������� ������ 2009-2-20
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
