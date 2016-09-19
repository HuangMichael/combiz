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
	 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
	 * @version 创建时间：2008-1-21下午12:10:42 类说明
	 */
	public void action(Component component) throws Exception {
		Double realqty = (Double) this.getValueByColname("realqty");
		Double waitqty = (Double) this.getValueByColname("waitqty");
		if(realqty - waitqty >0)
		{
			Messagebox.show("实际接收数量不能大于应接收数量，请确认！");
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
	 * @TODO 使用正则表达式判断给定的字符串是否为数字(java.uitl.Number)
	 * @param str
	 * @return 是true,否false; yuanjq 2007-8-29 下午07:10:40
	 */
}
