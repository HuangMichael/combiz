package combiz.business.assetscard;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface AllocationSrv  extends IBOBaseSrv
{
	//�ڵ���������ϸ����������ݣ�
	public void insertline(List list,Object s) throws Exception;
	//�ڵ�����������������ݣ�
	public void addallocline(List list,Object obj) throws Exception;
	//������������У�
	public void verify(List list,Object obj) throws Exception;

}
