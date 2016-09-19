package combiz.business.inventory;

import combiz.domain.inventory.Invusetrans;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface InvusetransSrv extends IBOBaseSrv
{
//	选中发放行，进行退库操作。
	public void returneq(List list) throws Exception;
}