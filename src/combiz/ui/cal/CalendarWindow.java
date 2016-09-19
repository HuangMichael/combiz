package combiz.ui.cal;
 
import java.util.HashMap;
import java.util.Map;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calshift;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainListWindow;
import combiz.system.ui.common.TableList;


public class CalendarWindow extends MainListWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CalendarWindow()
	{
		super();
	}

	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Calendar newobj = new Calendar();	
		mainObject = newobj;
		return true;
	}

	/**
	 * 定义班次模式
	 * brianhong  2007-10-28
	 */
	public void shift()
	throws Exception
	{
		ListWindow calshiftWnd = (ListWindow) this.getFellow("calshift");
		TableList tablelist = calshiftWnd.getTablelist();
		Listitem listitem = tablelist.getSelectedItem();
		if(listitem==null)
		{
			Messagebox.show("请先选择一个班次！");
		}
		else
		{
			Calshift calshift = (Calshift) calshiftWnd.getObjectFromListbox(listitem, "calshift");
			if(calshift==null)
			{
				Messagebox.show("请先选择一个班次！");
			}
			else
			{
				Map param = new HashMap();
				param.put("calshift", calshift);
				this.popupDialog(mainObject, "/cal/calshiftdaylist.xul", param);
			}
		}
	}
	
}
