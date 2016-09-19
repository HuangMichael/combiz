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

	// /////////////////////////////////方法区////////////////////////////////////////////////

	public PrlineList() {
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
		// PrWindow parentWnd = (PrWindow)ownerWnd;
		Pr pr = (Pr) this.getOwnerWnd().getMainObject();
		Prline pl = new Prline();
		// 赋值默认仓库
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
		pl.setProrateservice("否");
		pl.setWarehouse(this.getLaborInfo().getDefaultstoreloc());
		pl.setIsservice("否");
		pl.setLoadedcost(0d);
		//pl.setCorpnum(pr.getCorpnum());
		//pl.setSitenum(pr.getSitenum());
		this.mainObject = pl;
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
					Prline prline = (Prline) list.get(i);
					Favorite favorite = new Favorite();
					favorite.setItemnum(prline.getItemnum());
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
		Pr pr = (Pr) this.getOwnerWnd().getMainObject();
		// sql过滤："/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		if(!pr.getStatus().equals("流程未启动")){
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
			((PrlineSrv) this.getMainSrv()).addPrline(retList, pr);
			this.refreshData();
			Messagebox.show("收藏夹内所选物资已添加！");
		}
	}

}
