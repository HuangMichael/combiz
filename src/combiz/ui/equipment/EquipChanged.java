package combiz.ui.equipment;

import com.inbasis.zul.Messagebox;
import combiz.business.equipment.EquipmentSrv;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.CommonDialog;
import combiz.system.util.Util;

public class EquipChanged extends CommonDialog
{

	private String orgParent;

	@Override
	public void onCreate() throws Exception
	{
		super.onCreate();
		orgParent = ((Equipment)this.getMainObject()).getParent();
	}

	@Override
	public void confirm() throws Exception
	{
		if(!this.canSave())
			return;

		Equipment equipment = (Equipment)this.getMainObject();
		if((Util.isNotNull(equipment.getParent()) && equipment.getParent().equals(orgParent))||Util.isNull(equipment.getParent())&&Util.isNull(orgParent))
		{
			Messagebox.show("û�е��������豸��");
			return;
		}
		else
		{

			if(Util.isNotNull(equipment.getParent()))
			{
				int count  = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Eqance where ancestor='"+equipment.getEqnum()+"' and eqnum='"+equipment.getParent()+"'");
				if(count>0)
				{
					Messagebox.show("�ò��ŵĸ��������Ǹ��豸�Լ������Ӽ�["+equipment.getParent()+"]��ȡ��������");
					return;
				}
				((EquipmentSrv)this.getMainSrv()).eqparentchg(equipment, orgParent);
				this.setConfirm(true);
				this.onClose();
			}
			else
			{
				((EquipmentSrv)this.getMainSrv()).eqparentchg(equipment, orgParent);
				this.setConfirm(true);
				this.onClose();
			}

		}
	}

}
