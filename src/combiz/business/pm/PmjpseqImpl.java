package combiz.business.pm;

import combiz.domain.pm.Pmjpseq;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ��������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class PmjpseqImpl extends IBOBaseImpl
implements PmjpseqSrv {


	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Pmjpseq))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}