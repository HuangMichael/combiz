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

	// /////////////////////////////////方法区////////////////////////////////////////////////

	public PolineList() {
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * 
	 * @throws Exception
	 * @蒋祖兵 日期：2007-8-7
	 */
	public boolean addNew() throws Exception {
		// 获取父级主窗体对象
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
		pl.setReceiptscomplete("未接收");
		pl.setConversion(1d);
		pl.setProrated("否");
		pl.setInspection("否");
		pl.setService("否");
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
	 * 陈明锐 加入收藏夹 2009-02-20
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
				throw new Exception("所选物资收藏夹中已存在");
			}
			Messagebox.show("所选物资已经加入我的收藏");
		} else {
			Messagebox.show("请选择您需要加入的物资");
		}

	}

	/**
	 * 陈明锐 查找收藏夹 2009-02-20
	 * 
	 * @throws Exception
	 */
	public void findfavorite() throws Exception {
		Po po = (Po) this.getOwnerWnd().getMainObject();
		// sql过滤："/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		if (!po.getStatus().equals("流程未启动")) {
			Messagebox.show("流程已启动,不能再作此操作");
			return;
		}
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(
				this.mainObject, "/common/favoritelist.xul", "favoriteby='"
						+ this.getUserInfo().getLabornum() + "'");
		if (listWnd == null)
			return;
		// 判断是否点击了确定按钮，还是取消按钮
		if (!listWnd.isConfirm())
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			((PolineSrv) this.getMainSrv()).addPoline(retList, po);
			this.refreshData();
			Messagebox.show("收藏夹内所选物资已添加！");
		}
	}

}
