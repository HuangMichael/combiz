package combiz.business.inventory;

import java.util.List;

import combiz.system.IBOBaseSrv;


public interface WarehouseSrv extends IBOBaseSrv
{
	public void verify(List list,Object obj) throws Exception;//校验发放
//	发放过后的库存项目可以退库
	public void returnissue(List list,Object obj) throws Exception;
//	对物料申请退库物料行进行校验
	public void verifyreturn(List list,Object obj) throws Exception;
//	对物料申请退库物料行取消退库
	public void verifyreturnall(List list,Object obj) throws Exception;
}
