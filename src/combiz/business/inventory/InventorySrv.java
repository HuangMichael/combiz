package combiz.business.inventory;

import combiz.domain.inventory.Inventory;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface InventorySrv extends IBOBaseSrv
{
//	�ڿ��Ӧ�ó�����Կ����Ŀ�������ĵ���
	public void adjustbinnum(Object obj) throws Exception;
//	�ڿ��Ӧ�ó�����Կ����Ŀ���з���
	public void issue(Object obj) throws Exception;
//	�ڿ��Ӧ�ó�����Կ����Ŀ����������������
	public void editcurbal(Object obj) throws Exception;
//	�ڿ��Ӧ�ó�����Կ����Ŀ��������ƽ���ɱ�����
	public void rsetavgcost(Object obj) throws Exception;
//	�ض�������������
	public int autogenerate(List list) throws Exception;
}
