package combiz.ui.po;

import combiz.business.po.PolineSrv;
import combiz.domain.inventory.Favorite;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class PolineList extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////

	public PolineList() {
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * 
	 * @throws Exception
	 * @����� ���ڣ�2007-8-7
	 */
	public boolean addNew() throws Exception {
		// ��ȡ�������������
		PoWindow parentWnd = (PoWindow) ownerWnd;
		Po po = (Po) parentWnd.getMainObject();

		Poline pl = new Poline();

		int point = (int) this.mainSrv.getBaseDao().selectLongMaxByHql(
				"select max(p.polinenum) from Poline p where p.ponum='"
						+ po.getPonum() + "' ");
		List newprlinelist = this.getAddedObjectList();
		point = point + newprlinelist.size();
		pl.setPonum(po.getPonum());
		pl.setPolinenum((long) point + 1);
		pl.setEnterby(this.getLaborInfo().getLabornum());
		pl.setWarehouse(this.getLaborInfo().getDefaultstoreloc());
		pl.setEnterdate(new Date());
		pl.setReceiptscomplete("δ����");
		pl.setConversion(1d);
		pl.setProrated("��");
		pl.setInspection("��");
		pl.setService("��");
		pl.setReceivedqty(0d);
		pl.setReceivedunitcost(0d);
		pl.setReceivedtotalcost(0d);
		pl.setLoadedcost(0d);
		pl.setRejectedqty(0d);
		pl.setOrderqty(0d);
		this.mainObject = pl;
		pl.setRejectedqty(0d);
		return true;
	}

	/**
	 * ������ �����ղؼ� 2009-02-20
	 * 
	 * @throws Exception
	 */
	public void addfavorite() throws Exception {

		List list = this.getSelectObjects();
		if (list != null && list.size() != 0) {
			try {
				for (int i = 0; i < list.size(); i++) {
					Poline poline = (Poline) list.get(i);
					Favorite favorite = new Favorite();
					favorite.setItemnum(poline.getItemnum());
					favorite.setFavoriteby(this.getUserInfo().getLabornum());
					favorite.setFavoritedate(new Date());
					IBOSrvUtil.getBaseDao().saveObject(favorite);
				}
			} catch (Exception e) {
				throw new Exception("��ѡ�����ղؼ����Ѵ���");
			}
			Messagebox.show("��ѡ�����Ѿ������ҵ��ղ�");
		} else {
			Messagebox.show("��ѡ������Ҫ���������");
		}

	}

	/**
	 * ������ �����ղؼ� 2009-02-20
	 * 
	 * @throws Exception
	 */
	public void findfavorite() throws Exception {
		Po po = (Po) this.getOwnerWnd().getMainObject();
		// sql���ˣ�"/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		if (!po.getStatus().equals("����δ����")) {
			Messagebox.show("����������,���������˲���");
			return;
		}
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(
				this.mainObject, "/common/favoritelist.xul", "favoriteby='"
						+ this.getUserInfo().getLabornum() + "'");
		if (listWnd == null)
			return;
		// �ж��Ƿ�����ȷ����ť������ȡ����ť
		if (!listWnd.isConfirm())
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			((PolineSrv) this.getMainSrv()).addPoline(retList, po);
			this.refreshData();
			Messagebox.show("�ղؼ�����ѡ��������ӣ�");
		}
	}

}
