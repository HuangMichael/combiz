package combiz.business.assetscard;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface AllocationSrv  extends IBOBaseSrv
{
	//在调拨申请明细行里插入数据；
	public void insertline(List list,Object s) throws Exception;
	//在调拨接收行里插入数据；
	public void addallocline(List list,Object obj) throws Exception;
	//检验调拨接收行；
	public void verify(List list,Object obj) throws Exception;

}
