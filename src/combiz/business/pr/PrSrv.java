package combiz.business.pr;

import combiz.domain.pr.Pr;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PrSrv extends IBOBaseSrv
{
	//生成询价单及询价单行
	public String ceaterfq(Object obj) throws Exception;
	
	//生成采购单、采购单行
	public String createpo(Object obj) throws Exception;
	
	public String copypr(List list,Object obj) throws Exception;
	//合并需求计划行到采购申请行
	public void uniteprline(List list,Object obj) throws Exception;
	
}
