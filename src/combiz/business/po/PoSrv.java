package combiz.business.po;

import combiz.domain.po.Po;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PoSrv extends IBOBaseSrv
{
	//拷贝采购申请行
	public void copyprline(List list,Object obj) throws Exception;

	//拷贝物料接收事物处理
	public void addpoline(List list,Object obj) throws Exception;
	//将接收行进行检验，同时往数据库库存相关表写数据。
	public void verify(List list,Object obj,List polinelist) throws Exception;
	//拷贝采购单，将选中的采购单拷贝成为一个新的采购单
	public Po copypo(Object obj) throws Exception;
}
