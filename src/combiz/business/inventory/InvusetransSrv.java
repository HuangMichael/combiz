package combiz.business.inventory;

import combiz.domain.inventory.Invusetrans;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface InvusetransSrv extends IBOBaseSrv
{
//	ѡ�з����У������˿������
	public void returneq(List list) throws Exception;
}