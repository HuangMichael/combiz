package combiz.business.corp;

import combiz.domain.corp.Labor;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;

import java.util.List;

/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class LaborImpl extends IBOBaseImpl
implements LaborSrv {
	
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Labor))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		
	}
	
	@Override
	public void save(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		super.save(arg0);
	}

}
