package combiz.ui.inventory;

import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldRealqty extends FieldAdapter {

	public void init(Component component) throws Exception {
	}

	public void validate(Component component) throws Exception {

	}

	/**
	 * @author ���� E-mail:superyang4208731@yahoo.com.cn
	 * @version ����ʱ�䣺2008-1-21����12:10:42 ��˵��
	 */
	public void action(Component component) throws Exception {
		Double realqty = (Double) this.getValueByColname("realqty");
		Double waitqty = (Double) this.getValueByColname("waitqty");
		if(realqty - waitqty >0)
		{
			Messagebox.show("ʵ�ʽ����������ܴ���Ӧ������������ȷ�ϣ�");
			return;
		}

	}

	public Object initValue() throws Exception {
		Object realqty = 0.0D;
		Poline poline = (Poline) this.getMainObject();
		Double reqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + poline.getPonum() + "' and t.polinenum='"+poline.getPolinenum()+"' and itemnum = '"+poline.getItemnum()+"'");
		System.out.println("select sum(t.quantity) from Invrectrans t where t.ponum = '" + poline.getPonum() + "'");
		if (poline.getOrderqty() == null) {
			poline.setOrderqty(0D);
		}
		if(reqty == null)
			reqty = 0d;
		realqty = Double.valueOf(poline.getOrderqty() - reqty);
		return realqty;
	}

	/**
	 * @TODO ʹ��������ʽ�жϸ������ַ����Ƿ�Ϊ����(java.uitl.Number)
	 * @param str
	 * @return ��true,��false; yuanjq 2007-8-29 ����07:10:40
	 */
}
