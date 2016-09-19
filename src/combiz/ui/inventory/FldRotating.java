package combiz.ui.inventory;

import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;
import combiz.system.ui.common.ICombobox;

import com.inbasis.zk.ui.Component;
public class FldRotating extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) 
	{
		
	}
	/* 
	 * ���ܣ�
	 * ���ߣ����
	 * ���ڣ�2008-11-2����03:37:06
	 */
	public void validate(Component component) 
	throws Exception 
	{
			
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		Item inv =(Item) this.getMainObject();
		ICheckbox checkrot = (ICheckbox) component;
		ICombobox com = (ICombobox) component.getFellow("item.lottype");

		if (!(checkrot.getValue().equals("��"))) {
			this.setValueByColname("lottype","���ι���");
			this.setReadonly(com);
		}else{
			this.setNoReadonly(com);
		}
			
	}

}
