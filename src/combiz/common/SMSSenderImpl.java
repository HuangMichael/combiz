package combiz.common;

import combiz.domain.corp.Labor;
import combiz.system.common.MobileMessage;
import combiz.system.util.Util;

import java.util.Date;

public class SMSSenderImpl implements MobileMessage
{

	/**
	 * 发送手机短信接口方法！也可以作为发送其他提醒的接口，如RTX
	 * 
	 * brianhong  2009-4-23
	 * @param title  标题
	 * @param description  消息体
	 * @param sendDate  消息时间
	 * @param fromLabor  消息来源
	 * @param toLabor  发送给谁
	 * @param msgType  消息类型： 
							public static String MESSAGE = "MESSAGE";  消息
							public static String WFTASK = "WFTASK";   流程待办任务
							public static String WFNOTIFY = "WFNOTIFY";   流程任务通知
	 * @return
	 */
	public boolean executeSend(String title, String description, Date sendDate, 
			Labor fromLabor, Labor toLabor, String msgType, String httpUrl)
	{
		if(Util.isNotNull(toLabor.getMphone()))
		{
			//发送短信
			System.out.println("发送手机短信：["+title+"]["+description+"]["+sendDate+"]["+toLabor.getLaborname()+"]");
		}
		
		
		//如果需要调用发送QQ RTX消息通知，请打开下面注释
		/*if(Util.isNotNull(toLabor.getPhonenum()))
		{
			this.sendRTXMessage(title, description, sendDate, fromLabor, toLabor, msgType, httpUrl);
		}*/
		
		return true;
	}
	
	/**
	 * RTX消息接口，由上个方法调用
	 * 
	 * brianhong  2009-4-23
	 * @param title  标题
	 * @param description  消息体
	 * @param sendDate  消息时间
	 * @param fromLabor  消息来源
	 * @param toLabor  发送给谁
	 * @param msgType  消息类型： 
							public static String MESSAGE = "MESSAGE";  消息
							public static String WFTASK = "WFTASK";   流程待办任务
							public static String WFNOTIFY = "WFNOTIFY";   流程任务通知
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
    			//System.out.println("发送成功");
    			RtxsvrapiObj.UnInit();
    			return true;
    		}
    		else 
    		{
    			//System.out.println("发送失败");
    			RtxsvrapiObj.UnInit();
    			return false;
    		}
    	}
		return true;
	}*/
	 
}
