package combiz.ui.workorder;

import java.util.List;

import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.pm.Premaint;
import combiz.domain.po.Poline;
import combiz.domain.workorder.Workorder;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDoublebox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 1:52:11 PM  Oct 23, 2008 
 * ���ܣ�������λ�ú������λ����ֻ��һ̨�豸���Ͱ��豸���д�뵽eqnum�ֶ�
 * �����workorder,premaint
 * �����ֶΣ�location
 */
public class FldLocation extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������
		Object obj = this.getMainObject();
		if(obj instanceof Workorder)
		{
			Workorder wo  = (Workorder) obj;
		}
		if(obj instanceof Premaint)
		{
			Premaint pre = (Premaint) obj;
		}

		String location = (String) this.getValueByColname("location");
		if (Util.isNotNull(location)) 
		{
			List equiplist = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class, "location ='"+location+"' ");
			if(equiplist.size()==1)
			{
				Equipment equip = (Equipment) equiplist.get(0);
				String equipnum = equip.getEqnum();
				this.setValueByColname("eqnum", equipnum);
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
