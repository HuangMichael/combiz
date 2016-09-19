package combiz.business.rfq;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface RfqSrv extends IBOBaseSrv
{
	//生成报价行
	public void createquot(Object obj) throws Exception;
	//通过询价供应商及对应的报价行生成采购单和采购单行
	//public String CreatePo(Rfqvendor rv,Rfq rfq) throws Exception;
	public void CreatePo(List rvlist,Rfq rfq) throws Exception;
	//拷贝采购申请行
	public void CopyPrline(Object obj,List list) throws Exception;
	//根据授予的询价单行生成和同行
	public void createcont(List rvlist,List vendorlist,Rfq rfq) throws Exception;
}
