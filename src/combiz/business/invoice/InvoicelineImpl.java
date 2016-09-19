package combiz.business.invoice;

import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.po.Po;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class InvoicelineImpl extends IBOBaseImpl implements InvoicelineSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Invoiceline))
			throw new Exception("要删除的对象传递不正确！");
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
		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		// this.deleteAllChild(obj, "invociematch");

		// 删除发票行的时候，应该更新发票里的总成本！
		
	}

	@Override
	public void save(Object arg0) throws Exception {

		if (arg0 instanceof Invoiceline) {
			Invoiceline invoiceline = (Invoiceline) arg0;

			Invoice invoice = null;
			// 从界面上获取，如果界面上有invoice对象，那就不要从数据库中取了，避免重复对象
			if (this.getRecWnd() != null) {
				if (this.getRecWnd().getOwnerWnd() != null) {
					if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Invoice) {
						invoice = (Invoice) this.getRecWnd().getOwnerWnd().getMainObject();
						Double basetotalcost = invoice.getBasetotalcost();
						if (basetotalcost == null)
							basetotalcost = 0d;
						if (invoiceline.getId() == null) // 是新建记录
						{
							basetotalcost = basetotalcost + invoiceline.getTaxlinecost();

						} else // 更新记录
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
			
			// 保存自己
			super.save(invoiceline);
			// }
		}

		// 在申请发放的订单中添加物料进行的操作

	}

	// ///////////////////业务方法//////////////////////////////////
}
