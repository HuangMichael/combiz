package combiz.business.corp;

import combiz.domain.corp.Corpaddress;
import combiz.system.IBOBaseImpl;

import java.util.Date;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class CorpaddressImpl extends IBOBaseImpl
implements CorpaddressSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Corpaddress))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}


	/**
	 * @param msgsender
	 * @return
	 * @author:��Ⱥ��
	 * @description:����
	 * @time:15:21 2007-8-6
	 */
	@Override
	public void save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if(!(obj instanceof Corpaddress))
			throw new Exception("Ҫ����Ķ��󴫵ݲ���ȷ��");
		Corpaddress address = (Corpaddress)obj;
		address.setChangeby(this.getUserInfo().getLabornum());
		address.setChangedate(new Date());
		super.save(obj);
	}
	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
