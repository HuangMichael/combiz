package combiz.business.pr;

import combiz.domain.pr.Pr;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PrSrv extends IBOBaseSrv
{
	//����ѯ�۵���ѯ�۵���
	public String ceaterfq(Object obj) throws Exception;
	
	//���ɲɹ������ɹ�����
	public String createpo(Object obj) throws Exception;
	
	public String copypr(List list,Object obj) throws Exception;
	//�ϲ�����ƻ��е��ɹ�������
	public void uniteprline(List list,Object obj) throws Exception;
	
}
