package combiz.ui.inventory;

import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn 11:57:32 AM Nov 3, 2008
 *         ���ܣ�������δ������Ԥ������ �����INVRESERVE �����ֶΣ�ACTUALQTY
 */
public class FldActualqty extends FieldAdapter {

	public void init(Component component) throws Exception {
	}

	public void validate(Component component) throws Exception {

	}

	public void action(Component component) throws Exception {

	}

	public Object initValue() throws Exception {
		Object actualqty = 0.0D;
		Double inuqty = 0d;
		Invreserve invreserve = (Invreserve) this.getMainObject();
		if (this.getRecWnd().getOwnerWnd()!= null) {
			Object obj = this.getRecWnd().getOwnerWnd().getMainObject();
			if (obj instanceof Matreq) {
				inuqty = (Double) this.getMainSrv().getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
										+ invreserve.getReqnum()
										+ "' and t.itemnum = '"
										+ invreserve.getItemnum() + "'");
			} else {
				inuqty = (Double) this.getMainSrv().getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.wonum ='"
										+ invreserve.getWonum()
										+ "' and t.itemnum = '"
										+ invreserve.getItemnum() + "'");
			}

			if (inuqty == null)
				inuqty = 0d;
			actualqty = Double.valueOf(invreserve.getReservedqty() - inuqty);
		}
		return actualqty;
	}

	/**
	 * @TODO ʹ��������ʽ�жϸ������ַ����Ƿ�Ϊ����(java.uitl.Number)
	 * @param str
	 * @return ��true,��false; yuanjq 2007-8-29 ����07:10:40
	 */
}
