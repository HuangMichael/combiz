package combiz.ui.common;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;

/**
 * 陈明锐
 * 
 * @author Administrator
 * 
 */
public class MatreqFindpartByequip extends CommonDialog {
	/**
	 * 
	 * @throws Exception
	 */
	public void searchpart() throws Exception {
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("matreqfindpartbyequipWnd");
		Workorder workorder = (Workorder) this.getMainObject();
		clistwinfow.setQueryString("eqnum='" + workorder.getEqnum() + "'");
		clistwinfow.refreshData();
	}

	@Override
	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("matreqfindpartbyequipWnd");
		Workorder workorder = (Workorder) this.getMainObject();
		clistwinfow.setQueryString("eqnum='" + workorder.getEqnum() + "'");
		clistwinfow.refreshData();
		super.onCreate();
	}

	public void addpart() throws Exception {
		CommonListWindow listWnd = (CommonListWindow) this
				.getFellow("matreqfindpartbyequipWnd");
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		if (listWnd == null)
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			WpmaterialSrv wpmaterialSrv = (WpmaterialSrv) IBOSrvUtil
					.getSrv("Wpmaterial");
			wpmaterialSrv.addwpmaterialbypartmatreq(retList, matreq);
			this.refreshData();
			this.onClose();
			Messagebox.show("所选备件已添加！");
		}
		this.onClose();
	}

}
