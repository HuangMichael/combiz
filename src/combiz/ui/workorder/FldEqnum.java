package combiz.ui.workorder;

import java.util.List;

import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldEqnum extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
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
		String eqnum= (String) this.getValueByColname("eqnum");
		String loc= (String) this.getValueByColname("location");
		List eqlist = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class, "eqnum='"+eqnum+"'");
		if(eqlist.size()==1&& Util.isNull(loc))
		{
			Equipment eq = (Equipment) eqlist.get(0);
			this.setValueByColname("location", eq.getLocation());
		}
	}

	/**
	 * ����λ�û�ȡ������豸
	 * 
	 * brianhong  2007-11-6
	 * @param component
	 * @return
	 */
	@Override
	public String getListWhere(Component component) 
	{
		IBandbox eqnumbox =  (IBandbox)component;
		String location=null;
		try {
			location = (String) this.getValueByColname("location");
		} catch (Exception e) {
			return location;
		}
		if(location!=null && location.length()>0)
		{
			return "location='"+location+"'";
		}
		
		return location;
	}
	
	
	

}
