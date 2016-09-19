package combiz.business.rfq;

import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class RfqvendorImpl extends IBOBaseImpl
implements RfqvendorSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfqvendor))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Rfqvendor rv = (Rfqvendor)obj;
		//ɾ������
		super.delete(obj);
		//ɾ����������---������
		List rfqquoteline = this.getBaseDao().findWithQuery(Rfqquoteline.class, "vendor='"+rv.getVendor()+"' and rfqnum='"+rv.getRfqnum()+"'");
		this.getBaseDao().deleteBatch(rfqquoteline);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
