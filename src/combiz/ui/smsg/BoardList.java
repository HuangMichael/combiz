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
	 * ��Ӧ�б��е�˫���¼�
	 */
	@Override
	public void listRowDBClick(Listitem listitem)
	throws Exception 
	{
		//����������ʾ����
		if(this.mainObject==null)
		{
			Messagebox.show("ѡ��Ķ���Ϊ�գ����������Ƿ���ڣ�");
			return;
		}
		this.popupDialog(this.mainObject);
	}
	
	
}
