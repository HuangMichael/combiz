package combiz.business.doclib;

import combiz.domain.doclib.Doclibance;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class DoclibanceImpl extends IBOBaseImpl
implements DoclibanceSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Doclibance))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}
	/**
	 * @TODO ɾ�������б�����ȱ�����
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List doclibances) throws Exception {
		this.getBaseDao().deleteBatch(doclibances);
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
