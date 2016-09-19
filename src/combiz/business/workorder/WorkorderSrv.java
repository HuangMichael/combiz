package combiz.business.workorder;

import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface WorkorderSrv extends IBOBaseSrv
{
//	ɾ����׼��ҵ�ƻ�
	public void deljbplan(Object obj) throws Exception;
//  ����Ԥ��
	public void genequse(List list, Object obj) throws Exception; 
//	���ɹ�������������
	public void geninvuse(List list,Object obj) throws Exception;
//	�Թ������������н���У��
	public void verify(List list,Object obj) throws Exception;
//	Ϊ������������Ԥ��
	public void createinvr(Object obj) throws Exception;
//	���ɱ�׼��ҵ�ƻ�
	public void genJobplan(Workorder workorder,String jpnum) throws Exception;
//	������������ѡ�еĹ���������Ϊһ���µĹ���
	public Workorder copywo(Object obj) throws Exception;
//	���Ź���Ŀ����Ŀ�����˿�
	public void returnissue(List list,Object obj) throws Exception;
//	�Թ����˿������н���У��
	public void verifyreturn(List list,Object obj) throws Exception;
//	�ڹ������ƺ󣬴�����׼��ҵ�ƻ�.
	public void createjbplan(Object obj,Object obj2) throws Exception;
//  ������׼��ҵ�ƻ����ϡ����ߡ��˹����а��̵���Ϣ��	
	public void createjoplandetail(Workorder workorder,String jpnum) throws Exception;
//  �������ϴ��룬����ǰ�����¸ù��ϴ���Ĺ������⡢ԭ�����͹��ϴ�ʩ��ӵ����ϴ����ģ���		
	public void createfailcode(Object obj) throws Exception;
}
