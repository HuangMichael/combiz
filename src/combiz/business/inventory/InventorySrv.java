package combiz.business.inventory;

import combiz.domain.inventory.Inventory;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface InventorySrv extends IBOBaseSrv
{
//	在库存应用程序里对库存项目进行箱柜的调整
	public void adjustbinnum(Object obj) throws Exception;
//	在库存应用程序里对库存项目进行发放
	public void issue(Object obj) throws Exception;
//	在库存应用程序里对库存项目进行物料余量调整
	public void editcurbal(Object obj) throws Exception;
//	在库存应用程序里对库存项目进行物料平均成本调整
	public void rsetavgcost(Object obj) throws Exception;
//	重订购补充库存余量
	public int autogenerate(List list) throws Exception;
}
