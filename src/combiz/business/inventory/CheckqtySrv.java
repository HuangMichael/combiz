package combiz.business.inventory;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface CheckqtySrv  extends IBOBaseSrv
{
	//����ָ�����̵���������ʵʱ������̵����ڵ���Ŀ��������
	public void generate(Object obj,String wherestr) throws Exception;
	//������ɵ��̵�����ϸ��
	public void cleardata(List list) throws Exception;
	//�����̵㵥�����ϸ�޸Ŀ��������
	public void modifycurbal(List list) throws Exception;

}
