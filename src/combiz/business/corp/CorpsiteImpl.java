package combiz.business.corp;

import combiz.domain.corp.Corpsite;
import combiz.system.IBOBaseImpl;

import java.util.Date;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class CorpsiteImpl extends IBOBaseImpl
implements CorpsiteSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Corpsite))
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
		if(!(obj instanceof Corpsite))
			throw new Exception("Ҫ����Ķ��󴫵ݲ���ȷ��");
		Corpsite corpsite = (Corpsite)obj;
		
		if(corpsite.getId() == null) //�½�
		{
			corpsite.setEnterby(this.getUserInfo().getLabornum());
			corpsite.setEnterdate(new Date());
			corpsite.setChangeby(this.getUserInfo().getLabornum());
			corpsite.setChangedate(new Date());
		} else {
			corpsite.setChangeby(this.getUserInfo().getLabornum());
			corpsite.setChangedate(new Date());
		}
		super.save(obj);
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
