package combiz.ui.smsg;
 
import combiz.domain.smsg.Msgreceive;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.Attachment;


public class MsgreceiveWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public MsgreceiveWindow()
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
