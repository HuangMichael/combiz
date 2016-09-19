/*package combiz.ws.server;

import combiz.business.corp.LaborSrv;
import combiz.domain.corp.Labor;
import combiz.system.IBOSrvUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class SayHiService
{
	*//**
	 * 返回字符串
	 * brianhong  2009-4-24
	 * @param hi
	 * @return
	 * @throws Exception
	 *//*
	@WebMethod
	public String getHello(String hi) 
	throws Exception
	{
		return "服务器回复您：您也 " + hi;
	}
	
	*//**
	 * 测试返回字节码
	 * brianhong  2009-4-24
	 * @return
	 * @throws Exception
	 *//*
	@WebMethod
	public String getStringByte()
	throws Exception
	{
		File file = new File("d:\\部件组装结构图.png");
		FileInputStream input = new FileInputStream(file);
		byte[] fileByte = new byte[(int)file.length()];
		input.read(fileByte);
		return (new sun.misc.BASE64Encoder()).encode(fileByte);
	}
	
	
	@WebMethod
	@WebResult(name="LaborList")
	public List<Labor> getLabors(@WebParam(name="labornum") String labornum) 
	throws Exception
	{
		LaborSrv laborSrv = (LaborSrv) IBOSrvUtil.getSrv("labor");
		return laborSrv.findWithQuery("");
	}
	
	@WebMethod
	@WebResult(name="inserOk")
	public boolean newLabor(List<Labor> laborList)
	{
		for(int i=0;i<laborList.size();i++)
		{
			Labor labor = laborList.get(i);
			System.out.println("插入labor："+labor.getLabornum());
			System.out.println("插入姓名："+labor.getLaborname());
		}
		return true;
	}
	
	@WebMethod
	public String sayHello(String hi) 
	throws Exception
	{
		return "webservice测试返回dddddddddddddddddddddd： " + hi;
	}
	
}  
*/