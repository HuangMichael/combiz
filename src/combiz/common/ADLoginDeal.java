package combiz.common;

import combiz.domain.user.Ibsusers;
import combiz.system.LoginAdapter;

/**
 * �Ѿ�ͨ��AD��֤��ϵͳ������Ҫ�ٴ���֤����
 * @author brianhong
 *
 */
public class ADLoginDeal implements LoginAdapter
{

	public boolean executeLogin(Ibsusers ibsusers, String loginid, String password) throws Exception
	{
		return true;
	}

}
