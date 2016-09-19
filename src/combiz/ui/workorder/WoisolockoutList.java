package combiz.ui.workorder;

import combiz.domain.workorder.Woisolockout;
import combiz.domain.workorder.Woisotag;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class WoisolockoutList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WoisolockoutList()
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
		RecWindow woisotagWnd = (RecWindow)ownerWnd;
		//RecWindow WorkorderWnd = (RecWindow)ownerWnd.getOwnerWnd().getOwnerWnd();
		
		Woisotag woisotag = (Woisotag)woisotagWnd.getMainObject();
		//Workorder workorder = (Workorder)WorkorderWnd.getMainObject();
		
		if(woisotag == null ||woisotag.getHazardid() == null || woisotag.getHazardid().equals(""))
		{
			Messagebox.show("���ڰ�ȫ����ѡ��һΣ�ձ�ʶ����ӣ�");
			return false;
		}
		
		Woisolockout newobj = new Woisolockout();
		newobj.setWonum(woisotag.getWonum());
		newobj.setIsotagid(woisotag.getIsotagid());
		newobj.setAplyseq(woisotag.getAplyseq());
		newobj.setState(woisotag.getState());
		newobj.setLocation(woisotag.getLocation());
		newobj.setEqnum(woisotag.getEqnum());

		this.mainObject = newobj;
		return true;
	}
	
}
