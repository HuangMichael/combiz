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
	 * 在树上点击选择值后调用
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public void locationOfok(Event event) throws Exception
	{
		// 主界面
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		ListWindow listwnd = (ListWindow) this.getFellow("equipmentOfLocations");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("没有记录选择。");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "equipment");
		
		//返回自动配置字段值	
		this.retLookupMapValue(selectObj);
		//返回主查询字段的值
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);
		
		// 关闭窗口
		this.detach();
	}
	
	
	/**
	 * 在树上点击选择值后调用
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public void equipClassOfok(Event event) throws Exception
	{
		// 主界面
		IBandbox lookupbox = (IBandbox)recWindow.getFellow(lookupID);
		ListWindow listwnd = (ListWindow) this.getFellow("classOfequip");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("没有记录选择.");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "equipment");
		
		//返回主查询字段的值
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);
		//返回自动配置字段值	
		this.retLookupMapValue(selectObj);
		
		// 关闭窗口
		this.detach();
	}
}
