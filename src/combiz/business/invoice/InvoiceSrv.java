package combiz.business.invoice;

import combiz.domain.invoice.Invoice;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface InvoiceSrv extends IBOBaseSrv {

	// 生成发票行
	public void CreateInvoiceline(List list, Object obj) throws Exception;

	// 校验发票行
	public void verify(List list, Object obj) throws Exception;

	// 批准采购发票
	public void approveinvoice(List list, Object obj) throws Exception;
	// 批准合同发票
	public void apprcntinv(List list, Object obj) throws Exception;

	// 生成发票差异
	public void createinvtrans(List list) throws Exception;
}
