package combiz.ui.pr;

import combiz.business.pr.PrSrv;
import combiz.business.pr.PrlineSrv;
import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.inventory.Favorite;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class PrlineList extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////

	public PrlineList() {
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
		// PrWindow parentWnd = (PrWindow)ownerWnd;
		Pr pr = (Pr) this.getOwnerWnd().getMainObject();
		Prline pl = new Prline();
		// ��ֵĬ�ϲֿ�
		String defwarehouse = this.getUserInfo().getDefstoreroom();
		pl.setWarehouse(defwarehouse);
		int print = (int) this.mainSrv.getBaseDao().selectLongMaxByHql(
				"select max(p.prlinenum) from Prline p where p.prnum='"
				+ pr.getPrnum() + "' ");
		/*int PrlInt = this.mainSrv.getRowCountByWhere(pl, "prnum='"
				+ pr.getPrnum() + "'");*/
		List newprlinelist = this.getAddedObjectList();//
		print = print + newprlinelist.size();
		pl.setPrnum(pr.getPrnum());
		pl.setPrlinenum((long) print + 1);
		pl.setConversion(1.00);
		pl.setEnterdate(new Date());
		pl.setRequestedby(this.getLaborInfo().getLaborname());
		pl.setEnterby(this.getLaborInfo().getLaborname());
		pl.setProrateservice("��");
		pl.setWarehouse(this.getLaborInfo().getDefaultstoreloc());
		pl.setIsservice("��");
		pl.setLoadedcost(0d);
		//pl.setCorpnum(pr.getCorpnum());
		//pl.setSitenum(pr.getSitenum());
		this.mainObject = pl;
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
					Prline prline = (Prline) list.get(i);
					Favorite favorite = new Favorite();
					favorite.setItemnum(prline.getItemnum());
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
		Pr pr = (Pr) this.getOwnerWnd().getMainObject();
		// sql���ˣ�"/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		if(!pr.getStatus().equals("����δ����")){
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
			((PrlineSrv) this.getMainSrv()).addPrline(retList, pr);
			this.refreshData();
			Messagebox.show("�ղؼ�����ѡ��������ӣ�");
		}
	}

}
