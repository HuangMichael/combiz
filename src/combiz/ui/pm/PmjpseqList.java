package combiz.ui.pm;

import combiz.domain.pm.Pmjpseq;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;

public class PmjpseqList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public PmjpseqList()
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
		PremaintWindow parentWnd = (PremaintWindow)ownerWnd;
		Premaint parent = (Premaint) parentWnd.getMainObject();
		
		Pmjpseq newobj = new Pmjpseq();
		newobj.setPmnum(parent.getPmnum());

		this.mainObject = newobj;
		return true;
	}
	

}
