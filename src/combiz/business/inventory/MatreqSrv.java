package combiz.business.inventory;
import combiz.system.IBOBaseSrv;
import java.util.List;

public interface MatreqSrv extends IBOBaseSrv
{
//  �ڷ��������У�ѡ��Ԥ������Ԥ�������Ŀ���뵽�����У��ȴ�ȷ��
	public void geninvuse(List list,Object obj) throws Exception;
//  ���������д���Ԥ����Ϣ
	public void createinvr(Object obj) throws Exception;
//  ȷ�Ϸ���
	public void verify(List list,Object obj) throws Exception;
//	���Ź���Ŀ����Ŀ�����˿�
	public void returnissue(List list,Object obj) throws Exception;
//	�����������˿������н���У��
	public void verifyreturn(List list,Object obj) throws Exception;
//	��ѡ�е���ת�����з���
	public void genequse(List list,Object obj) throws Exception;
//	��ѡ�е��������������ʽ��б���
	public int createitem(List list) throws Exception;
//������δ��ʶΪ�Ѵ�ӡ�ķ��ż�¼����ʶΪ�Ѵ�ӡ����
	public void hasprint(List list) throws Exception;
}
