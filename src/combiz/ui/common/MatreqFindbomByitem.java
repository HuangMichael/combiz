package combiz.ui.common;

import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
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
public class MatreqFindbomByitem extends CommonDialog {
	/**
	 * 
	 * @throws Exception
	 */
	public void searchpart() throws Exception {
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("matreqfindbomitemWnd");
		Wpmaterial wpmaterial = (Wpmaterial) this.getMainObject();
		clistwinfow.setQueryString("parent='" + wpmaterial.getItemnum() + "'");
		clistwinfow.refreshData();
	}

	@Override
	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("matreqfindbomitemWnd");
		Wpmaterial wpmaterial = (Wpmaterial) this.getMainObject();
		clistwinfow.setQueryString("parent='" + wpmaterial.getItemnum() + "'");
		clistwinfow.refreshData();
		super.onCreate();
	}

	public void addpart() throws Exception {
		CommonListWindow listWnd = (CommonListWindow) this
				.getFellow("matreqfindbomitemWnd");
		Matreq matreq =  (Matreq)this.getOwnerWnd().getMainObject();
		if (listWnd == null)
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			WpmaterialSrv wpmaterialSrv = (WpmaterialSrv)IBOSrvUtil.getSrv("Wpmaterial");
			wpmaterialSrv.addwpmaterialbybommatreq(retList, matreq);
			this.refreshData();
			this.onClose();
			Messagebox.show("所选BOM备件已添加！");
		}
		this.onClose();
	}

}
