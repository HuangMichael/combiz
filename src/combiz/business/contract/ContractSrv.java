package combiz.business.contract;

import combiz.domain.po.Po;
import combiz.system.IBOBaseSrv;

public interface ContractSrv extends IBOBaseSrv {
	//ͨ����ͬ�����ɲɹ�����
	public String createpo(Object obj) throws Exception;
	
}
