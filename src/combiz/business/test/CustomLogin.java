package combiz.business.test;

import combiz.domain.user.Ibsusers;
import combiz.system.LoginAdapter;

/**
 * ʾ������¼�ͻ�����
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
