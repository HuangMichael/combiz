package combiz.ui.tool;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolspec;
import combiz.system.ui.ListWindow;
import java.util.Date;

import com.inbasis.zul.Messagebox;

public class ToolspecList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ToolspecList()
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
		
		Toolspec newobj = new Toolspec();
		newobj.setToolnum(parent.getToolnum());
		newobj.setClassid(parent.getClassid());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setIsmustbe("��");

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
