package combiz.business.assetscard;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface AssetscardSrv  extends IBOBaseSrv
{
	//向月末结转表（carryover）中插入一行年月
	public void insert() throws Exception;
	//向月末结转表（carryover）中status修改状态
	public void uninsert() throws Exception;
}
