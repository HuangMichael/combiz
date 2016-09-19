package combiz.ui.workorder;

import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.budget.Budget;
import combiz.domain.inventory.Favorite;
import combiz.domain.inventory.Item;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inbasis.zul.Messagebox;

public class WpmaterialList extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WpmaterialList() {
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
		// WorkorderWindow parentWnd = (WorkorderWindow)ownerWnd;
		RecWindow window = this.ownerWnd;
		Workorder parent = (Workorder) window.getMainObject();
		String dept = this.getLaborInfo().getDeptnum();
		Wpmaterial newobj = new Wpmaterial();
		newobj.setWonum(parent.getWonum());
		String budnum = null;
		List list = this.getMainSrv().getBaseDao().findWithQuery(Budget.class, "buddept='"+dept+"'");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Budget budget= (Budget)list.get(0);
				budnum = budget.getBudnum();				
			}
		}
		newobj.setBudnum(budnum);
		newobj.setItemqty(0.0);
		newobj.setUnitcost(0.0);
		newobj.setLinecost(0.0);

		this.mainObject = newobj;
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
					Wpmaterial wpmaterial = (Wpmaterial) list.get(i);
					Favorite favorite = new Favorite();
					favorite.setItemnum(wpmaterial.getItemnum());
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
		Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		///////////////////�ղ�
		// sql���ˣ�"/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		if(!workorder.getStatus().equals("����δ����")){
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
			((WpmaterialSrv) this.getMainSrv()).addwpmaterial(retList,
					workorder);
			this.refreshData();
			Messagebox.show("�ղؼ�����ѡ��������ӣ�");
		}
	}

	/**
	 * ������ �����豸ѡ�񱸼� 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("����׼")) {
		CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
				"/common/findpartbyequip.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		this.refreshData();
		// }else{
		// Messagebox.show("��������׼�޷���ӱ���");
		// }
	}
}
