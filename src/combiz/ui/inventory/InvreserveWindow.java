package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Invreserve;

import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class InvreserveWindow extends MainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvreserveWindow()
	{
		super();
	}

	
	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Invreserve newobj = new Invreserve();
		//����������Ӷ���ĳ�ʼ��ֵ
		
		mainObject = newobj;
		return true;
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����ѡ�м�¼��Ԥ����Ϣ
	 * ���ڣ�2:12:17 PM  Apr 9, 2009 
	 *
	 */
	public void clearinvre()
	throws Exception
	{
		List invreslist = this.getSelectObjects();
		this.mainSrv.getBaseDao().deleteBatch(invreslist);
		this.clear();
	}
	
}
