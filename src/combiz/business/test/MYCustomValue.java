package combiz.business.test;
import combiz.system.assetdraw.CustomValue;

/**
 * ͼ�λ��豸�ͻ��˵��õĲ��ֵ�Զ�����
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
