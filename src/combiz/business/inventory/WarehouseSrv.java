package combiz.business.inventory;

import java.util.List;

import combiz.system.IBOBaseSrv;


public interface WarehouseSrv extends IBOBaseSrv
{
	public void verify(List list,Object obj) throws Exception;//У�鷢��
//	���Ź���Ŀ����Ŀ�����˿�
	public void returnissue(List list,Object obj) throws Exception;
//	�����������˿������н���У��
	public void verifyreturn(List list,Object obj) throws Exception;
//	�����������˿�������ȡ���˿�
	public void verifyreturnall(List list,Object obj) throws Exception;
}
