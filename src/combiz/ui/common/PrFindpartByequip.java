package combiz.ui.common;

import combiz.business.pr.PrlineSrv;
import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.MainWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;

public class PrFindpartByequip  extends CommonDialog {
	/**
	 * 
	 * @throws Exception
	 */
	public void searchpart() throws Exception {
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("prfindpartyequipWnd");
		Workorder workorder = (Workorder) this.getMainObject();
		clistwinfow.setQueryString("eqnum='" + workorder.getEqnum() + "'");
		clistwinfow.refreshData();
	}

	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("prfindpartyequipWnd");
		Workorder workorder = (Workorder) this.getMainObject();
		clistwinfow.setQueryString("eqnum='" + workorder.getEqnum() + "'");
		clistwinfow.refreshData();
		super.onCreate();
	}
	public void addpart() throws Exception {
		CommonListWindow listWnd = (CommonListWindow) this
				.getFellow("prfindpartyequipWnd");
		Pr pr = (Pr)this.getOwnerWnd().getMainObject();
		if (listWnd == null)
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size()!=0) {
			PrlineSrv prlineSrv = (PrlineSrv)IBOSrvUtil.getSrv("Prline");
			prlineSrv.addPrlinebypart(retList,pr);
			this.onClose();
			Messagebox.show("所选备件已添加！");
		}
		this.onClose();
	}
}
