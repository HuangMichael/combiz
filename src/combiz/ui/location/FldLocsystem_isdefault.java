package combiz.ui.location;

import combiz.domain.location.Locsystem;
import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldLocsystem_isdefault extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{
		
	}

	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception 
	{
		String isdef = (String) this.getValueByColname("isdefault");
		if(isdef!=null && isdef.equals("��"))
		{
			int count = this.mainSrv.getBaseDao().selectCountByWhere(Locsystem.class, "isdefault='��'");
			if(count>0)
			{
				Messagebox.show("Ĭ��ϵͳ�Ѿ����ڣ�");
				this.setValueByColname("isdefault", "��");
			}
		}
	}

}
