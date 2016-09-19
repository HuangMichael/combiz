package combiz.business.equipment;

import combiz.domain.classattr.Classification;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseImpl;

import java.util.Date;
import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class EqtransImpl extends IBOBaseImpl
implements EqtransSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Eqtrans))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
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
			eqtrans.setToparent(equipment.getParent());		//����::�������� ������Ҫ�Ժ��޸�
			eqtrans.setTransdate(eqtrans.getDatemoved());
			eqtrans.setMoveby(this.getLaborInfo().getLabornum());
			this.getBaseDao().updateObject(eqtrans);
		} else {
			throw new Exception("���ݵĲ�������ȷ��");
		}
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
