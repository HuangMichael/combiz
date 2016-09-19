package combiz.ui.location;

import combiz.business.location.LocationsSrv;
import combiz.domain.location.Locations;
import combiz.system.ui.CommonDialog;
import com.inbasis.zul.Messagebox;

public class UpdateLocPopupDialog extends CommonDialog {

	@Override
	public void confirm() throws Exception {
		Locations loc = (Locations) this.mainObject;
		// String location = loc.getLocation();
		// String parent = loc.getParent();
		// if(location.substring(0,5).equals(parent.substring(0,5))){
		((LocationsSrv) this.getMainSrv()).updateLocCode(loc);
		super.confirm();
		Messagebox.show("更新完毕！");
		// }else{
		// Messagebox.show("该操作不允许！");
		// }
	}

}
