package combiz.ui.location;

import combiz.system.ui.CommonDialog;

public class AddLocationDialog extends CommonDialog
{
	public void addLoc()
	{
		this.setConfirm(true);
		this.onClose();
	}
}
