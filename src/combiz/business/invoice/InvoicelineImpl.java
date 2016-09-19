package combiz.business.invoice;

import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.po.Po;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class InvoicelineImpl extends IBOBaseImpl implements InvoicelineSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Invoiceline))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Invoiceline invoiceline = (Invoiceline) obj;
		
		Double basetotalcost = 0d;
		if (this.getRecWnd() != null) {
			Invoice invoice = (Invoice) this.getRecWnd().getOwnerWnd().getMainObject();
			basetotalcost = invoice.getBasetotalcost();
			if (invoiceline.getId() != null) {
				Double dblinecost = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.taxlinecost) from Invoiceline t where t.id = '"
						+ invoiceline.getId() + "'");
				if (dblinecost == null) {
					dblinecost = 0d;
				}
				basetotalcost = basetotalcost - dblinecost;
			} 
			else 
			{
				basetotalcost = basetotalcost - invoiceline.getTaxlinecost();
			}
			invoice.setBasetotalcost(basetotalcost);
			this.getBaseDao().updateObject(invoice);

		}
		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "invociematch");

		// ɾ����Ʊ�е�ʱ��Ӧ�ø��·�Ʊ����ܳɱ���
		
	}

	@Override
	public void save(Object arg0) throws Exception {

		if (arg0 instanceof Invoiceline) {
			Invoiceline invoiceline = (Invoiceline) arg0;

			Invoice invoice = null;
			// �ӽ����ϻ�ȡ�������������invoice�����ǾͲ�Ҫ�����ݿ���ȡ�ˣ������ظ�����
			if (this.getRecWnd() != null) {
				if (this.getRecWnd().getOwnerWnd() != null) {
					if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Invoice) {
						invoice = (Invoice) this.getRecWnd().getOwnerWnd().getMainObject();
						Double basetotalcost = invoice.getBasetotalcost();
						if (basetotalcost == null)
							basetotalcost = 0d;
						if (invoiceline.getId() == null) // ���½���¼
						{
							basetotalcost = basetotalcost + invoiceline.getTaxlinecost();

						} else // ���¼�¼
						{
							Double dblinecost = (Double) this.getBaseDao()
									.selectSumByHql(
											"select sum(p.taxlinecost) from Invoiceline p where p.id = '"
													+ invoiceline.getId() + "'");
							if (dblinecost == null) {
								dblinecost = 0d;
							}
							basetotalcost = basetotalcost - dblinecost
									+ invoiceline.getTaxlinecost();
						}
						invoice.setBasetotalcost(basetotalcost);
						this.getBaseDao().updateObject(invoice);
					}
				}
			}
			
			// �����Լ�
			super.save(invoiceline);
			// }
		}

		// �����뷢�ŵĶ�����������Ͻ��еĲ���

	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////
}
