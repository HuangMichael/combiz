package combiz.ui.doclib;

import combiz.domain.doclib.Docapplib;
import combiz.domain.doclib.Doclibary;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class DocapplibList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	public DocapplibList()
	{
		super();
	}
			
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		DoclibaryWindow parentWnd = (DoclibaryWindow)ownerWnd;
		Doclibary parent = (Doclibary)parentWnd.getMainObject();
		
		Docapplib newobj = new Docapplib();
		newobj.setLibnum(parent.getLibnum());
		newobj.setIsrelapp("��");
		
		this.mainObject = newobj;
		return true;
	}
}
