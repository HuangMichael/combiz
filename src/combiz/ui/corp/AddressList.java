package combiz.ui.corp;

import combiz.domain.corp.Corpaddress;
import combiz.domain.corp.Corporation;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class AddressList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AddressList()
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
