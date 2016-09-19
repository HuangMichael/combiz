package combiz.ui.workorder;

import combiz.domain.workorder.Wohazard;
import combiz.domain.workorder.Woisotag;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zul.Messagebox;

public class WoisotagList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WoisotagList()
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
		RecWindow parentWnd = (RecWindow)ownerWnd;
		Workorder workorder = (Workorder)parentWnd.getOwnerWnd().getMainObject();
		Wohazard wohazard = (Wohazard)parentWnd.getMainObject();
		
		if(wohazard == null ||wohazard.getHazardid() == null || wohazard.getHazardid().equals(""))
		{
			Messagebox.show("���ڰ�ȫ����ѡ��һΣ�ձ�ʶ����ӣ�");
			return false;
		}
		
		Woisotag newobj = new Woisotag();
		newobj.setHazardid(wohazard.getHazardid());
		newobj.setWonum(workorder.getWonum());

		this.mainObject = newobj;
		return true;
	}
	
}
