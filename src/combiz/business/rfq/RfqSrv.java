package combiz.business.rfq;

import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface RfqSrv extends IBOBaseSrv
{
	//���ɱ�����
	public void createquot(Object obj) throws Exception;
	//ͨ��ѯ�۹�Ӧ�̼���Ӧ�ı��������ɲɹ����Ͳɹ�����
	//public String CreatePo(Rfqvendor rv,Rfq rfq) throws Exception;
	public void CreatePo(List rvlist,Rfq rfq) throws Exception;
	//�����ɹ�������
	public void CopyPrline(Object obj,List list) throws Exception;
	//���������ѯ�۵������ɺ�ͬ��
	public void createcont(List rvlist,List vendorlist,Rfq rfq) throws Exception;
}
