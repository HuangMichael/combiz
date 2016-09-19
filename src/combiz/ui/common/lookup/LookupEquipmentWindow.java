package combiz.ui.common.lookup;

import combiz.system.ui.ListWindow;
import combiz.system.ui.LookupWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.TableList;

import com.inbasis.zk.ui.event.Event;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Messagebox;

public class LookupEquipmentWindow extends LookupWindow
{
	/**
	 * �����ϵ��ѡ��ֵ�����
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public void locationOfok(Event event) throws Exception
	{
		// ������
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		ListWindow listwnd = (ListWindow) this.getFellow("equipmentOfLocations");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("û�м�¼ѡ��");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "equipment");
		
		//�����Զ������ֶ�ֵ	
		this.retLookupMapValue(selectObj);
		//��������ѯ�ֶε�ֵ
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);
		
		// �رմ���
		this.detach();
	}
	
	
	/**
	 * �����ϵ��ѡ��ֵ�����
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public void equipClassOfok(Event event) throws Exception
	{
		// ������
		IBandbox lookupbox = (IBandbox)recWindow.getFellow(lookupID);
		ListWindow listwnd = (ListWindow) this.getFellow("classOfequip");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("û�м�¼ѡ��.");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "equipment");
		
		//��������ѯ�ֶε�ֵ
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);
		//�����Զ������ֶ�ֵ	
		this.retLookupMapValue(selectObj);
		
		// �رմ���
		this.detach();
	}
}
