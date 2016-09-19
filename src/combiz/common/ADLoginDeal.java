package combiz.common;

import combiz.domain.user.Ibsusers;
import combiz.system.LoginAdapter;

/**
 * 已经通过AD验证的系统，不需要再次验证密码
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
