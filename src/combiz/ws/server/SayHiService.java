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
	 * �����ַ���
	 * brianhong  2009-4-24
	 * @param hi
	 * @return
	 * @throws Exception
	 *//*
	@WebMethod
	public String getHello(String hi) 
	throws Exception
	{
		return "�������ظ�������Ҳ " + hi;
	}
	
	*//**
	 * ���Է����ֽ���
	 * brianhong  2009-4-24
	 * @return
	 * @throws Exception
	 *//*
	@WebMethod
	public String getStringByte()
	throws Exception
	{
		File file = new File("d:\\������װ�ṹͼ.png");
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
			System.out.println("����labor��"+labor.getLabornum());
			System.out.println("����������"+labor.getLaborname());
		}
		return true;
	}
	
	@WebMethod
	public String sayHello(String hi) 
	throws Exception
	{
		return "webservice���Է���dddddddddddddddddddddd�� " + hi;
	}
	
}  
*/