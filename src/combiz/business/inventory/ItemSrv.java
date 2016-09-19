package combiz.business.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface ItemSrv extends IBOBaseSrv
{
	public void cretetree(List classlist) throws Exception;
	public void createinventory(Inventory inventory) throws Exception;
	public void createeqnum(Object obj) throws Exception;
}