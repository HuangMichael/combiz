package combiz.business.inventory;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface RejectSrv  extends IBOBaseSrv
{
//	�ڿ�汨��Ӧ�ó����ｫ�ֿ���ѡ�еĿ�������ɱ�����ϸ
	public void createdel(List list,String s) throws Exception;
//	�Ա��ϵ��µı�����ϸ�н�����׼���ϡ�
	public void verify(List list) throws Exception;
//	���ʲ����𱨷�Ӧ�ó����ｫ��̨����ѡ�����ϸ�����ɱ�����ϸ
	public void genrejectline(List list,String s) throws Exception;
//	�쵼����ʱ�򣬴ӱ���������ѡ��ͨ���������ʲ���
	public void noapproveeq(List list) throws Exception;
//	�쵼����ʱ���޸ļ�¼Ϊ���ϡ�
	public void noyesapproveeq(List list) throws Exception;	
//	�쵼����ʱ��ͬ�ⱨ���ʲ���
	public void approverej(List list,Object obj) throws Exception;
}
