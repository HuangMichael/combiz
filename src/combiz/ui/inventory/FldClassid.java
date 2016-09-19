package combiz.ui.inventory;

import combiz.domain.inventory.Item;
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
		List itemspecList;
		Item item = (Item)this.mainObject;
		try {
			itemspecList = this.mainSrv.findByRelation(mainObject, "itemspec");
			if(item.getClassid()!=null && itemspecList.size()>0)
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
