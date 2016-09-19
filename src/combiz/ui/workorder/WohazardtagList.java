package combiz.ui.workorder;

import combiz.domain.stdplan.Hazardtag;
import combiz.domain.workorder.Wohazard;
import combiz.system.ui.ListWindow;

public class WohazardtagList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WohazardtagList()
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
		Wohazard wohazard = (Wohazard)this.getOwnerWnd().getMainObject();
		if(wohazard == null ||wohazard.getHazardid() == null || wohazard.getHazardid().equals(""))
		{
			throw new Exception("���ڰ�ȫ����ѡ��һΣ�ձ�ʶ�����");
		}
		
		Hazardtag newobj = new Hazardtag();
		newobj.setHazardid(wohazard.getHazardid());

		this.mainObject = newobj;
		return true;
	}
	
}
