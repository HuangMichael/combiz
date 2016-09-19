package combiz.ui.common;

import combiz.business.workorder.WpmaterialSrv;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Item;
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
public class FindbomByitem extends CommonDialog {
	/**
	 * 
	 * @throws Exception
	 */
	public void searchpart() throws Exception {
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("findbomitemWnd");
		Wpmaterial wpmaterial = (Wpmaterial) this.getMainObject();
		clistwinfow.setQueryString("parent='" + wpmaterial.getItemnum() + "'");
		clistwinfow.refreshData();
	}

	@Override
	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		CommonListWindow clistwinfow = (CommonListWindow) this
				.getFellow("findbomitemWnd");
		Wpmaterial wpmaterial = (Wpmaterial) this.getMainObject();
		//如果Itemnum等于空 按工单中的设备查出对应的Itemnum
		if(wpmaterial.getItemnum()==null){
			Workorder work = (Workorder)this.getOwnerWnd().getMainObject();
			List list = IBOSrvUtil.getBaseDao().findWithQuery(Equipment.class, "eqnum='"+work.getEqnum()+"'");
			if(list!=null && list.size()>0){
				Equipment equipment = (Equipment)list.get(0);
				wpmaterial.setItemnum(equipment.getItemnum());
			}
		}
		clistwinfow.setQueryString("parent='" + wpmaterial.getItemnum() + "'");
		clistwinfow.refreshData();
		super.onCreate();
	}

	public void addpart() throws Exception {
		CommonListWindow listWnd = (CommonListWindow) this
				.getFellow("findbomitemWnd");
		Workorder workorder =  (Workorder)this.getOwnerWnd().getMainObject();
		if (listWnd == null)
			return;
		List retList = listWnd.getSelectObjects();
		if (retList != null && retList.size() != 0) {
			WpmaterialSrv wpmaterialSrv = (WpmaterialSrv)IBOSrvUtil.getSrv("Wpmaterial");
			wpmaterialSrv.addwpmaterialbybom(retList, workorder);
			this.onClose();
			Messagebox.show("所选BOM备件已添加！");
		}
		this.onClose();
	}

}
