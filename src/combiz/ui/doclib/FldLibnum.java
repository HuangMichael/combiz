package combiz.ui.doclib;

import combiz.domain.doclib.Doclibary;
import combiz.system.FieldAdapter;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.ITextbox;

import com.inbasis.zk.ui.Component;

public class FldLibnum extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
	}

	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		ITextbox libnumTbox = (ITextbox)component;
		ITextbox libpathTbox = (ITextbox)libnumTbox.getFellow("doclibary.libpath");
		DoclibaryWindow mainwnd = (DoclibaryWindow)libnumTbox.getFellow("mainWnd");
		
		if (libnumTbox!=null && !libnumTbox.equals("")) {
			libpathTbox.setIBSValue(mainwnd.getParentPathStr() + libnumTbox.getValue() + "/");
		}
	}
}
