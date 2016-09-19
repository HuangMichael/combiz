package combiz.business.location;

import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.IBOBaseSrv;

public interface LocationsSrv extends IBOBaseSrv {
	public void updateLocCode(Locations loc) throws Exception;
	
	/**
	 * ÃÌº”Œª÷√
	 * 
	 * ”¢±¥Àº  Nov 24, 2009
	 * @param locstruct
	 * @param locations
	 * @throws Exception
	 */
	public void addLocations(Locstruct locstruct, Locations locations)
	throws Exception;
	

}
