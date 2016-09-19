package combiz.business.rfq;

import combiz.domain.rfq.Rfqquoteline;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class RfqquotelineImpl extends IBOBaseImpl
implements RfqquotelineSrv {
	
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfqquoteline))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}


	@Override
	public void save(Object arg0) 
	throws Exception 
	{
		Rfqquoteline rfqquoteline = (Rfqquoteline)arg0;
		String isawarded = rfqquoteline.getIsawarded();
		if(Util.isNotNull(isawarded) && isawarded.equals("��"))
		{
			//�Ѿ�������������Ӧ��
			int count = this.getRowCountByWhere(rfqquoteline, "rfqnum='"+rfqquoteline.getRfqnum()+
					"' and rfqlinenum="+rfqquoteline.getRfqlinenum()+" and itemnum='"+rfqquoteline.getItemnum()+"'"+
					" and vendor<>'"+rfqquoteline.getVendor()+"' and isawarded='��'");
			if(count>0)
			{
				throw new Exception("������["+rfqquoteline.getItemnum()+"]�Ѿ������˳������ҹ�Ӧ��,���鱨����!");
			}
		}
		super.save(arg0);
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
