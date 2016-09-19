package combiz.business.corp;

import combiz.domain.corp.Corporation;
import combiz.system.IBOBaseImpl;

/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class CorporationImpl extends IBOBaseImpl
implements CorporationSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Corporation))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "corpsite");
		this.deleteAllChild(obj, "addressTable");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
	
}
