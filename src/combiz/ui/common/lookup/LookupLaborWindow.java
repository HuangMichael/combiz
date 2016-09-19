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
	 * @TODO 在树上点击选择值后调用
	 * @param event
	 * @throws Exception
	 * @洪小林  2007-8-13  下午02:18:51
	 */
	public void departOfok(Event event) throws Exception
	{
		// 主界面
		Bandbox lookupbox = (Bandbox)recWindow.getFellow(lookupID);
		
		ListWindow listwnd = (ListWindow) this.getFellow("laborOfDepartment");
		TableList listbox = (TableList)listwnd.getTablelist();
		if(listbox.getItemCount() == 0 || listbox.getSelectedItem() == null)
		{
			Messagebox.show("没有记录选择.");
			return;
		}

		//多选
		if(listbox.isMultiple())
		{
			List selectObj = listwnd.getSelectObjects();
			for(int i=0;i<selectObj.size();i++)
			{
				Object targetObj = selectObj.get(i);
				//返回主查询字段的值
				this.retLookupValue(lookupbox, targetObj, this.targetfield, true);
			}
		}
		else
		{
			Object selectObj = listbox.getObjectFromListbox(listbox.getSelectedItem(), "labor");
			
			//返回主查询字段的值
			this.retLookupValue(lookupbox, selectObj, this.targetfield, true);
			//返回自动配置字段值	
			this.retLookupMapValue(selectObj);
		}
		// 关闭窗口
		this.detach();
	}
}
