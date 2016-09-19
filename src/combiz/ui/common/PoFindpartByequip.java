package combiz.ui.common;

import combiz.business.po.PolineSrv;
import combiz.business.pr.PrlineSrv;
import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;

public class PoFindpartByequip  extends CommonDialog {
	/**
	 * 
	 * @throws Exception
	 */
	public void searchpart() throws Exception {
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("pofindpartyequipWnd");
		Workorder workorder = (Workorder) this.getMainObject();
		clistwinfow.setQueryString("eqnum='" + workorder.getEqnum() + "'");
		clistwinfow.refreshData();
	}

	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("pofindpartyequipWnd");
		Workorder workorder = (Workorder) this.getMainObject();
		clistwinfow.setQueryString("eqnum='" + workorder.getEqnum() + "'");
		clistwinfow.refreshData();
		super.onCreate();
	}
	public void addpart() throws Exception {
		CommonListWindow listWnd = (CommonListWindow) this
				.getFellow("pofindpartyequipWnd");
		Po po = (Po)this.getOwnerWnd().getMainObject();
		if (listWnd == null)
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size()!=0) {
			PolineSrv polineSrv = (PolineSrv)IBOSrvUtil.getSrv("Poline");
			polineSrv.addPolinebypart(retList,po);
			this.onClose();
			Messagebox.show("所选备件已添加！");
		}
		this.onClose();
	}
}
