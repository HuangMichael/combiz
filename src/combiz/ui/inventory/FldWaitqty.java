package combiz.ui.inventory;

import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

public class FldWaitqty extends FieldAdapter {

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
		Object waitqty = 0.0D;
		Poline poline = (Poline) this.getMainObject();
		Double reqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + poline.getPonum() + "' and t.polinenum='"+poline.getPolinenum()+"' and itemnum = '"+poline.getItemnum()+"'");
		System.out.println("select sum(t.quantity) from Invrectrans t where t.ponum = '" + poline.getPonum() + "'");
		if (poline.getOrderqty() == null) {
			poline.setOrderqty(0D);
		}
		if(reqty == null)
			reqty = 0d;
        waitqty = Double.valueOf(poline.getOrderqty() - reqty);
		return waitqty;
	}

	/**
	 * @TODO ʹ��������ʽ�жϸ������ַ����Ƿ�Ϊ����(java.uitl.Number)
	 * @param str
	 * @return ��true,��false; yuanjq 2007-8-29 ����07:10:40
	 */
}
