package combiz.business.assetscard;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface AssetscardSrv  extends IBOBaseSrv
{
	//����ĩ��ת��carryover���в���һ������
	public void insert() throws Exception;
	//����ĩ��ת��carryover����status�޸�״̬
	public void uninsert() throws Exception;
}
