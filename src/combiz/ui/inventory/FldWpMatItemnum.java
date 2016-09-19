package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

import combiz.business.inventory.ItemSrv;
import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IBandbox;

public class FldWpMatItemnum extends FieldAdapter
{

	/**
	 * 自动写入wpmateril表中的其他相关字段
	 * 
	 * brianhong  2007-11-12
	 * @param arg0
	 * @throws Exception
	 */
	@Override
	public void action(Component arg0)
	throws Exception
	{
		IBandbox itembox = (IBandbox) arg0;
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+itembox.getValue()+"'");
		if(itemlist.size()>0)
		{
			Item item = (Item) itemlist.get(0);
			this.setValueByColname("description", item.getDescription());
			this.setValueByColname("modelnum", item.getModelnum());
			this.setValueByColname("orderunit", item.getOrderunit());
		}
		
	}

	@Override
	public void init(Component arg0)
	throws Exception 
	{
		
	}

	@Override
	public void validate(Component arg0) 
	throws Exception 
	{

	}	
}
