package combiz.ui.location;

import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.system.ui.ListWindow;

public class AssetClassSepcList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AssetClassSepcList()
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
		AssetClassWindow parentWnd = (AssetClassWindow)ownerWnd;
		Classification parent = (Classification) parentWnd.getMainObject();
		
		Classspec newobj = new Classspec();
		newobj.setClassid(parent.getClassid());
		newobj.setIsmustbe("��");

		this.mainObject = newobj;
		return true;
	}
	
}
