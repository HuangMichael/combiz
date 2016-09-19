package combiz.business.assetscard;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface CarryoverSrv  extends IBOBaseSrv
{
	public void copycarryover() throws Exception;
	public void copyuncarryover(Object obj) throws Exception;
	
}
