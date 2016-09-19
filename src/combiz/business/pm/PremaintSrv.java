package combiz.business.pm;

import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface PremaintSrv extends IBOBaseSrv
{
	
	public Workorder createWorkorder(Premaint premain ,String description) throws Exception;
}
