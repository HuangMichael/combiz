package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;

import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldClassid extends FieldAdapter
{	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{
		List eqspecList;
		Equipment equipment = (Equipment)mainObject;
		try {
			eqspecList = this.mainSrv.findByRelation(equipment, "eqspec");
			if(equipment.getClassid()!=null && eqspecList.size()>0)
			{
				this.setReadonly(component);
			}
			else
			{
				this.setNoReadonly(component);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
	}

}
