package combiz.business.workorder;

import java.util.List;

import combiz.system.IBOBaseSrv;
/**
 * 
 * ����
 * 
 * ����:������
 * ����
 * ����Mar 3, 2009
 */
public interface WpmaterialSrv extends IBOBaseSrv
{
	/**
	 * ����:������
	 * �����ҵ��ղ���ӱ���(����)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Mar 3, 2009
	 */
	public void addwpmaterial(List list,Object obj)throws Exception;
	/**
	 * ����:������
	 * ���ݱ����������(����)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Mar 3, 2009
	 */
	public void addwpmaterialbypart(List list,Object obj)throws Exception;
	

	/**
	 * ����:������
	 * �����ҵ��ղؼ���ӱ���(��������)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Mar 3, 2009
	 */
	public void addwpmaterialbymatreq(List list,Object obj)throws Exception;
	
	/**
	 * ����:������
	 * �����豸�������(��������)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Mar 3, 2009
	 */
	public void addwpmaterialbypartmatreq(List list,Object obj)throws Exception;
	
	/**
	 * ����:������
	 * �������ʱ��������BOM����(����)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Mar 3, 2009
	 */
	public void addwpmaterialbybom(List list,Object obj)throws Exception;
	
	/**
	 * ����:������
	 * �������ʱ��������BOM����(��������)
	 * @param list
	 * @param obj
	 * @throws Exception
	 * ����:Mar 3, 2009
	 */
	public void addwpmaterialbybommatreq(List list,Object obj)throws Exception;
}
