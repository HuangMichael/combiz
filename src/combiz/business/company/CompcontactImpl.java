package combiz.business.company;

import combiz.domain.company.Compcontact;
import combiz.system.IBOBaseImpl;


/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class CompcontactImpl extends IBOBaseImpl implements CompcontactSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Compcontact))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "");
	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////
}
