package combiz.business.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface EquipmentSrv extends IBOBaseSrv
{
	public void cretetree(List classlist) throws Exception;
	public void copyfixed(List list) throws Exception;
	public void eqparentchg(Equipment equipment,String orgParent)	throws Exception;

}
