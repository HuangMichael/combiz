package combiz.business.po;

import combiz.domain.po.Po;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PoSrv extends IBOBaseSrv
{
	//�����ɹ�������
	public void copyprline(List list,Object obj) throws Exception;

	//�������Ͻ������ﴦ��
	public void addpoline(List list,Object obj) throws Exception;
	//�������н��м��飬ͬʱ�����ݿ�����ر�д���ݡ�
	public void verify(List list,Object obj,List polinelist) throws Exception;
	//�����ɹ�������ѡ�еĲɹ���������Ϊһ���µĲɹ���
	public Po copypo(Object obj) throws Exception;
}
