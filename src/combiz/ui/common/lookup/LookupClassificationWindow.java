package combiz.ui.common.lookup;

import combiz.system.ui.ListWindow;
import combiz.system.ui.LookupWindow;
import combiz.system.ui.common.TableList;

import com.inbasis.zk.ui.event.Event;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Messagebox;

public class LookupClassificationWindow extends LookupWindow
{
	/**
	 * �����ϵ��ѡ��ֵ�����
	 * 
	 * ���ߣ�ljh ���ڣ�2008-03-10
	 */
	public void classOfok(Event event) throws Exception
	{
		// ������
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		
		ListWindow listwnd = (ListWindow) this.getFellow("classificationofitem");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("û�м�¼ѡ��.");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "item");
		//�����Զ������ֶ�ֵ	
		this.retLookupMapValue(selectObj);
		//��������ѯ�ֶε�ֵ
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);

		
		// �رմ���
		this.detach();
	}
}
