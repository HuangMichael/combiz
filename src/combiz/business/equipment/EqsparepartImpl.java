package combiz.business.equipment;

import combiz.domain.equipment.Eqsparepart;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class EqsparepartImpl extends IBOBaseImpl
implements EqsparepartSrv {
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Eqsparepart))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
