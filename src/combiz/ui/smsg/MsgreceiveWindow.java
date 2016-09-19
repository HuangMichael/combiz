package combiz.ui.smsg;
 
import combiz.domain.smsg.Msgreceive;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.Attachment;


public class MsgreceiveWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public MsgreceiveWindow()
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
		Msgreceive newobj = new Msgreceive();
		mainObject = newobj;
		return true;
	}



	@Override
	public void initData() 
	throws Exception 
	{
		super.initData();
		
		Attachment attachment = (Attachment) this.getFellow("attachment");
		attachment.setReadonly(true);
	}


}
