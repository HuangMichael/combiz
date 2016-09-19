package combiz.business.invoice;

import combiz.domain.invoice.Invoice;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface InvoiceSrv extends IBOBaseSrv {

	// ���ɷ�Ʊ��
	public void CreateInvoiceline(List list, Object obj) throws Exception;

	// У�鷢Ʊ��
	public void verify(List list, Object obj) throws Exception;

	// ��׼�ɹ���Ʊ
	public void approveinvoice(List list, Object obj) throws Exception;
	// ��׼��ͬ��Ʊ
	public void apprcntinv(List list, Object obj) throws Exception;

	// ���ɷ�Ʊ����
	public void createinvtrans(List list) throws Exception;
}
