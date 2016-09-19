package combiz.business.contract;

import combiz.domain.po.Po;
import combiz.system.IBOBaseSrv;

public interface ContractSrv extends IBOBaseSrv {
	//通过合同，生成采购订单
	public String createpo(Object obj) throws Exception;
	
}
