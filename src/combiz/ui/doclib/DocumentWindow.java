package combiz.ui.doclib;

import combiz.domain.doclib.Document;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;

public class DocumentWindow extends MainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public DocumentWindow()
	{
		super();
	}
	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Document newobj = new Document();
		newobj.setDocnum(this.genAutokey("docnum"));
		newobj.setCreator(this.getLaborInfo().getLabornum());
		newobj.setCreatedate(new Date());
		
		mainObject = newobj;
		return true;
	}
}
