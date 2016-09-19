package combiz.business.test;

import combiz.domain.user.Ibsusers;
import combiz.system.LoginAdapter;

/**
 * 示例：登录客户化类
 * @author brianhong
 *
 */
public class CustomLogin implements LoginAdapter {

	public boolean executeLogin(Ibsusers ibsusers, String userid, String passwd)
	throws Exception 
	{
		return true;
	}

}
