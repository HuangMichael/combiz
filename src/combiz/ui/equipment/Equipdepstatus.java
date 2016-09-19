package combiz.ui.equipment;

import combiz.domain.equipment.Equipdep;
import combiz.system.ui.adapter.PreClassAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Image;
import com.inbasis.zul.Window;

public class Equipdepstatus implements PreClassAdapter {

	public Component execute(Window recWnd, Object mainObject, String fieldname, String fieldvalue)
			throws Exception {
		Equipdep checkitem = (Equipdep) mainObject;
		Image image = null;
		if (checkitem.getStatus().equals("δ"))
		{
			image = new Image("/images/red.gif");
		} else {
				image = new Image("/images/green.gif");
		}
		return image;
	}

}
