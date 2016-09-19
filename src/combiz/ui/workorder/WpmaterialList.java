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

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WpmaterialList() {
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew() throws Exception {
		// 获取父级主窗体对象
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
	 * 陈明锐 加入收藏夹 2009-02-20
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
				throw new Exception("所选备件收藏夹中已存在");
			}
			Messagebox.show("所选备件已经加入我的收藏");
		} else {
			Messagebox.show("请选择您需要加入的备件");
		}

	}

	/**
	 * 陈明锐 查找收藏夹 2009-02-20
	 * 
	 * @throws Exception
	 */
	public void findfavorite() throws Exception {
		Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		///////////////////收藏
		// sql过滤："/common/favoritelist.xul","favoriteby='"+this.getUserInfo().getLabornum()+"'
		// and itemnum not in (select w.itemnum from Wpmaterial w where
		// wonum='"+workorder.getWonum()+"')"
		if(!workorder.getStatus().equals("流程未启动")){
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
			((WpmaterialSrv) this.getMainSrv()).addwpmaterial(retList,
					workorder);
			this.refreshData();
			Messagebox.show("收藏夹内所选物资已添加！");
		}
	}

	/**
	 * 陈明锐 根据设备选择备件 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("已批准")) {
		CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
				"/common/findpartbyequip.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		this.refreshData();
		// }else{
		// Messagebox.show("流程已批准无法添加备件");
		// }
	}
}
