package combiz.ui.inventory;

import combiz.domain.inventory.Invrectrans;
import combiz.system.ui.adapter.PreClassAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Image;
import com.inbasis.zul.Window;

public class InvTblChanged implements PreClassAdapter {

	public Component execute(Window recWnd, Object mainObject, String fieldname, String fieldvalue)
			throws Exception {
		Invrectrans invrectrans = (Invrectrans) mainObject;
		Image image = null;
		if (invrectrans.getStatus() == null) {
			image = new Image("/images/btn_unitem.gif");
		} else {
			if (invrectrans.getStatus().equals("´ý¼ìÑé")) {
				image = new Image("/images/btn_unitem.gif");
			} else if (invrectrans.getStatus().equals("´ý±àºÅ")) {
				image = new Image("/images/btn_uneqnum.gif");
			} else {
				image = new Image("/images/btn_itemchecked.gif");
			}
		}
		return image;
	}

}
