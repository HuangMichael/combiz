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
	 * 在树上点击选择值后调用
	 * 
	 * 作者：ljh 日期：2008-03-10
	 */
	public void classOfok(Event event) throws Exception
	{
		// 主界面
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		
		ListWindow listwnd = (ListWindow) this.getFellow("classificationofitem");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null || listbox.getSelectedItem().getValue() == null)
		{
			Messagebox.show("没有记录选择.");
			return;
		}
		Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "item");
		//返回自动配置字段值	
		this.retLookupMapValue(selectObj);
		//返回主查询字段的值
		this.retLookupValue(lookupbox, selectObj, this.targetfield, true);

		
		// 关闭窗口
		this.detach();
	}
}
