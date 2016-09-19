package combiz.business.rfq;

import combiz.domain.rfq.Rfqline;
import combiz.domain.rfq.Rfqquoteline;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class RfqlineImpl extends IBOBaseImpl
implements RfqlineSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfqline))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Rfqline rl = (Rfqline)obj;
		//ɾ��������¼---������
		List rfqquoteline = this.getBaseDao().findWithQuery(Rfqquoteline.class, "rfqnum='"+rl.getRfqnum()+"' and rfqlinenum='"+rl.getRfqlinenum()+"'");
		this.getBaseDao().deleteBatch(rfqquoteline);
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
