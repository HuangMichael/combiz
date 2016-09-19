package combiz.ui.doclib;

import combiz.domain.doclib.Document;
import combiz.domain.doclib.Docversion;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import java.util.Date;

import com.inbasis.zul.Messagebox;

public class DocversionList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	public DocversionList()
	{
		super();
	}
			
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		RecWindow parentWnd = (RecWindow)ownerWnd;
		Document parent = (Document)parentWnd.getMainObject();
		if(parent==null || parent.getId()==null || parent.getDocnum()==null)
		{
			Messagebox.show("����ѡ��һ���ĵ���");
			return false;
		}
		Docversion newobj = new Docversion();
		newobj.setDocnum(parent.getDocnum());
		newobj.setLibnum(parent.getLibnum());
		newobj.setCreatedate(new Date());
		newobj.setCreator(this.getUserInfo().getLabornum());
		newobj.setDescription(parent.getDescription());
		newobj.setOwnerid(parent.getOwnerid());
		newobj.setOwnertable(parent.getOwnertable());
		newobj.setRevdate(new Date());
		newobj.setStatus("δ�ϴ�");
		newobj.setUrlpath(parent.getUrlpath());
		newobj.setUrltype(parent.getUrltype());
		
		//newobj.setVersion(version);
		this.mainObject = newobj;
		return true;
	}
}
