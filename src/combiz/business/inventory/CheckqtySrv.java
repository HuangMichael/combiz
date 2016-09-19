package combiz.business.inventory;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface CheckqtySrv  extends IBOBaseSrv
{
	//根据指定的盘点条件生成实时库存在盘点日期当天的库存余量。
	public void generate(Object obj,String wherestr) throws Exception;
	//清除生成的盘点库存明细。
	public void cleardata(List list) throws Exception;
	//根据盘点单库存明细修改库存余量。
	public void modifycurbal(List list) throws Exception;

}
