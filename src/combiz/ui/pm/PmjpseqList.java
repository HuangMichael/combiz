package combiz.ui.pm;

import combiz.domain.pm.Pmjpseq;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;

public class PmjpseqList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public PmjpseqList()
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
		PremaintWindow parentWnd = (PremaintWindow)ownerWnd;
		Premaint parent = (Premaint) parentWnd.getMainObject();
		
		Pmjpseq newobj = new Pmjpseq();
		newobj.setPmnum(parent.getPmnum());

		this.mainObject = newobj;
		return true;
	}
	

}
