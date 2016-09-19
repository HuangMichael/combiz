package combiz.ui.tool;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Tooltrans;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class TooltransList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public TooltransList()
	{
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Tool parent = (Tool) this.getOwnerWnd().getMainObject();
		
		Tooltrans newobj = new Tooltrans();
		newobj.setToolnum(parent.getToolnum());
		newobj.setEnterby(this.getUserInfo().getLabornum());
		newobj.setEnterdate(new Date());
		this.mainObject = newobj;
		return true;
	}

}
