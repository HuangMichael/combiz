package combiz.ui.assetscard;

import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 11:41:58 AM  May 18, 2010 
 * 功能：在调拨接收的时候，拷贝接收明细行
 * 捆绑表：allocationline
 * 捆绑字段：towarehouse
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
	 * @TODO 使用正则表达式判断给定的字符串是否为数字(java.uitl.Number)
	 * @param str
	 * @return 是true,否false; yuanjq 2007-8-29 下午07:10:40
	 */
}
