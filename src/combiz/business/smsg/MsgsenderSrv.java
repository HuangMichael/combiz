package combiz.business.smsg;

import combiz.domain.smsg.Msgsender;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface MsgsenderSrv extends IBOBaseSrv
{
	
	/**
	 * @param msgsender
	 * @return
	 * @author:��Ⱥ��
	 * @description:���淢��
	 * @time:15:21 2007-7-27
	 */
	public void pubboard(Object obj)  throws Exception;
	/**
	 * @param msgsender
	 * @return
	 * @author:��Ⱥ��
	 * @description:����ȡ������
	 * @time:15:21 2007-7-27
	 */
	public void cancelpub(Object obj)  throws Exception;
	
	
	/**
	 * ������Ϣ
	 * brianhong  2008-6-4
	 * @param msgsend
	 * @param sendmail
	 * @throws Exception
	 */
	public void sendMessage(Object obj, boolean sendmail)
	throws Exception;
}
