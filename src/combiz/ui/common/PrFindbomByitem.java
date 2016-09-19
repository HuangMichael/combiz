package combiz.ui.common;

import combiz.business.pr.PrlineSrv;
import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Pr;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;

/**
 * 陈明锐
 * 
 * @author Administrator
 * 
 */
public class PrFindbomByitem extends CommonDialog {
	/**
	 * 
	 * @throws Exception
	 */
	public void searchpart() throws Exception {
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("prfindbomitemWnd");
		Wpmaterial wpmaterial = (Wpmaterial) this.getMainObject();
		clistwinfow.setQueryString("parent='" + wpmaterial.getItemnum() + "'");
		clistwinfow.refreshData();
	}

	@Override
	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("prfindbomitemWnd");
		Wpmaterial wpmaterial = (Wpmaterial) this.getMainObject();
		clistwinfow.setQueryString("parent='" + wpmaterial.getItemnum() + "'");
		clistwinfow.refreshData();
		super.onCreate();
	}

	public void addpart() throws Exception {
		CommonListWindow listWnd = (CommonListWindow) this
				.getFellow("prfindbomitemWnd");
		Pr pr = (Pr)this.getOwnerWnd().getMainObject();
		if (listWnd == null)
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			PrlineSrv prlineSrv = (PrlineSrv)IBOSrvUtil.getSrv("Prline");
			prlineSrv.addPrlinebybom(retList, pr);
			this.refreshData();
			this.onClose();
			Messagebox.show("所选BOM备件已添加！");
		}
		this.onClose();
	}

}
