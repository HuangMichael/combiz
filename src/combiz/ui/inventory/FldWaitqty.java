package combiz.ui.inventory;

import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

public class FldWaitqty extends FieldAdapter {

	/**
	 * 
	 * @TODO 设置非添加状态下，为只读
	 * @param component
	 * @throws Exception
	 * @洪小林 2007-8-16 上午10:54:34
	 */
	public void init(Component component) throws Exception {
	}

	public void validate(Component component) throws Exception {

	}

	/**
	 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
	 * @version 创建时间：2008-1-21下午12:10:42 类说明
	 */
	public void action(Component component) throws Exception {

	}

	/**
	 * 
	 * @TODO 初始化虚拟字段值，只能用于虚拟字段类
	 * @return Object 返回一个值对象
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
	 * @TODO 使用正则表达式判断给定的字符串是否为数字(java.uitl.Number)
	 * @param str
	 * @return 是true,否false; yuanjq 2007-8-29 下午07:10:40
	 */
}
