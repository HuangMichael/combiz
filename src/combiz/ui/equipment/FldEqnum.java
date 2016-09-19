package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;

import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldEqnum extends FieldAdapter
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
		/*Equipment equipment = (Equipment)this.getMainObject();
		String parent =  equipment.getParent();
		
		String eqnum = (String) this.getValueByColname("eqnum");
		if (parent != null && parent !="") {
			if (eqnum !=null && eqnum !="") {
				if (parent.equals(eqnum)) {
					this.throwException(component, "������� ["+parent+"] Ϊ ["+parent+"] �Ĳ�����");
				}
			}
		}
		List eqlist = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class, " parent = '"+parent+"' and eqnum='"+eqnum+"'");
		if (!(eqlist.size()>0)) {
			this.throwException(component, "["+eqnum+"] �Ѿ�Ϊ ["+parent+"] �Ĳ���������Ҫ��ӣ����ʵ��");
		}*/
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception 
	{
		
	}

	@Override
	public String getListWhere(Component arg0) {
		// TODO Auto-generated method stub
				
		Equipment equipment = (Equipment)this.getMainObject();
		String parent = equipment.getParent();
		String str = "";
		if (parent != null && parent != "") {
			str = " eqnum = '"+parent+"' ";
			return str;
		}
		return super.getListWhere(arg0);
	}

}
