package combiz.ui.inventory;

import java.util.List;

import combiz.domain.corp.Labor;
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
 * 11:49:28 AM  Nov 5, 2008 
 * ���ܣ��ڷ����豸��ʱ�����ѡ�з��Ÿ�ĳһ����Ա�Ļ���������Ա���ڵĲ�����Ϣд�ء�
 * �����INVUSETRANS
 * �����ֶΣ�ISSUETOLABOR
 */
public class FldIssuelabor extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������
		
		String issuelabor = (String) this.getValueByColname("issuetolabor");
		if(Util.isNotNull(issuelabor))
		{
			List laborlist = this.getMainSrv().getBaseDao().findWithQuery(Labor.class, "labornum='"+issuelabor+"'");
			if(laborlist.size()>0)
			{
				Labor labor = (Labor) laborlist.get(0);
				String dept = labor.getDeptnum();
				this.setValueByColname("issuedeptnum", dept);
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
