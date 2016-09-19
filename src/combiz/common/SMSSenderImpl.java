package combiz.common;

import combiz.domain.corp.Labor;
import combiz.system.common.MobileMessage;
import combiz.system.util.Util;

import java.util.Date;

public class SMSSenderImpl implements MobileMessage
{

	/**
	 * �����ֻ����Žӿڷ�����Ҳ������Ϊ�����������ѵĽӿڣ���RTX
	 * 
	 * brianhong  2009-4-23
	 * @param title  ����
	 * @param description  ��Ϣ��
	 * @param sendDate  ��Ϣʱ��
	 * @param fromLabor  ��Ϣ��Դ
	 * @param toLabor  ���͸�˭
	 * @param msgType  ��Ϣ���ͣ� 
							public static String MESSAGE = "MESSAGE";  ��Ϣ
							public static String WFTASK = "WFTASK";   ���̴�������
							public static String WFNOTIFY = "WFNOTIFY";   ��������֪ͨ
	 * @return
	 */
	public boolean executeSend(String title, String description, Date sendDate, 
			Labor fromLabor, Labor toLabor, String msgType, String httpUrl)
	{
		if(Util.isNotNull(toLabor.getMphone()))
		{
			//���Ͷ���
			System.out.println("�����ֻ����ţ�["+title+"]["+description+"]["+sendDate+"]["+toLabor.getLaborname()+"]");
		}
		
		
		//�����Ҫ���÷���QQ RTX��Ϣ֪ͨ���������ע��
		/*if(Util.isNotNull(toLabor.getPhonenum()))
		{
			this.sendRTXMessage(title, description, sendDate, fromLabor, toLabor, msgType, httpUrl);
		}*/
		
		return true;
	}
	
	/**
	 * RTX��Ϣ�ӿڣ����ϸ���������
	 * 
	 * brianhong  2009-4-23
	 * @param title  ����
	 * @param description  ��Ϣ��
	 * @param sendDate  ��Ϣʱ��
	 * @param fromLabor  ��Ϣ��Դ
	 * @param toLabor  ���͸�˭
	 * @param msgType  ��Ϣ���ͣ� 
							public static String MESSAGE = "MESSAGE";  ��Ϣ
							public static String WFTASK = "WFTASK";   ���̴�������
							public static String WFNOTIFY = "WFNOTIFY";   ��������֪ͨ
	 * @return
	 */
	/*private boolean sendRTXMessage(String title, String description, Date sendDate, 
			Labor fromLabor, Labor toLabor, String msgType, String httpUrl)
	{
		String receivers= toLabor.getHomephone();
    	String type = "0";
    	String delayTime = "0";
    	
    	int iRet= -1;
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.sendNotify(receivers, title, "["+description+"|"+httpUrl+"]", type, delayTime);
    		if (iRet == 0)
    		{
    			//System.out.println("���ͳɹ�");
    			RtxsvrapiObj.UnInit();
    			return true;
    		}
    		else 
    		{
    			//System.out.println("����ʧ��");
    			RtxsvrapiObj.UnInit();
    			return false;
    		}
    	}
		return true;
	}*/
	 
}
