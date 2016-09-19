package combiz.ui.doclib;

import combiz.domain.doclib.Document;
import combiz.domain.doclib.Docversion;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import java.util.Date;

import com.inbasis.zul.Messagebox;

public class DocversionList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	public DocversionList()
	{
		super();
	}
			
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		RecWindow parentWnd = (RecWindow)ownerWnd;
		Document parent = (Document)parentWnd.getMainObject();
		if(parent==null || parent.getId()==null || parent.getDocnum()==null)
		{
			Messagebox.show("请先选择一个文档！");
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
		newobj.setStatus("未上传");
		newobj.setUrlpath(parent.getUrlpath());
		newobj.setUrltype(parent.getUrltype());
		
		//newobj.setVersion(version);
		this.mainObject = newobj;
		return true;
	}
}
