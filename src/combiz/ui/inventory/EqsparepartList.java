package combiz.ui.inventory;

import combiz.domain.equipment.Eqsparepart;
import combiz.domain.inventory.Item;
import combiz.system.ui.ListWindow;

public class EqsparepartList 
extends ListWindow 
{

	@Override
	public boolean addNew()
	throws Exception
	{
		Item item = (Item) this.getOwnerWnd().getMainObject();
		
		Eqsparepart newobj = new Eqsparepart();
		newobj.setDescription(item.getDescription());
		newobj.setItemnum(item.getItemnum());
		newobj.setQuantity(0.0D);
		
		this.setMainObject(newobj);
		
		return true;
	}
	
}
