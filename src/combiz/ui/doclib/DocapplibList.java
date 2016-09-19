package combiz.ui.doclib;

import combiz.domain.doclib.Docapplib;
import combiz.domain.doclib.Doclibary;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class DocapplibList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	public DocapplibList()
	{
		super();
	}
			
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		DoclibaryWindow parentWnd = (DoclibaryWindow)ownerWnd;
		Doclibary parent = (Doclibary)parentWnd.getMainObject();
		
		Docapplib newobj = new Docapplib();
		newobj.setLibnum(parent.getLibnum());
		newobj.setIsrelapp("否");
		
		this.mainObject = newobj;
		return true;
	}
}
