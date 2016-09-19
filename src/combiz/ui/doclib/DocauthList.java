package combiz.ui.doclib;

import combiz.domain.doclib.Docauth;
import combiz.domain.doclib.Document;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class DocauthList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	public DocauthList()
	{
		super();
	}
			
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		RecWindow parentWnd = (RecWindow)ownerWnd;
		
		Document parent = (Document)parentWnd.getMainObject();
		if(parent.getId()==null)
		{
			Messagebox.show("��������ĵ���");
			return false;
		}
		Docauth newobj = new Docauth();
		newobj.setDocnum(parent.getDocnum());
		//newobj.setDocread("��");
		//newobj.setDocedit("��");
		
		this.mainObject = newobj;
		return true;
	}
}
