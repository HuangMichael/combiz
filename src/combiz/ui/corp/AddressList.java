package combiz.ui.corp;

import combiz.domain.corp.Corpaddress;
import combiz.domain.corp.Corporation;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class AddressList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public AddressList()
	{
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		CorporationWindow parentWnd = (CorporationWindow)ownerWnd;
		Corporation parent = (Corporation) parentWnd.getMainObject();
		
		Corpaddress newobj = new Corpaddress();
		newobj.setCorpnum(parent.getCorpnum());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getLaborInfo().getLabornum());
		this.mainObject = newobj;
		return true;
	}

	/**
	 * 
	 * yuanjq  2007-9-10
	 * @throws Exception
	 * @see combiz.system.ui.ListWindow#save()
	 */
	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		Corpaddress newobj = (Corpaddress)this.mainObject;
		//newobj.setCorpnum(parent.getCorpnum());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getLaborInfo().getLabornum());
		//this.mainObject = newobj;
		super.save();
	}
	
}
