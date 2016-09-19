package combiz.ui.doclib;

import combiz.domain.doclib.Document;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;

public class DocumentWindow extends MainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public DocumentWindow()
	{
		super();
	}
	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Document newobj = new Document();
		newobj.setDocnum(this.genAutokey("docnum"));
		newobj.setCreator(this.getLaborInfo().getLabornum());
		newobj.setCreatedate(new Date());
		
		mainObject = newobj;
		return true;
	}
}
