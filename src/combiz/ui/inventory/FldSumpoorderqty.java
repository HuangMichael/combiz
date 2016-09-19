package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

public class FldSumpoorderqty extends FieldAdapter {

	/**
	 * 
	 * @TODO ���÷����״̬�£�Ϊֻ��
	 * @param component
	 * @throws Exception
	 * @��С�� 2007-8-16 ����10:54:34
	 */
	public void init(Component component) throws Exception {
	}

	public void validate(Component component) throws Exception {

	}

	/**
	 * @author ���� E-mail:superyang4208731@yahoo.com.cn
	 * @version ����ʱ�䣺2008-1-21����12:10:42 ��˵��
	 */
	public void action(Component component) throws Exception {

	}

	/**
	 * 
	 * @TODO ��ʼ�������ֶ�ֵ��ֻ�����������ֶ���
	 * @return Object ����һ��ֵ����
	 * @throws Exception
	 * @brianhong 2007-8-30
	 */
	public Object initValue() throws Exception {
		Object sumpoorderqty = 0.0D;
		Inventory inv = (Inventory) this.getMainObject();
		Double sumpoqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.orderqty) from Poline t where  t.ponum in(select p.ponum  from Po p where p.status not in ('ȡ��','�ر�')) and t.itemnum = '"+inv.getItemnum()+"' and t.warehouse = '"+inv.getWarehouse()+"' ");
		Double sumporecqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.receivedqty) from Poline t where  t.ponum in(select p.ponum  from Po p where p.status not in ('ȡ��','�ر�')) and t.itemnum = '"+inv.getItemnum()+"' and t.warehouse = '"+inv.getWarehouse()+"' ");
		if(sumpoqty == null)
			sumpoqty = 0d;
		if(sumporecqty == null)
			sumporecqty = 0d;
		sumpoorderqty = sumpoqty - sumporecqty;
		return sumpoorderqty;
	}

	/**
	 * @TODO ʹ��������ʽ�жϸ������ַ����Ƿ�Ϊ����(java.uitl.Number)
	 * @param str
	 * @return ��true,��false; yuanjq 2007-8-29 ����07:10:40
	 */
}
