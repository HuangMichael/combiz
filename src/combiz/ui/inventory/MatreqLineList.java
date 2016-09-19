package combiz.ui.inventory;

import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Favorite;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class MatreqLineList extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public MatreqLineList() {
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew() throws Exception {
		// ��ȡ�������������
		Matreq parent = (Matreq) ownerWnd.getMainObject();

		Wpmaterial newobj = new Wpmaterial();
		newobj.setMatreqnum(parent.getMatreqnum());
		newobj.setWonum(parent.getWonum());
		newobj.setItemqty(0.0);
		newobj.setUnitcost(0.0);
		newobj.setLinecost(0.0);
		newobj.setWarehouse(this.getLaborInfo().getDefaultstoreloc());
		newobj.setRequestby(parent.getRequestby());
		newobj.setRequiredate(new Date());
		String budgetnum = parent.getBudnum();
		String budgetitem = parent.getBuditem();
		newobj.setBuditem(budgetitem);
		newobj.setBudnum(budgetnum);
		this.mainObject = newobj;
		return true;
	}

	/**
	 * ������ �����ղؼ� 2009-02-20
	 * 
	 * @throws Exception
	 */
	public void addfavorite() throws Exception {
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		if(!matreq.getStatus().equals("����δ����")){
			Messagebox.show("����������,���������˲���");
			return;
		}
		List list = this.getSelectObjects();
		if (list != null && list.size() != 0) {
			try {
				for (int i = 0; i < list.size(); i++) {
					Wpmaterial wpmaterial = (Wpmaterial) list.get(i);
					Favorite favorite = new Favorite();
					favorite.setItemnum(wpmaterial.getItemnum());
					List listeq = IBOSrvUtil.getBaseDao().findWithQuery(Equipment.class, "itemnum='"+wpmaterial.getItemnum()+"'");
					if(listeq!=null && listeq.size()>0){
						Equipment equipment = (Equipment)listeq.get(0);
						favorite.setEqnum(equipment.getEqnum());
					}
					favorite.setFavoriteby(this.getUserInfo().getLabornum());
					favorite.setFavoritedate(new Date());
					IBOSrvUtil.getBaseDao().saveObject(favorite);
				}
			} catch (Exception e) {
				throw new Exception("��ѡ�����ղؼ����Ѵ���");
			}
			Messagebox.show("��ѡ�����Ѿ������ҵ��ղ�");
		} else {
			Messagebox.show("��ѡ������Ҫ����ı���");
		}

	}

	/**
	 * ������ �����ղؼ� 2009-02-20
	 * 
	 * @throws Exception
	 */
	public void findfavorite() throws Exception {
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		// sql���ˣ�"/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		String sql = "favoriteby='"+ this.getUserInfo().getLabornum() + "'";
		if(matreq.getReqtype().equals("������������")){
			sql += " and eqnum is null" ;
		}else{
			sql += " and eqnum is not null" ;
		}
		if(!matreq.getStatus().equals("����δ����")){
			Messagebox.show("����������,���������˲���");
			return;
		}
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(
				this.mainObject, "/common/favoritelist.xul", sql);
		if (listWnd == null)
			return;
		// �ж��Ƿ�����ȷ����ť������ȡ����ť
		if (!listWnd.isConfirm())
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			((WpmaterialSrv) this.getMainSrv()).addwpmaterialbymatreq(retList,
					matreq);
			this.refreshData();
			Messagebox.show("�ղؼ�����ѡ��������ӣ�");
		}
	}

}
