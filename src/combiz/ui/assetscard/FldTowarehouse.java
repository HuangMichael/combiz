package combiz.ui.assetscard;

import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 11:41:58 AM  May 18, 2010 
 * ���ܣ��ڵ������յ�ʱ�򣬿���������ϸ��
 * �����allocationline
 * �����ֶΣ�towarehouse
 */
public class FldTowarehouse extends FieldAdapter {

	public void init(Component component) throws Exception {
	}

	public void validate(Component component) throws Exception {

	}

	public void action(Component component) throws Exception {

	}

	public Object initValue() throws Exception {
		Object towarehouse = this.getLaborInfo().getDefaultstoreloc();
		return towarehouse;
	}

	/**
	 * @TODO ʹ��������ʽ�жϸ������ַ����Ƿ�Ϊ����(java.uitl.Number)
	 * @param str
	 * @return ��true,��false; yuanjq 2007-8-29 ����07:10:40
	 */
}
