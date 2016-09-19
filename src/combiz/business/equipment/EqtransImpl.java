package combiz.business.equipment;

import combiz.domain.classattr.Classification;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseImpl;

import java.util.Date;
import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class EqtransImpl extends IBOBaseImpl
implements EqtransSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Eqtrans))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}


	@Override
	public void save(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Eqtrans eqtrans = (Eqtrans)arg0;
		
		List list = this.getBaseDao().findWithQuery(Equipment.class, "eqnum='"+eqtrans.getEqnum()+"'"); 
		if(list!=null && list.size()>0)
		{
			Equipment equipment = (Equipment)list.get(0);
			equipment.setLocation(eqtrans.getToloc());
			this.getBaseDao().updateObject(equipment);
			
			eqtrans.setDatemoved(new Date());
			eqtrans.setFromparent(equipment.getParent());	//
			eqtrans.setToparent(equipment.getParent());		//顶层::父级移至 可能需要以后修改
			eqtrans.setTransdate(eqtrans.getDatemoved());
			eqtrans.setMoveby(this.getLaborInfo().getLabornum());
			this.getBaseDao().updateObject(eqtrans);
		} else {
			throw new Exception("传递的参数不正确！");
		}
	}

	
/////////////////////业务方法//////////////////////////////////
}
