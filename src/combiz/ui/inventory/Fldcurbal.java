package combiz.ui.inventory;

import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

public class Fldcurbal extends FieldAdapter {

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
		Object sumcurbal = 0.0D;
		Item item = (Item) this.getMainObject();
		sumcurbal = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where  t.itemnum = '"+item.getItemnum()+"' ");
		if(sumcurbal == null)
			sumcurbal = 0d;
		return sumcurbal;
	}

	/**
	 * @TODO ʹ��������ʽ�жϸ������ַ����Ƿ�Ϊ����(java.uitl.Number)
	 * @param str
	 * @return ��true,��false; yuanjq 2007-8-29 ����07:10:40
	 */
}
