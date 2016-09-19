package combiz.ui.common.lookup;

import combiz.system.ui.ListWindow;
import combiz.system.ui.LookupWindow;
import combiz.system.ui.common.TableList;

import com.inbasis.zk.ui.event.Event;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Messagebox;

public class LookupFailurecodeWindow extends LookupWindow
{
	/**
	 * �����ϵ��ѡ��ֵ�����
	 * 
	 * ���ߣ�ljh ���ڣ�2008-03-10
	 */
	public void failurecodeOfok(Event event) throws Exception
	{
		// ������
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		
		ListWindow listwnd = (ListWindow) this.getFellow("failureofclass");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("û�м�¼ѡ��.");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "failurecode");
		
		//�����Զ������ֶ�ֵ	
		this.retLookupMapValue(selectObj);
		//��������ѯ�ֶε�ֵ
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);

		
		// �رմ���
		this.detach();
	}
}
