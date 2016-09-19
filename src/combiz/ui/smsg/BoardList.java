package combiz.ui.smsg;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.system.ui.CommonListWindow;

public class BoardList extends CommonListWindow 
{
	public BoardList()
	{
		super();
	}

	/**
	 * 响应列表行的双击事件
	 */
	@Override
	public void listRowDBClick(Listitem listitem)
	throws Exception 
	{
		//弹出数据显示窗口
		if(this.mainObject==null)
		{
			Messagebox.show("选择的对象为空，请检查数据是否存在！");
			return;
		}
		this.popupDialog(this.mainObject);
	}
	
	
}
