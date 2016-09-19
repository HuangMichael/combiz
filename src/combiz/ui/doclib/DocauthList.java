package combiz.ui.doclib;

import combiz.domain.doclib.Docauth;
import combiz.domain.doclib.Document;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class DocauthList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	public DocauthList()
	{
		super();
	}
			
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		RecWindow parentWnd = (RecWindow)ownerWnd;
		
		Document parent = (Document)parentWnd.getMainObject();
		if(parent.getId()==null)
		{
			Messagebox.show("请先添加文档！");
			return false;
		}
		Docauth newobj = new Docauth();
		newobj.setDocnum(parent.getDocnum());
		//newobj.setDocread("否");
		//newobj.setDocedit("否");
		
		this.mainObject = newobj;
		return true;
	}
}
