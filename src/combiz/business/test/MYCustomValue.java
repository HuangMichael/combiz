package combiz.business.test;
import combiz.system.assetdraw.CustomValue;

/**
 * 图形化设备客户端调用的测点值自定义类
 * @author Administrator
 *
 */
public class MYCustomValue implements CustomValue 
{

	public Double execute() throws Exception 
	{
		return Math.random() * 1000 / 1 - Math.random();
	}
}
