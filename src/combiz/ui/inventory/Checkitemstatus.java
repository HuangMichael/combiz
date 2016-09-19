package combiz.ui.inventory;

import combiz.domain.inventory.Checkqtyitem;
import combiz.system.ui.adapter.PreClassAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Image;
import com.inbasis.zul.Window;

public class Checkitemstatus implements PreClassAdapter {

	public Component execute(Window recWnd, Object mainObject,
			String fieldname, String fieldvalue) throws Exception {
		Checkqtyitem checkitem = (Checkqtyitem) mainObject;
		Image image = null;
		if (checkitem.getActualqty() != null
				&& checkitem.getAccountqty() != null) {
			if (checkitem.getActualqty() - checkitem.getAccountqty() > 0)
			// ≈Ã”Ø
			{
				image = new Image("/images/red.gif");
			} else {
				if (checkitem.getActualqty() - checkitem.getAccountqty() < 0)
				// ≈Ãø˜
				{
					image = new Image("/images/green.gif");
				} else if (checkitem.getActualqty() - checkitem.getAccountqty() == 0)
				// ’ ŒÔ“ª÷¬
				{
					image = new Image("/images/yellow.gif");
				}

			}
		}
		return image;
	}

}
