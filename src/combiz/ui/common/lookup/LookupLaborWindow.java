package combiz.ui.common.lookup;

import java.util.List;

import combiz.system.ui.ListWindow;
import combiz.system.ui.LookupWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.TableList;

import com.inbasis.zk.ui.event.Event;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Messagebox;

public class LookupLaborWindow extends LookupWindow
{
	/**
	 * 
	 * @TODO �����ϵ��ѡ��ֵ�����
	 * @param event
	 * @throws Exception
	 * @��С��  2007-8-13  ����02:18:51
	 */
	public void departOfok(Event event) throws Exception
	{
		// ������
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		
		ListWindow listwnd = (ListWindow) this.getFellow("laborOfDepartment");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null)
		{
			Messagebox.show("û�м�¼ѡ��.");
			return;
		}

		//��ѡ
		if(listbox.isMultiple())
		{
			List selectObj = listwnd.getSelectObjects();
			for(int i=0;i<selectObj.size();i++)
			{
				Object targetObj = selectObj.get(i);
				//��������ѯ�ֶε�ֵ
				this.retLookupValue(lookupbox, targetObj, this.targetfield, true);
			}
		}
		else
		{
			Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "labor");
			
			//��������ѯ�ֶε�ֵ
			this.retLookupValue(lookupbox, selectObj, this.targetfield, true);
			//�����Զ������ֶ�ֵ	
			this.retLookupMapValue(selectObj);
		}
		// �رմ���
		this.detach();
	}
}
