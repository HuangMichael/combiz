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
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CalendarWindow()
	{
		super();
	}

	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Calendar newobj = new Calendar();	
		mainObject = newobj;
		return true;
	}

	/**
	 * ������ģʽ
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
			Messagebox.show("����ѡ��һ����Σ�");
		}
		else
		{
			Calshift calshift = (Calshift) calshiftWnd.getObjectFromListbox(listitem, "calshift");
			if(calshift==null)
			{
				Messagebox.show("����ѡ��һ����Σ�");
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
